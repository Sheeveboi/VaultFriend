package net.altosheeve.vaultfriend;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public class Rendering {
    public static int yLevel = 500;
    public static boolean toggleRender = true;
    public static ArrayList<FieldRenderInfo> localBuffer = new ArrayList<FieldRenderInfo>();

    public static void buildRenderBox(Box box, float R, float G, float B, float alpha, BufferBuilder buffer) {

        MinecraftClient mc = MinecraftClient.getInstance();
        GameRenderer gr = mc.gameRenderer;

        Vec3d vec3d = gr.getCamera().getPos().negate();
        box = box.offset(vec3d).expand(.01f);

        double x1 = box.minX;
        double y1 = box.minY;
        double z1 = box.minZ;

        double x2 = box.maxX;
        double y2 = box.maxY;
        double z2 = box.maxZ;

        buffer.vertex(x1, y1, z1).color(R, G, B, alpha).next();
        buffer.vertex(x1, y1, z1).color(R, G, B, alpha).next();
        buffer.vertex(x1, y1, z1).color(R, G, B, alpha).next();
        buffer.vertex(x1, y1, z2).color(R, G, B, alpha).next();
        buffer.vertex(x1, y2, z1).color(R, G, B, alpha).next();
        buffer.vertex(x1, y2, z2).color(R, G, B, alpha).next();
        buffer.vertex(x1, y2, z2).color(R, G, B, alpha).next();
        buffer.vertex(x1, y1, z2).color(R, G, B, alpha).next();
        buffer.vertex(x2, y2, z2).color(R, G, B, alpha).next();
        buffer.vertex(x2, y1, z2).color(R, G, B, alpha).next();
        buffer.vertex(x2, y1, z2).color(R, G, B, alpha).next();
        buffer.vertex(x2, y1, z1).color(R, G, B, alpha).next();
        buffer.vertex(x2, y2, z2).color(R, G, B, alpha).next();
        buffer.vertex(x2, y2, z1).color(R, G, B, alpha).next();
        buffer.vertex(x2, y2, z1).color(R, G, B, alpha).next();
        buffer.vertex(x2, y1, z1).color(R, G, B, alpha).next();
        buffer.vertex(x1, y2, z1).color(R, G, B, alpha).next();
        buffer.vertex(x1, y1, z1).color(R, G, B, alpha).next();
        buffer.vertex(x1, y1, z1).color(R, G, B, alpha).next();
        buffer.vertex(x2, y1, z1).color(R, G, B, alpha).next();
        buffer.vertex(x1, y1, z2).color(R, G, B, alpha).next();
        buffer.vertex(x2, y1, z2).color(R, G, B, alpha).next();
        buffer.vertex(x2, y1, z2).color(R, G, B, alpha).next();
        buffer.vertex(x1, y2, z1).color(R, G, B, alpha).next();
        buffer.vertex(x1, y2, z1).color(R, G, B, alpha).next();
        buffer.vertex(x1, y2, z2).color(R, G, B, alpha).next();
        buffer.vertex(x2, y2, z1).color(R, G, B, alpha).next();
        buffer.vertex(x2, y2, z2).color(R, G, B, alpha).next();
        buffer.vertex(x2, y2, z2).color(R, G, B, alpha).next();
        buffer.vertex(x2, y2, z2).color(R, G, B, alpha).next();
    }

    public static void render() {

        MinecraftClient mc = MinecraftClient.getInstance();
        GameRenderer gr = mc.gameRenderer;

        if (mc.player == null) return;
        if (mc.world == null) return;
        if (localBuffer.isEmpty()) return;

        RenderSystem.disableTexture();
        RenderSystem.enableDepthTest();
        RenderSystem.depthFunc(515);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.disableCull();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        buffer.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);

        try {
            if (toggleRender) {
                for (FieldRenderInfo f : localBuffer) {
                    Box b = new Box(f.c1.getX(), f.c1.getY(), f.c1.getZ(), f.c2.getX(), f.c2.getY() + yLevel, f.c2.getZ());
                    buildRenderBox(b, f.rgb[0], f.rgb[1], f.rgb[2], f.rgb[3], buffer);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        tessellator.draw();

        RenderSystem.enableTexture();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.lineWidth(1.0F);
        RenderSystem.clearColor(1, 1, 1, 0.3f);
    }
}
