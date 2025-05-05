package tps.mixin;

import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WorldTimeUpdateS2CPacket.class)
public interface WorldTimeUpdateAccessor {
    @Accessor("timeOfDay")
    long getTimeOfDay();
}
