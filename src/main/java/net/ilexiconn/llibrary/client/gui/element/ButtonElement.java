package net.ilexiconn.llibrary.client.gui.element;

import net.ilexiconn.llibrary.LLibrary;
import net.ilexiconn.llibrary.client.gui.element.color.ColorScheme;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.Function;

@SideOnly(Side.CLIENT)
public class ButtonElement<T extends GuiScreen> extends Element<T> {
    public static final ColorScheme CLOSE = ColorScheme.create(() -> LLibrary.CONFIG.getDarkAccentColor(), () -> 0xFFE04747);

    private String text;
    private Function<ButtonElement<T>, Boolean> function;

    public ButtonElement(T gui, String text, float posX, float posY, int width, int height, Function<ButtonElement<T>, Boolean> function) {
        super(gui, posX, posY, width, height);
        this.text = text;
        this.function = function;
    }

    @Override
    public void render(float mouseX, float mouseY, float partialTicks) {
        this.drawRectangle(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), this.isEnabled() && this.isSelected(mouseX, mouseY) ? this.getColorScheme().getPrimaryColor() : this.getColorScheme().getSecondaryColor());
        FontRenderer fontRenderer = this.getGUI().mc.fontRendererObj;
        fontRenderer.drawString(this.text, this.getPosX() + (this.getWidth() / 2) - (fontRenderer.getStringWidth(this.text) / 2), this.getPosY() + (this.getHeight() / 2) - (fontRenderer.FONT_HEIGHT / 2), LLibrary.CONFIG.getTextColor(), false);
    }

    @Override
    public boolean mouseClicked(float mouseX, float mouseY, int button) {
        if (this.isSelected(mouseX, mouseY)) {
            if (this.function.apply(this)) {
                this.getGUI().mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.ui_button_click, 1.0F));
            }
            return true;
        } else {
            return false;
        }
    }
}
