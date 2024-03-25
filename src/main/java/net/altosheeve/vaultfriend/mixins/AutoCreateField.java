package net.altosheeve.vaultfriend.mixins;

import net.altosheeve.vaultfriend.Interactions;
import net.altosheeve.vaultfriend.Rendering;
import net.altosheeve.vaultfriend.field;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(Block.class)
public class AutoCreateField {

    @Unique
    public boolean JUSTDOITONCE = true;

    @Inject(at = @At("HEAD"), method = "onPlaced")
    private void placeCall(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        if (Objects.equals(state.getBlock().getName().getString(), "Sponge")) {
            if (JUSTDOITONCE) {
                Interactions.createField(pos, state);
                JUSTDOITONCE = false;
            } else { JUSTDOITONCE = true; }
        }
    }
}
