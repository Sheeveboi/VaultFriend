package net.altosheeve.vaultfriend;

import net.altosheeve.vaultfriend.gui.MainScreen;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Objects;

public class Interactions {

    public static boolean yMode = true;
    public static boolean randomColor = true;
    public static float fieldAlpha = 0.07f;
    public static float fieldR = 0.0f;
    public static float fieldG = 1.0f;
    public static float fieldB = 0.0f;

    public static float iAlpha = 0.1f;
    public static float iR = 1.0f;
    public static float iG = 0.0f;
    public static float iB = 0.0f;

    public static void updateColor(boolean value) {
        for (FieldRenderInfo field : Rendering.localBuffer) {
            if (!field.intersection) {
                if (value) {
                    float[] rgb = {(float) Math.random(), (float) Math.random(), (float) Math.random(), fieldAlpha};
                    field.rgb = rgb;
                } else {
                    float [] rgb = {fieldR, fieldG, fieldB, fieldAlpha};
                    field.rgb = rgb;
                }
            } else {
                float [] rgb = {iR, iG,iB, iAlpha};
                field.rgb = rgb;
            }
        }
    }
    public static BlockPos getLookingAtPos() {
        MinecraftClient client = MinecraftClient.getInstance();
        HitResult hit = client.crosshairTarget;
        assert hit != null;
        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) hit;
            return blockHit.getBlockPos();
        } else {
            return new BlockPos(0,0,0);
        }
    }

    public static void handleKeys() {
        while (Keymappings.addField.wasPressed()) {
            MinecraftClient client = MinecraftClient.getInstance();
            BlockPos pos = getLookingAtPos();
            createField(pos, client.world.getBlockState(pos));
        }

        while (Keymappings.removeField.wasPressed()) {
            MinecraftClient client = MinecraftClient.getInstance();
            BlockPos pos = getLookingAtPos();
            removeField(pos, client.world.getBlockState(pos));
        }

        while (Keymappings.yModeToggle.wasPressed()) {
            if (yMode) {
                Rendering.yLevel = 1;
                yMode = false;
            } else {
                Rendering.yLevel = 500;
                yMode = true;
            }
        }

        while (Keymappings.toggleRender.wasPressed()) Rendering.toggleRender = !Rendering.toggleRender;

        if (Keymappings.vMenuModkey.isPressed()) {
            if (Keymappings.mMenuModkey.isPressed()) {
                MinecraftClient.getInstance().setScreen(new MainScreen());
            }
        }

    }

    public static boolean withinField(BlockPos pos1, BlockPos pos2) {
        return (pos2.getX() <= pos1.getX() + 10 && pos2.getX() >= pos1.getX() - 10 &&
                pos2.getZ() <= pos1.getZ() + 10 && pos2.getZ() >= pos1.getZ() - 10 );
    }

    public static void createField(BlockPos pos, BlockState state) {
        if (Objects.equals(state.getBlock().getName().getString(), "Sponge")) {

            ArrayList<FieldRenderInfo> addToBuffer = new ArrayList<FieldRenderInfo>();
            for (int i = 0; i < Rendering.localBuffer.size(); i++) {
                FieldRenderInfo f = Rendering.localBuffer.get(i);
                if (f.origins.size() == 1) {
                    BlockPos o = f.origins.get(0);

                    BlockPos pc1 = new BlockPos(pos.getX() - 10, pos.getY(), pos.getZ() - 10);
                    BlockPos pc2 = new BlockPos(pos.getX() + 10, pos.getY(), pos.getZ() - 10);
                    BlockPos pc3 = new BlockPos(pos.getX() - 10, pos.getY(), pos.getZ() + 10);
                    BlockPos pc4 = new BlockPos(pos.getX() + 10, pos.getY(), pos.getZ() + 10);

                    BlockPos c1 = new BlockPos(o.getX() - 10, pos.getY(), o.getZ() - 10);
                    BlockPos c2 = new BlockPos(o.getX() + 10, pos.getY(), o.getZ() - 10);
                    BlockPos c3 = new BlockPos(o.getX() - 10, pos.getY(), o.getZ() + 10);
                    BlockPos c4 = new BlockPos(o.getX() + 10, pos.getY(), o.getZ() + 10);

                    if (withinField(pc1, o)) {
                        BlockPos nc1 = c4.add(1, 0, 1);
                        float[] rgb = {iR, iG, iB, iAlpha};
                        FieldRenderInfo fnew = new FieldRenderInfo(true, pos, pc1, nc1, rgb);
                        fnew.origins.add(o);
                        addToBuffer.add(fnew);
                    }
                    else if (withinField(pc2, o)) {
                        BlockPos nc1 = pc2.add(1, 0, 0);
                        BlockPos nc2 = c3.add(0, 0, 1);
                        float[] rgb = {iR, iG, iB, iAlpha};
                        FieldRenderInfo fnew = new FieldRenderInfo(true, pos, nc1, nc2, rgb);
                        fnew.origins.add(o);
                        addToBuffer.add(fnew);
                    }
                    else if (withinField(pc3, o)) {
                        BlockPos nc1 = pc3.add(0, 0, 1);
                        BlockPos nc2 = c2.add(1, 0, 0);
                        float[] rgb = {iR, iG, iB, iAlpha};
                        FieldRenderInfo fnew = new FieldRenderInfo(true, pos, nc1, nc2, rgb);
                        fnew.origins.add(o);
                        addToBuffer.add(fnew);
                    }
                    else if (withinField(pc4, o)) {
                        BlockPos nc1 = pc4.add(1, 0, 1);
                        float[] rgb = {iR, iG, iB, iAlpha};
                        FieldRenderInfo fnew = new FieldRenderInfo(true, pos, nc1, c1, rgb);
                        fnew.origins.add(o);
                        addToBuffer.add(fnew);
                    }
                }
            }
            Rendering.localBuffer.addAll(addToBuffer);

            BlockPos c1 = new BlockPos(pos.getX() - 10, pos.getY(), pos.getZ() - 10);
            BlockPos c2 = new BlockPos(pos.getX() + 11, pos.getY(), pos.getZ() + 11);

            if (randomColor) {
                float [] rgb = {(float) Math.random(), (float) Math.random(), (float) Math.random(), fieldAlpha};
                Rendering.localBuffer.add(new FieldRenderInfo(false, pos, c1, c2, rgb));
            }
            else {
                float [] rgb = {fieldR, fieldG, fieldB, fieldAlpha};
                Rendering.localBuffer.add(new FieldRenderInfo(false, pos, c1, c2, rgb));
            }

            Rendering.localBuffer.addAll(addToBuffer);
        }
    }
    public static void removeField(BlockPos pos, BlockState state) {
        if (Objects.equals(state.getBlock().getName().getString(), "Sponge")) {
            ArrayList<FieldRenderInfo> rem = new ArrayList<FieldRenderInfo>();
            for (int i = 0; i < Rendering.localBuffer.size(); i++) {
                FieldRenderInfo f = Rendering.localBuffer.get(i);
                for (int t = 0; t < f.origins.size(); t++) {
                    BlockPos o = f.origins.get(t);
                    if (o.getX() == pos.getX() && o.getY() == pos.getY() && o.getZ() == pos.getZ()) {
                        rem.add(f);
                    }
                }
            }
            Rendering.localBuffer.removeAll(rem);
        }
    }
}
