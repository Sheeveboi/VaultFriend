package net.altosheeve.vaultfriend.gui;

import net.altosheeve.vaultfriend.Interactions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class MainScreen extends Screen {

    double h;
    public MainScreen() {
        super(Text.of("Vein friend config screen"));
        this.h = this.height;
    }
    /*
    private String reduceFloat(double i) {
        try {
            return Double.toString(i).substring(0, 4);
        } catch (Exception e) {
            if (i == 1) {
                return "1.00";
            } else if (i == 0) {
                return "0.00";
            }
        }
        return "";
    }

    @Override
    protected void init() {
        String on = "Random";
        String off = "Defined";
        this.h = this.height;
        if (!Interactions.randomColor) {
            on = "Defined";
            off = "Random";
        }
        CyclingButtonWidget<Boolean> button = CyclingButtonWidget.onOffBuilder(
                Text.of(on),
                Text.of(off))
                .build(
                        this.width / 9 * 1,
                        40,
                        this.width / 9 * 3,
                        20,
                        Text.of("Color Mode: "),
                        (button1, value) -> {
                            Interactions.randomColor = !Interactions.randomColor;
                            Interactions.updateColor(Interactions.randomColor);
                        }
                );

        String val = reduceFloat(Interactions.fieldAlpha);
        SliderWidget fieldAlpha = new SliderWidget(
                this.width / 9 * 1,
                75 - 20 / 2,
                this.width / 9 * 3,
                20,
                Text.of("Field Alpha: " + val),
                Interactions.fieldAlpha
        ) {
            @Override
            protected void updateMessage() {}

            @Override
            protected void applyValue() {
                String val = reduceFloat(this.value);
                this.setMessage(Text.of("Field Alpha: " + val));
                Interactions.fieldAlpha = (float)this.value;
                Interactions.updateColor(Interactions.randomColor);
            }
        };

        val = reduceFloat(Interactions.fieldR);
        SliderWidget fieldR = new SliderWidget(
                this.width / 9 * 1,
                96 - 20 / 2,
                this.width / 9 * 3,
                20,
                Text.of("Field R: " + val),
                Interactions.fieldR
        ) {
            @Override
            protected void updateMessage() {}

            @Override
            protected void applyValue() {
                String val = reduceFloat(this.value);
                this.setMessage(Text.of("Field R: " + val));
                Interactions.fieldR = (float)this.value;
                Interactions.updateColor(Interactions.randomColor);
            }
        };

        val = reduceFloat(Interactions.fieldG);
        SliderWidget fieldG = new SliderWidget(
                this.width / 9 * 1,
                117 - 20 / 2,
                this.width / 9 * 3,
                20,
                Text.of("Field G: " + val),
                Interactions.fieldG
        ) {
            @Override
            protected void updateMessage() {}

            @Override
            protected void applyValue() {
                String val = reduceFloat(this.value);
                this.setMessage(Text.of("Field G: " + val));
                Interactions.fieldG= (float)this.value;
                Interactions.updateColor(Interactions.randomColor);
            }
        };

        val = reduceFloat(Interactions.fieldB);
        SliderWidget fieldB = new SliderWidget(
                this.width / 9 * 1,
                138 - 20 / 2,
                this.width / 9 * 3,
                20,
                Text.of("Field B: " + val),
                Interactions.fieldB
        ) {
            @Override
            protected void updateMessage() {}

            @Override
            protected void applyValue() {
                String val = reduceFloat(this.value);
                this.setMessage(Text.of("Field B: " + val));
                Interactions.fieldB = (float)this.value;
                Interactions.updateColor(Interactions.randomColor);
            }
        };

        val = reduceFloat(Interactions.iAlpha);
        SliderWidget iAlpha = new SliderWidget(
                this.width / 9 * 1,
                160 - 20 / 2,
                this.width / 9 * 3,
                20,
                Text.of("Intersection Alpha: " + val),
                Interactions.iAlpha
        ) {
            @Override
            protected void updateMessage() {}

            @Override
            protected void applyValue() {
                String val = reduceFloat(this.value);
                this.setMessage(Text.of("Field Alpha: " + val));
                Interactions.iAlpha = (float)this.value;
                Interactions.updateColor(Interactions.randomColor);
            }
        };

        val = reduceFloat(Interactions.iR);
        SliderWidget iR = new SliderWidget(
                this.width / 9 * 1,
                181 - 20 / 2,
                this.width / 9 * 3,
                20,
                Text.of("Intersection R: " + val),
                Interactions.iR
        ) {
            @Override
            protected void updateMessage() {}

            @Override
            protected void applyValue() {
                String val = reduceFloat(this.value);
                this.setMessage(Text.of("Field R: " + val));
                Interactions.iR = (float)this.value;
                Interactions.updateColor(Interactions.randomColor);
            }
        };

        val = reduceFloat(Interactions.iG);
        SliderWidget iG = new SliderWidget(
                this.width / 9 * 1,
                202 - 20 / 2,
                this.width / 9 * 3,
                20,
                Text.of("Intersection G: " + val),
                Interactions.iG
        ) {
            @Override
            protected void updateMessage() {}

            @Override
            protected void applyValue() {
                String val = reduceFloat(this.value);
                this.setMessage(Text.of("Field G: " + val));
                Interactions.iG = (float)this.value;
                Interactions.updateColor(Interactions.randomColor);
            }
        };

        val = reduceFloat(Interactions.iB);
        SliderWidget iB = new SliderWidget(
                this.width / 9 * 1,
                223 - 20 / 2,
                this.width / 9 * 3,
                20,
                Text.of("Intersection B: " + val),
                Interactions.iB
        ) {
            @Override
            protected void updateMessage() {}

            @Override
            protected void applyValue() {
                String val = reduceFloat(this.value);
                this.setMessage(Text.of("Field B: " + val));
                Interactions.iB = (float)this.value;
                Interactions.updateColor(Interactions.randomColor);
            }
        };

        addDrawableChild(button);
        addDrawableChild(fieldAlpha);
        addDrawableChild(fieldR);
        addDrawableChild(fieldG);
        addDrawableChild(fieldB);
        addDrawableChild(iAlpha);
        addDrawableChild(iR);
        addDrawableChild(iG);
        addDrawableChild(iB);

        ListWidget fieldList = new ListWidget(
                this,
                MinecraftClient.getInstance(),
                this.width / 9 * 3,
                (int) 233,
                40,
                (int) this.h - 10,
                20
        );
        fieldList.setLeftPos(this.width / 9 * 5);
        fieldList.setElements();

        addDrawableChild(fieldList);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);

        final MultilineText visheader = MultilineText.create(textRenderer, Text.of("Options"));
        visheader.drawCenterWithShadow(matrices, this.width / 9 * 3, 20);

        super.render(matrices, mouseX, mouseY, delta);
    }*/
}
