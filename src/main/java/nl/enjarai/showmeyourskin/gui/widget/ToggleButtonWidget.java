package nl.enjarai.showmeyourskin.gui.widget;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ToggleButtonWidget extends TexturedButtonWidget {
    private final Screen parent;
    @Nullable
    private final Text tooltip;
    private boolean enabled;
    private final BooleanConsumer toggleAction;

    public ToggleButtonWidget(Screen parent, int x, int y, int u, int v, Identifier texture, boolean initial, BooleanConsumer toggleAction, @Nullable Text tooltip) {
        super(x, y, 20, 20, u + (initial ? 0 : 20), v, texture, null);
        this.parent = parent;
        this.tooltip = tooltip;
        this.enabled = initial;
        this.toggleAction = toggleAction;
    }

    @Override
    public void onPress() {
        u += enabled ? 20 : -20;
        enabled = !enabled;

        toggleAction.accept(enabled);
    }

    @Override
    public void renderTooltip(MatrixStack matrices, int mouseX, int mouseY) {
        if (tooltip != null) {
            parent.renderTooltip(matrices, tooltip, mouseX, mouseY);
        }
    }
}
