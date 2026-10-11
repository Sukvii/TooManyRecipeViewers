package dev.nolij.toomanyrecipeviewers.impl.widget;

import mezz.jei.api.gui.widgets.IDrawableWidget;
import mezz.jei.common.gui.elements.AbstractRecipeWidgetBuilder;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;

/**
 * A JEI {@link IDrawableWidget} that renders EMI's own textures/widgets, so the `IRecipeExtrasBuilder.add*Widget`
 * methods added in JEI 19.51 look the same as their deprecated `add*` counterparts. Position and tooltip handling are
 * inherited from JEI; the owning {@code TMRVRecipeWidget} translates to {@link #getPosition()} before drawing.
 */
public class EmiDrawableWidget extends AbstractRecipeWidgetBuilder<IDrawableWidget> implements IDrawableWidget {
	
	private final Renderable renderable;
	private final int width;
	private final int height;
	
	/**
	 * @param renderable drawn at (0, 0)
	 */
	public EmiDrawableWidget(Renderable renderable, int width, int height) {
		super(0, 0);
		this.renderable = renderable;
		this.width = width;
		this.height = height;
	}
	
	@Override
	protected IDrawableWidget getThis() {
		return this;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}
	
	@Override
	public void drawWidget(GuiGraphics guiGraphics, double mouseX, double mouseY) {
		renderable.render(guiGraphics, (int) mouseX, (int) mouseY, 0F);
	}
	
}
