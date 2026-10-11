package dev.nolij.toomanyrecipeviewers.mixin;

import dev.nolij.toomanyrecipeviewers.impl.ingredient.ErrorIngredient;
import dev.nolij.toomanyrecipeviewers.impl.ingredient.TMRVStack;
import dev.nolij.toomanyrecipeviewers.util.IStackish;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.common.ingredients.TypedIngredientUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// newer JEI replaced `TypedIngredient.defensivelyCopyTypedIngredientFromApi` with the `TypedIngredientUtil.check*FromApi`
// family, which all funnel through `isKnownType`. Treating TMRV/EMI-backed stacks as known skips the defensive copy
// (and the dev-environment `IllegalArgumentException` for "unknown" `ITypedIngredient` implementations).
@Mixin(value = TypedIngredientUtil.class, remap = false)
public class TypedIngredientMixin {
	
	@Inject(method = "isKnownType", at = @At("HEAD"), cancellable = true)
	private static void tmrv$isKnownType$HEAD(ITypedIngredient<?> typedIngredient, CallbackInfoReturnable<Boolean> cir) {
		if (typedIngredient instanceof IStackish<?> || typedIngredient instanceof TMRVStack<?> || typedIngredient == ErrorIngredient.TYPED_INSTANCE) {
			cir.setReturnValue(true);
		}
	}
	
}
