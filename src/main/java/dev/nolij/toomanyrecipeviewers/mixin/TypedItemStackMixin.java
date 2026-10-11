package dev.nolij.toomanyrecipeviewers.mixin;

import dev.nolij.toomanyrecipeviewers.util.IItemStackish;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.common.ingredients.itemStacks.TypedItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = TypedItemStack.class, remap = false)
public abstract class TypedItemStackMixin implements IItemStackish<TypedItemStack> {
	
	@Shadow protected abstract Item getItem();
	
	@Override
	public Item tmrv$getItem() {
		return getItem();
	}
	
	// newer JEI turned the static `TypedItemStack.normalize(ITypedIngredient)` + `getNormalized()` pair into the
	// instance method `ITypedIngredient.normalize(IIngredientHelper)`. TypedItemStack's implementations ignore the helper.
	@Shadow public abstract TypedItemStack normalize(IIngredientHelper<ItemStack> ingredientHelper);
	
	@Override
	public TypedItemStack tmrv$normalize() {
		return normalize(null);
	}
	
}
