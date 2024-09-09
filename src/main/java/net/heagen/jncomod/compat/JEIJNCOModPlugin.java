package net.heagen.jncomod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IStackHelper;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.heagen.jncomod.recipe.JeanBuildingRecipe;
import net.heagen.jncomod.recipe.SewingMachineRecipe;
import net.heagen.jncomod.screen.*;
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

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(JeanBuildingStationMenu.class, ModMenuTypes.JEAN_BUILDING_MENU.get(), JeanBuildingRecipeCategory.JEAN_BUILDING_TYPE, 0, 9, 9, 36);
        registration.addRecipeTransferHandler(SewingMachineStationMenu.class, ModMenuTypes.SEWING_MACHINE_MENU.get(), SewingMachineRecipeCategory.SEWING_MACHINE_TYPE, 0, 2, 9, 36);
    }
}
