package dev.nolij.toomanyrecipeviewers.impl.jei.api.gui.ingredient;

import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.TagEmiIngredient;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotDrawable;
import mezz.jei.api.ingredients.ITypedIngredient;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("NonExtendableApiUsage")
public interface ITMRVRecipeSlotDrawable extends IRecipeSlotDrawable {
	
	/**
	 * Implemented by EMI's {@link dev.emi.emi.api.widget.SlotWidget}.
	 */
	EmiIngredient getStack();
	
	// TMRV slots don't track JEI display groups; EMI cycles through every candidate, so they all form one group
	@Override
	default Stream<ITypedIngredient<?>> getDisplayedIngredients() {
		return getAllIngredients();
	}
	
	// EMI collapses ingredient lists that exactly match a tag into a `TagEmiIngredient`
	@Override
	default Optional<TagKey<?>> getTagKey() {
		return getStack() instanceof TagEmiIngredient tagIngredient ? Optional.of(tagIngredient.key) : Optional.empty();
	}
	
	@SuppressWarnings("removal")
	@Override
	default void draw(GuiGraphics guiGraphics) {
		throw new UnsupportedOperationException();
	}
	
	@SuppressWarnings("removal")
	@Override
	default void drawHoverOverlays(GuiGraphics guiGraphics) {
		throw new UnsupportedOperationException();
	}
	
	@SuppressWarnings("removal")
	@Override
	default List<Component> getTooltip() {
		throw new UnsupportedOperationException(); // TODO
	}
	
	@SuppressWarnings("removal")
	@Override
	default void getTooltip(ITooltipBuilder tooltipBuilder) {
		throw new UnsupportedOperationException(); // TODO
	}
	
	@Override
	default void drawTooltip(GuiGraphics guiGraphics, int i, int i1) {
		throw new UnsupportedOperationException(); // TODO
	}
	
	@SuppressWarnings("removal")
	@Override
	default boolean isMouseOver(double x, double y) {
		return getRect().contains((int) x, (int) y);
	}
	
	@SuppressWarnings("removal")
	@Override
	default void addTooltipCallback(mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback tooltipCallback) {
		throw new UnsupportedOperationException(); // TODO
	}
	
	@SuppressWarnings("removal")
	@Override
	default Rect2i getAreaIncludingBackground() {
		return getRect();
	}
	
	@Override
	default void drawHighlight(GuiGraphics guiGraphics, int colour) {
		throw new UnsupportedOperationException(); // TODO
	}
	
}
