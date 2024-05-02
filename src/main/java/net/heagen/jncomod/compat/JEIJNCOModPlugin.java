package net.heagen.jncomod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.heagen.jncomod.recipe.JeanBuildingRecipe;
import net.heagen.jncomod.recipe.SewingMachineRecipe;
import net.heagen.jncomod.screen.JeanBuildingStationScreen;
import net.heagen.jncomod.screen.SewingMachineStationScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIJNCOModPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return null;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new JeanBuildingRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(new SewingMachineRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<JeanBuildingRecipe> jeanRecipes = recipeManager.getAllRecipesFor(JeanBuildingRecipe.Type.INSTANCE);
        registration.addRecipes(JeanBuildingRecipeCategory.JEAN_BUILDING_TYPE, jeanRecipes);

        List<SewingMachineRecipe> sewingRecipes = recipeManager.getAllRecipesFor(SewingMachineRecipe.Type.INSTANCE);
        registration.addRecipes(SewingMachineRecipeCategory.SEWING_MACHINE_TYPE, sewingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(JeanBuildingStationScreen.class, 132, 43, 18, 10,
                JeanBuildingRecipeCategory.JEAN_BUILDING_TYPE);

        registration.addRecipeClickArea(SewingMachineStationScreen.class, 126, 42, 18, 10,
                SewingMachineRecipeCategory.SEWING_MACHINE_TYPE);
    }
}
