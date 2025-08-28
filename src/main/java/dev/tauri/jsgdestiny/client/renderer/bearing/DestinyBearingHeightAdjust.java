package dev.tauri.jsgdestiny.client.renderer.bearing;

import dev.tauri.jsg.blockentity.stargate.*;
import dev.tauri.jsg.config.JSGConfig;
import dev.tauri.jsg.stargate.StargateSizeEnum;
import dev.tauri.jsgdestiny.common.blockentity.DestinyBearingBE;

import java.util.Objects;

public class DestinyBearingHeightAdjust {
    public static float getY(DestinyBearingBE blockEntity) {
        var y = 1.2f;
        if (JSGConfig.Stargate.stargateSize.get() == StargateSizeEnum.SMALL)
            y = 0.8f;

        if (Objects.requireNonNull(blockEntity.getLevel()).getBlockEntity(blockEntity.getBlockPos().below()) instanceof StargateAbstractMemberBE member) {
            if (member.getClass() == StargateMilkyWayMemberBE.StargateMilkyWayChevronBE.class
                    || member.getClass() == StargatePegasusMemberBE.StargatePegasusChevronBE.class
                    || member.getClass() == StargateMovieMemberBE.StargateMovieChevronBE.class
            ) {
                y += 0.25f;
            } else if (member.getClass() == StargateTollanMemberBE.StargateTollanChevronBE.class) {
                y -= 0.65f;
            }
        }

        return y;
    }
}
