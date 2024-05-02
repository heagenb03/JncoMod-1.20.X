package net.heagen.jncomod.datagen.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.recipe.JeanBuildingRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.Consumer;

public class JeanBuildingRecipeBuilder implements RecipeBuilder {
    private final Item result;
    private final Ingredient thread;
    private final Ingredient waist;
    private final Ingredient front_left_pocket;
    private final Ingredient front_right_pocket;
    private final Ingredient back_left_pocket;
    private final Ingredient back_right_pocket;
    private final Ingredient left_pant;
    private final Ingredient right_pant;
    private final Ingredient left_opening;
    private final Ingredient right_opening;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public JeanBuildingRecipeBuilder(ItemLike thread, ItemLike waist, ItemLike front_left_pocket, ItemLike front_right_pocket, ItemLike back_left_pocket
                                    , ItemLike back_right_pocket, ItemLike left_pant, ItemLike right_pant, ItemLike left_opening, ItemLike right_opening, ItemLike result, int count) {
        this.thread = Ingredient.of(thread);
        this.waist = Ingredient.of(waist);
        this.front_left_pocket = Ingredient.of(front_left_pocket);
        this.front_right_pocket = Ingredient.of(front_right_pocket);
        this.back_left_pocket = Ingredient.of(back_left_pocket);
        this.back_right_pocket = Ingredient.of(back_right_pocket);
        this.left_pant = Ingredient.of(left_pant);
        this.right_pant = Ingredient.of(right_pant);
        this.left_opening = Ingredient.of(left_opening);
        this.right_opening = Ingredient.of(right_opening);
        this.result = result.asItem();
        this.count = count;
    }

    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);

        pFinishedRecipeConsumer.accept(new Result(pRecipeId, this.result, this.count, this.thread, this.waist, this.front_left_pocket, this.front_right_pocket, this.back_left_pocket, this.back_right_pocket
                , this.left_pant, this.right_pant, this.left_opening, this.right_opening,
                this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" + pRecipeId.getPath())));

    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final Ingredient thread;
        private final Ingredient waist;
        private final Ingredient front_left_pocket;
        private final Ingredient front_right_pocket;
        private final Ingredient back_left_pocket;
        private final Ingredient back_right_pocket;
        private final Ingredient left_pant;
        private final Ingredient right_pant;
        private final Ingredient left_opening;
        private final Ingredient right_opening;
        private final int count;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, Item pResult, int pCount, Ingredient thread, Ingredient waist, Ingredient front_left_pocket, Ingredient front_right_pocket,
                      Ingredient back_left_pocket, Ingredient back_right_pocket, Ingredient left_pant, Ingredient right_pant, Ingredient left_opening, Ingredient right_opening, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
            this.id = pId;
            this.result = pResult;
            this.thread = thread;
            this.waist = waist;
            this.front_left_pocket = front_left_pocket;
            this.front_right_pocket = front_right_pocket;
            this.back_left_pocket = back_left_pocket;
            this.back_right_pocket = back_right_pocket;
            this.left_pant = left_pant;
            this.right_pant = right_pant;
            this.left_opening = left_opening;
            this.right_opening = right_opening;
            this.count = pCount;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(thread.toJson());
            jsonArray.add(waist.toJson());
            jsonArray.add(front_left_pocket.toJson());
            jsonArray.add(front_right_pocket.toJson());
            jsonArray.add(back_left_pocket.toJson());
            jsonArray.add(back_right_pocket.toJson());
            jsonArray.add(left_pant.toJson());
            jsonArray.add(right_pant.toJson());
            jsonArray.add(left_opening.toJson());
            jsonArray.add(right_opening.toJson());

            pJson.add("ingredients", jsonArray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }

            pJson.add("output", jsonobject);
        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(JNCOMod.MOD_ID,
                    ForgeRegistries.ITEMS.getKey(this.result).getPath() + "_from_jean_building");
        }

        @Override
        public RecipeSerializer<?> getType() {
            return JeanBuildingRecipe.Serializer.INSTANCE;
        }

        @javax.annotation.Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @javax.annotation.Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
