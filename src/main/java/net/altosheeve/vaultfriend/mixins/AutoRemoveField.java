package net.altosheeve.vaultfriend.mixins;

import net.altosheeve.vaultfriend.Interactions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class AutoRemoveField {
    @Inject(at = @At("HEAD"), method = "onBreak")
    private void placeCall(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        Interactions.removeField(pos, state);
    }
}
