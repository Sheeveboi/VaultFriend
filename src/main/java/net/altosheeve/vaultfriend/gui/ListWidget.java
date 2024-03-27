package net.altosheeve.vaultfriend.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Environment(value= EnvType.CLIENT)
public class ListWidget extends ElementListWidget {

    public Screen screen;
    public int x;
    public int y;
    public ListWidget(Screen screen, MinecraftClient minecraftClient, int i, int j, int k, int l, int m) {
        super(minecraftClient, i, j, k, l, m);
        this.screen = screen;
        this.setRenderBackground(false);
        this.x = i;
        this.y = j;
        this.setRenderHeader(false, 0);
        this.setRenderHorizontalShadows(false);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        int x = this.left;
        int y = this.top;
        super.fillGradient(matrices, x, y, this.width + x, this.height, 0x44444444, 0x44444444);
        super.render(matrices, mouseX, mouseY, delta);
    }

    public void setElements() {
        this.addEntry(new FieldEntry(this));
    }

    public class FieldEntry extends Entry {

        ListWidget parent;
        public FieldEntry(ListWidget parent) {
            this.parent = parent;
        }
        @Override
        public List<? extends Selectable> selectableChildren() {
            ArrayList<Selectable> children = new ArrayList<>();
            CyclingButtonWidget<Boolean> button = CyclingButtonWidget.onOffBuilder(
                    Text.of("test 1"),
                    Text.of("test 2"))
                    .build(
                            0,
                            0,
                            20,
                            20,
                            Text.of("lol")

                    );
            children.add(button);
            return children;
        }

        @Override
        public List<? extends Element> children() {
            return null;
        }

        @Override
        public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        }
    }

}
