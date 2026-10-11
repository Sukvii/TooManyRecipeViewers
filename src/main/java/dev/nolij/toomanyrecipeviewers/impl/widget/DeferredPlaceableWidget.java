package dev.nolij.toomanyrecipeviewers.impl.widget;

import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.IPlaceable;
import mezz.jei.api.gui.placement.VerticalAlignment;
import mezz.jei.common.util.PlaceableUtil;

public class DeferredPlaceableWidget implements IPlaceable<DeferredPlaceableWidget> {
	
	@FunctionalInterface
	public interface PlaceableCallback {
		void setPosition(int x, int y);
	}
	
	private final PlaceableCallback callback;
	private final int width, height;
	
	private boolean placed = false;
	
	public DeferredPlaceableWidget(PlaceableCallback callback, int width, int height) {
		this.callback = callback;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public DeferredPlaceableWidget setPosition(int x, int y) {
		if (placed)
			throw new IllegalStateException();
		placed = true;
		
		callback.setPosition(x, y);
		return this;
	}
	
	// newer JEI made this overload abstract (it used to be a default method with exactly this body)
	@Override
	public DeferredPlaceableWidget setPosition(int areaX, int areaY, int areaWidth, int areaHeight, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
		return PlaceableUtil.setPosition(this, areaX, areaY, areaWidth, areaHeight, horizontalAlignment, verticalAlignment);
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}
	
}
