package net.altosheeve.vaultfriend;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class FieldRenderInfo {
    public BlockPos c1;
    public BlockPos c2;
    public ArrayList<BlockPos> origins = new ArrayList<>();
    public float[] rgb;
    public boolean intersection;

    public FieldRenderInfo(boolean i, BlockPos originA, BlockPos c1A, BlockPos c2A, float[] rgbA) {
        intersection = i;
        origins.add(originA);
        c1 = c1A;
        c2 = c2A;
        rgb = rgbA;
    }

    public FieldRenderInfo(boolean i, BlockPos originA, BlockPos c1A, BlockPos c2A) {
        intersection = i;
        origins.add(originA);
        c1 = c1A;
        c2 = c2A;
        rgb = new float[]{1f, 0f, 0f, 0.05f};
    }

    public FieldRenderInfo(boolean i, BlockPos originA) {
        intersection = i;
        origins.add(originA);
        c1 = new BlockPos(0, 0, 0);
        c2 = new BlockPos(0, 0, 0);
        rgb = new float[]{1f, 0f, 0f, 0.05f};
    }
}
