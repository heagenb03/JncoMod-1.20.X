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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class JeanBuildingRecipeCategory implements IRecipeCategory<JeanBuildingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(JNCOMod.MOD_ID, "jean_building");
    public static final ResourceLocation TEXTURE = new ResourceLocation(JNCOMod.MOD_ID, "textures/gui/jean_building_station_gui.png");

    public static final RecipeType<JeanBuildingRecipe> JEAN_BUILDING_TYPE =
            new RecipeType<>(UID, JeanBuildingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public JeanBuildingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 82);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.JEAN_BUILDING_STATION.get()));
    }

    @Override
    public RecipeType<JeanBuildingRecipe> getRecipeType() {
        return JEAN_BUILDING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Jean Building Station");
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
    public void setRecipe(IRecipeLayoutBuilder builder, JeanBuildingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 17, 17).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 88, 22).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 43, 9).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 133, 9).addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 67, 9).addIngredients(recipe.getIngredients().get(4));
        builder.addSlot(RecipeIngredientRole.INPUT, 109, 9).addIngredients(recipe.getIngredients().get(5));
        builder.addSlot(RecipeIngredientRole.INPUT, 67, 43).addIngredients(recipe.getIngredients().get(6));
        builder.addSlot(RecipeIngredientRole.INPUT, 109, 43).addIngredients(recipe.getIngredients().get(7));
        builder.addSlot(RecipeIngredientRole.INPUT, 60, 64).addIngredients(recipe.getIngredients().get(8));
        builder.addSlot(RecipeIngredientRole.INPUT, 116, 64).addIngredients(recipe.getIngredients().get(9));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 153, 40).addItemStack(recipe.getResultItem(null));
    }
}
