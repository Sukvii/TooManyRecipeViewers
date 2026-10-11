package dev.nolij.toomanyrecipeviewers.impl.widget;

import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.api.widget.Widget;
import dev.emi.emi.jemi.impl.JemiTooltipBuilder;
import dev.emi.emi.runtime.EmiDrawContext;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.VerticalAlignment;
import mezz.jei.api.gui.widgets.IRecipeWidgetTooltipCallback;
import mezz.jei.api.gui.widgets.ITextWidget;
import mezz.jei.common.util.PlaceableUtil;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.Collection;
import java.util.List;

public class TextWidget extends Widget implements ITextWidget {
	
	private final mezz.jei.common.gui.elements.TextWidget delegate;
	
	public TextWidget(List<FormattedText> lines, int maxWidth, int maxHeight) {
		this.delegate = new mezz.jei.common.gui.elements.TextWidget(lines, 0, 0, maxWidth, maxHeight);
	}
	
	@Override
	public ITextWidget setPosition(int x, int y) {
		delegate.setPosition(x, y);
		return this;
	}
	
	// newer JEI made this overload abstract (it used to be a default method with exactly this body)
	@Override
	public ITextWidget setPosition(int areaX, int areaY, int areaWidth, int areaHeight, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
		return PlaceableUtil.setPosition(this, areaX, areaY, areaWidth, areaHeight, horizontalAlignment, verticalAlignment);
	}
	
	@Override
	public int getWidth() {
		return delegate.getWidth();
	}
	
	@Override
	public int getHeight() {
		return delegate.getHeight();
	}
	
	@Override
	public Bounds getBounds() {
		final var position = delegate.getPosition();
		return new Bounds(position.x(), position.y(), getWidth(), getHeight());
	}
	
	// JEI 19.51: `ITextWidget` now extends `IRecipeWidgetBuilder`, whose tooltip is stored by the JEI delegate
	@Override
	public List<ClientTooltipComponent> getTooltip(int mouseX, int mouseY) {
		final var position = delegate.getPosition();
		final var tooltipBuilder = new JemiTooltipBuilder();
		delegate.getTooltip(tooltipBuilder, mouseX - position.x(), mouseY - position.y());
		return tooltipBuilder.tooltip;
	}
	
	@Override
	public void render(GuiGraphics draw, int mouseX, int mouseY, float delta) {
		final var position = delegate.getPosition();
		final var context = EmiDrawContext.wrap(draw);
		context.push();
		context.matrices().translate(position.x(), position.y(), 0);
		delegate.drawWidget(context.raw(), mouseX, mouseY);
		context.pop();
	}
	
	//region ITextWidget
	@Override
	public ITextWidget setFont(Font font) {
		delegate.setFont(font);
		return this;
	}
	
	@Override
	public ITextWidget setColor(int colour) {
		delegate.setColor(colour);
		return this;
	}
	
	@Override
	public ITextWidget setLineSpacing(int spacing) {
		delegate.setLineSpacing(spacing);
		return this;
	}
	
	@Override
	public ITextWidget setShadow(boolean shadow) {
		delegate.setShadow(shadow);
		return this;
	}
	
	@Override
	public ITextWidget setTextAlignment(HorizontalAlignment horizontalAlignment) {
		delegate.setTextAlignment(horizontalAlignment);
		return this;
	}
	
	@Override
	public ITextWidget setTextAlignment(VerticalAlignment verticalAlignment) {
		delegate.setTextAlignment(verticalAlignment);
		return this;
	}
	//endregion
	
	//region IRecipeWidgetBuilder
	@Override
	public ITextWidget setTooltip(FormattedText tooltip) {
		delegate.setTooltip(tooltip);
		return this;
	}
	
	@Override
	public ITextWidget setTooltip(Collection<? extends FormattedText> tooltip) {
		delegate.setTooltip(tooltip);
		return this;
	}
	
	@Override
	public ITextWidget setTooltip(TooltipComponent tooltip) {
		delegate.setTooltip(tooltip);
		return this;
	}
	
	@Override
	public ITextWidget setTooltip(IRecipeWidgetTooltipCallback tooltipCallback) {
		delegate.setTooltip(tooltipCallback);
		return this;
	}
	//endregion
	
}
