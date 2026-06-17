package dev.tauri.jsgdestiny.common.blockentity;

import dev.tauri.jsg.api.block.stargate.IStargateChevronBlock;
import dev.tauri.jsg.api.stargate.Stargate;
import dev.tauri.jsg.api.stargate.listener.IStargateListener;
import dev.tauri.jsg.api.stargate.result.StargateOpenResult;
import dev.tauri.jsg.common.blockentity.stargate.StargateAbstractMemberBE;
import dev.tauri.jsg.common.registry.tags.JSGBlockTags;
import dev.tauri.jsg.core.common.blockentity.BEStateProvider;
import dev.tauri.jsg.core.common.blockentity.ITickable;
import dev.tauri.jsg.core.common.blockentity.ScheduledTaskExecutorInterface;
import dev.tauri.jsg.core.common.entity.ScheduledTask;
import dev.tauri.jsg.core.common.entity.ScheduledTaskType;
import dev.tauri.jsg.core.common.entity.State;
import dev.tauri.jsg.core.common.entity.StateType;
import dev.tauri.jsg.core.common.registry.CoreStateTypes;
import dev.tauri.jsg.core.common.symbol.SymbolInterface;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyBlockEntities;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyScheduledTasks;
import dev.tauri.jsgdestiny.common.state.DestinyBearingRendererState;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DestinyBearingBE extends BlockEntity implements ITickable, BEStateProvider, ScheduledTaskExecutorInterface, IStargateListener {
    public DestinyBearingBE(BlockPos pPos, BlockState pBlockState) {
        super(JSGDestinyBlockEntities.DESTINY_BEARING.get(), pPos, pBlockState);
    }

    public DestinyBearingBE(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Override
    public BlockPos getListenerBlockPos() {
        return getBlockPos();
    }

    @Override
    public void gateOpen() {
        activate(0);
    }

    @Override
    public void gateDisconnect() {
        deactivate();
    }

    @Override
    public void gateFail(StargateOpenResult result) {
        deactivate();
    }

    @Override
    public void gateSymbolEngage(SymbolInterface symbol) {
        activate(20);
    }

    @Override
    public void gateIncoming(int dialedAddressSize) {
        activate(0);
    }

    public BlockPos gateBasePos;


    protected boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    private ScheduledTask lastDeactivationTask;

    public void activate(int time) {
        if (getLevel() == null || getLevel().isClientSide) return;
        this.isActive = true;
        if (lastDeactivationTask != null)
            removeTask(lastDeactivationTask);
        lastDeactivationTask = null;
        if (time > 0) {
            lastDeactivationTask = new ScheduledTask(JSGDestinyScheduledTasks.DESTINY_BEARING_RENDER_UPDATE, time);
            addTask(lastDeactivationTask);
        }
        setChanged();
        getAndSendState(CoreStateTypes.RENDERER_STATE.get());
    }

    public void deactivate() {
        if (getLevel() == null || getLevel().isClientSide) return;
        lastDeactivationTask = null;
        this.isActive = false;
        setChanged();
        getAndSendState(CoreStateTypes.RENDERER_STATE.get());
    }

    // ------------------------------------------------------------------------
    // Scheduled tasks

    /**
     * List of scheduled tasks to be performed on {@link ITickable#tick(net.minecraft.world.level.Level)()}.
     */
    protected List<ScheduledTask> scheduledTasks = new ArrayList<>();

    @Override
    public void addTask(ScheduledTask scheduledTask) {
        if (getLevel() == null) return;
        scheduledTask.setExecutor(this);
        scheduledTask.setTaskCreated(getLevel().getGameTime());

        scheduledTasks.add(scheduledTask);
        setChanged();
    }

    public void removeTask(ScheduledTask scheduledTask) {
        scheduledTasks.remove(scheduledTask);
        setChanged();
    }

    @Override
    public void executeTask(ScheduledTaskType enumScheduledTask, @Nullable CompoundTag compoundTag) {
        if (enumScheduledTask == JSGDestinyScheduledTasks.DESTINY_BEARING_RENDER_UPDATE.get()) {
            deactivate();
        }
    }

    public final DestinyBearingRendererState rendererState = new DestinyBearingRendererState();

    @Override
    public State getState(StateType stateTypeEnum) {
        if (stateTypeEnum == CoreStateTypes.RENDERER_STATE.get()) {
            rendererState.active = isActive;
            return rendererState;
        }
        return null;
    }

    @Override
    public State createState(StateType stateTypeEnum) {
        if (stateTypeEnum == CoreStateTypes.RENDERER_STATE.get())
            return new DestinyBearingRendererState();
        return null;
    }

    @Override
    public void setState(StateType stateTypeEnum, State state) {
        if (stateTypeEnum == CoreStateTypes.RENDERER_STATE.get()) {
            rendererState.active = ((DestinyBearingRendererState) state).active;
            setChanged();
        }
    }

    protected PacketDistributor.TargetPoint targetPoint;

    @Nullable
    protected Stargate<?> getLinkableStargate() {
        if (getLevel() == null) return null;
        var belowBlock = getLevel().getBlockState(getBlockPos().below()).getBlock();
        if (!(belowBlock instanceof IStargateChevronBlock)) return null;
        var belowBE = getLevel().getBlockEntity(getBlockPos().below());
        if (!(belowBE instanceof StargateAbstractMemberBE stargateMember)) return null;
        return stargateMember.getBaseTile(getLevel());
    }

    protected void updateLink() {
        if (getLevel() == null) return;
        if (gateBasePos == null) {
            var base = getLinkableStargate();
            if (base != null) {
                gateBasePos = base.blockPosition();
                setChanged();
                base.getListenerHandler().addListener(this);
                JSGDestiny.logger.info("added to listeners");
            }
        } else {
            var gState = getLevel().getBlockState(gateBasePos);
            if (!gState.is(JSGBlockTags.ALL_STARGATE_BASES) || !(getLevel().getBlockEntity(gateBasePos) instanceof Stargate<?>)) {
                gateBasePos = null;
                setChanged();
                JSGDestiny.logger.info("removed from listeners");
            }
        }
    }

    @Override
    public void tick(@NotNull Level level) {
        // Scheduled tasks
        ScheduledTask.iterate(scheduledTasks, level.getGameTime());
        if (!level.isClientSide) {
            var pos = getBlockPos();
            if (targetPoint == null) {
                targetPoint = new PacketDistributor.TargetPoint(pos.getX(), pos.getY(), pos.getZ(), 512, level.dimension());
                setChanged();
            }
            updateLink();
        } else {
            requestState(CoreStateTypes.RENDERER_STATE.get());
        }
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        compound.put("scheduledTasks", ScheduledTask.serializeList(scheduledTasks));
        compound.putBoolean("isActive", isActive);
        if (gateBasePos != null)
            compound.putLong("gateBasePos", gateBasePos.asLong());
    }

    @Override
    public void load(@NotNull CompoundTag compound) {
        ScheduledTask.deserializeList(compound.getCompound("scheduledTasks"), scheduledTasks, this);
        isActive = compound.getBoolean("isActive");
        if (compound.contains("gateBasePos"))
            gateBasePos = BlockPos.of(compound.getLong("gateBasePos"));
    }

    @Override
    public PacketDistributor.TargetPoint getTargetPoint() {
        if (getLevel() == null) return targetPoint;
        if (targetPoint == null) {
            var pos = getStateHandlerBlockPos();
            targetPoint = new PacketDistributor.TargetPoint(pos.getX(), pos.getY(), pos.getZ(), 512, getLevel().dimension());
        }
        return targetPoint;
    }
}
