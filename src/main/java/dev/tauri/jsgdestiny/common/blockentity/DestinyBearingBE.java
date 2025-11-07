package dev.tauri.jsgdestiny.common.blockentity;

import dev.tauri.jsg.JSG;
import dev.tauri.jsg.api.stargate_listener.IStargateListener;
import dev.tauri.jsg.block.stargate.IStargateChevronBlock;
import dev.tauri.jsg.blockentity.stargate.StargateAbstractBaseBE;
import dev.tauri.jsg.blockentity.stargate.StargateAbstractMemberBE;
import dev.tauri.jsg.blockentity.util.ScheduledTask;
import dev.tauri.jsg.blockentity.util.ScheduledTaskExecutorInterface;
import dev.tauri.jsg.packet.JSGPacketHandler;
import dev.tauri.jsg.packet.packets.StateUpdatePacketToClient;
import dev.tauri.jsg.packet.packets.StateUpdateRequestToServer;
import dev.tauri.jsg.registry.TagsRegistry;
import dev.tauri.jsg.stargate.ScheduledTaskType;
import dev.tauri.jsg.stargate.StargateOpenResult;
import dev.tauri.jsg.stargate.network.SymbolInterface;
import dev.tauri.jsg.state.State;
import dev.tauri.jsg.state.StateProviderInterface;
import dev.tauri.jsg.state.StateTypeEnum;
import dev.tauri.jsg.util.ITickable;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.expansion.ScheduledTasksRegistry;
import dev.tauri.jsgdestiny.common.registry.BlockEntityRegistry;
import dev.tauri.jsgdestiny.common.state.DestinyBearingRendererState;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DestinyBearingBE extends BlockEntity implements ITickable, StateProviderInterface, ScheduledTaskExecutorInterface, IStargateListener {
    public DestinyBearingBE(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.DESTINY_BEARING.get(), pPos, pBlockState);
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
            lastDeactivationTask = new ScheduledTask(ScheduledTasksRegistry.DESTINY_BEARING_RENDER_UPDATE, time);
            addTask(lastDeactivationTask);
        }
        setChanged();
        getAndSendState(DestinyBearingRendererState.STATE_TYPE);
    }

    public void deactivate() {
        if (getLevel() == null || getLevel().isClientSide) return;
        lastDeactivationTask = null;
        this.isActive = false;
        setChanged();
        getAndSendState(DestinyBearingRendererState.STATE_TYPE);
    }

    // ------------------------------------------------------------------------
    // Scheduled tasks

    /**
     * List of scheduled tasks to be performed on {@link ITickable#tick()()}.
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
        if (enumScheduledTask == ScheduledTasksRegistry.DESTINY_BEARING_RENDER_UPDATE) {
            deactivate();
        }
    }

    public final DestinyBearingRendererState rendererState = new DestinyBearingRendererState();

    @Override
    public State getState(StateTypeEnum stateTypeEnum) {
        if (stateTypeEnum == DestinyBearingRendererState.STATE_TYPE) {
            rendererState.active = isActive;
            return rendererState;
        }
        return null;
    }

    @Override
    public State createState(StateTypeEnum stateTypeEnum) {
        if (stateTypeEnum == DestinyBearingRendererState.STATE_TYPE)
            return new DestinyBearingRendererState();
        return null;
    }

    @Override
    public void setState(StateTypeEnum stateTypeEnum, State state) {
        if (stateTypeEnum == DestinyBearingRendererState.STATE_TYPE) {
            rendererState.active = ((DestinyBearingRendererState) state).active;
            setChanged();
        }
    }

    protected PacketDistributor.TargetPoint targetPoint;

    @Override
    public void sendState(StateTypeEnum type, State state) {
        if (getLevel() == null || getLevel().isClientSide) return;

        if (targetPoint != null) {
            JSGPacketHandler.sendToClient(new StateUpdatePacketToClient(getBlockPos(), type, state), targetPoint);
        } else {
            JSG.logger.debug("targetPoint was null trying to send {} from {}", type, this.getClass().getCanonicalName());
        }
    }

    @Override
    public void onLoad() {
        if (getLevel() != null && !getLevel().isClientSide) {
            var pos = getBlockPos();
            targetPoint = new PacketDistributor.TargetPoint(pos.getX(), pos.getY(), pos.getZ(), 512, getLevel().dimension());
        }
    }

    @Nullable
    protected StargateAbstractBaseBE getLinkableStargate() {
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
                gateBasePos = base.getBlockPos();
                setChanged();
                base.listenerHandler.addListener(this);
                JSGDestiny.logger.info("added to listeners");
            }
        } else {
            var gState = getLevel().getBlockState(gateBasePos);
            if (!gState.is(TagsRegistry.ALL_STARGATE_BASES) || !(getLevel().getBlockEntity(gateBasePos) instanceof StargateAbstractBaseBE)) {
                gateBasePos = null;
                setChanged();
                JSGDestiny.logger.info("removed from listeners");
            }
        }
    }

    @Override
    public void tick() {
        if (getLevel() == null) return;
        // Scheduled tasks
        ScheduledTask.iterate(scheduledTasks, getLevel().getGameTime());
        if (!getLevel().isClientSide) {
            var pos = getBlockPos();
            if (targetPoint == null) {
                targetPoint = new PacketDistributor.TargetPoint(pos.getX(), pos.getY(), pos.getZ(), 512, getLevel().dimension());
                setChanged();
            }
            updateLink();
        } else {
            JSGPacketHandler.sendToServer(new StateUpdateRequestToServer(getBlockPos(), DestinyBearingRendererState.STATE_TYPE));
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
}
