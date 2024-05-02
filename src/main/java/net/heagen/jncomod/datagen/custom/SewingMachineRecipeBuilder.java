package net.heagen.jncomod.datagen.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.recipe.SewingMachineRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class SewingMachineRecipeBuilder implements RecipeBuilder {
    private final Item result;
    private final Ingredient magic_thread;
    private final Ingredient denim;
    private final Ingredient embroidery;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public SewingMachineRecipeBuilder(ItemLike magic_thread, ItemLike denim, ItemLike embroidery, ItemLike result, int count) {
        this.magic_thread = Ingredient.of(magic_thread);
        this.denim = Ingredient.of(denim);
        this.embroidery = Ingredient.of(embroidery);
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

        pFinishedRecipeConsumer.accept(new Result(pRecipeId, this.result, this.count, this.magic_thread, this.denim, this.embroidery,
                this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" + pRecipeId.getPath())));

    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final Ingredient magic_thread;
        private final Ingredient denim;
        private final Ingredient embroidery;
        private final int count;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, Item pResult, int pCount, Ingredient magic_thread, Ingredient denim, Ingredient embroidery, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
            this.id = pId;
            this.result = pResult;
            this.magic_thread = magic_thread;
            this.denim = denim;
            this.embroidery = embroidery;
            this.count = pCount;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(magic_thread.toJson());
            jsonArray.add(denim.toJson());
            jsonArray.add(embroidery.toJson());

            pJson.add("ingredients", jsonArray);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
            if (this.count > 1) {
                jsonObject.addProperty("count", this.count);
            }

            pJson.add("output", jsonObject);
        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(JNCOMod.MOD_ID,
                    ForgeRegistries.ITEMS.getKey(this.result).getPath() + "_from_sewing_machine");
        }

        @Override
        public RecipeSerializer<?> getType() {
            return SewingMachineRecipe.Serializer.INSTANCE;
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
