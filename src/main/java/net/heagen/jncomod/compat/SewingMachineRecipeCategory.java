package net.heagen.jncomod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.block.ModBlocks;
import net.heagen.jncomod.recipe.JeanBuildingRecipe;
import net.heagen.jncomod.recipe.SewingMachineRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class SewingMachineRecipeCategory implements IRecipeCategory<SewingMachineRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(JNCOMod.MOD_ID, "sewing_machine");
    public static final ResourceLocation TEXTURE = new ResourceLocation(JNCOMod.MOD_ID, "textures/gui/sewing_machine_station_gui.png");

    public static final RecipeType<SewingMachineRecipe> SEWING_MACHINE_TYPE =
            new RecipeType<>(UID, SewingMachineRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public SewingMachineRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 82);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.SEWING_MACHINE_STATION.get()));
    }

    @Override
    public RecipeType<SewingMachineRecipe> getRecipeType() {
        return SEWING_MACHINE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Sewing Machine Station");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SewingMachineRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 129, 6).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 73, 39).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 100, 39).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 148, 39).addItemStack(recipe.getResultItem(null));
    }
}
