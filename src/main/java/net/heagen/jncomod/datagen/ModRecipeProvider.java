package net.heagen.jncomod.datagen;

import net.heagen.jncomod.block.ModBlocks;
import net.heagen.jncomod.datagen.custom.JeanBuildingRecipeBuilder;
import net.heagen.jncomod.datagen.custom.SewingMachineRecipeBuilder;
import net.heagen.jncomod.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput){
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.THREAD_WHITE.get())
                .pattern("  S")
                .pattern(" s ")
                .pattern("S  ")
                .define('S', Ingredient.of(Items.STICK))
                .define('s', Ingredient.of(Items.STRING))
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().of(Items.STICK).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGIC_THREAD_WHITE.get())
                .pattern(" M ")
                .pattern("MTM")
                .pattern(" M ")
                .define('M', ModItems.MAGIC_DUST.get())
                .define('T', ModItems.THREAD_WHITE.get())
                .unlockedBy("has_magic_dust", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.MAGIC_DUST.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.JEAN_BUILDING_STATION.get())
                .pattern("AMA")
                .pattern("SDS")
                .pattern("OOO")
                .define('O', Ingredient.of(Items.OBSIDIAN))
                .define('D', Ingredient.of(Items.DIAMOND))
                .define('S', Ingredient.of(Items.STONE_BRICK_SLAB))
                .define('A', Ingredient.of(Items.AMETHYST_SHARD))
                .define('M', ModItems.MAGIC_DUST.get())

                .unlockedBy("has_magic_dust", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.MAGIC_DUST.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SEWING_MACHINE_STATION.get())
                .pattern("SSM")
                .pattern("N S")
                .pattern("III")
                .define('I', Ingredient.of(Items.IRON_BLOCK))
                .define('N', Ingredient.of(Items.IRON_NUGGET))
                .define('S', Ingredient.of(Items.STONE_BRICK_SLAB))
                .define('M', ModItems.MAGIC_DUST.get())
                .unlockedBy("has_magic_dust", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.MAGIC_DUST.get()).build()))
                .save(pWriter);

        new JeanBuildingRecipeBuilder(ModItems.THREAD_WHITE.get(), ModItems.DARK_STONE_TWIN_CANNON_WAIST.get(), ModItems.DARK_STONE_TWIN_CANNON_LEFT_FRONT_POCKET.get(),
                ModItems.DARK_STONE_TWIN_CANNON_RIGHT_FRONT_POCKET.get(), ModItems.DARK_STONE_TWIN_CANNON_LEFT_BACK_POCKET.get(),
                ModItems.DARK_STONE_TWIN_CANNON_RIGHT_BACK_POCKET.get(), ModItems.DARK_STONE_TWIN_CANNON_LEFT_PANT_LEG.get(),
                ModItems.DARK_STONE_TWIN_CANNON_RIGHT_PANT_LEG.get(), ModItems.DARK_STONE_TWIN_CANNON_LEFT_OPENING.get(),
                ModItems.DARK_STONE_TWIN_CANNON_RIGHT_OPENING.get(), ModItems.DARK_STONE_TWIN_CANNON_LEGGINGS.get(),1)
                .unlockedBy("has_denim", has(ModItems.DENIM.get())).save(pWriter);

        new SewingMachineRecipeBuilder(ModItems.MAGIC_THREAD_WHITE.get(), ModItems.DARK_STONE_TWIN_CANNON_LEGGINGS.get(), ModItems.EMBROIDERY_BUDDHA.get(), ModItems.DARK_STONE_TWIN_CANNON_BUDDHA_LEGGINGS.get(),1)
                .unlockedBy("has_denim", has(ModItems.DENIM.get())).save(pWriter);
        new SewingMachineRecipeBuilder(ModItems.MAGIC_THREAD_WHITE.get(), ModItems.JET_BLACK_TWIN_CANNON_LEGGINGS.get(), ModItems.EMBROIDERY_BUDDHA.get(), ModItems.JET_BLACK_TWIN_CANNON_BUDDHA_LEGGINGS.get(),1)
                .unlockedBy("has_denim", has(ModItems.DENIM.get())).save(pWriter);
    }
}
