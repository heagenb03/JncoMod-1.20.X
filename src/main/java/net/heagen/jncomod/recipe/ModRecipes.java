package net.heagen.jncomod.recipe;

import net.heagen.jncomod.JNCOMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.rmi.registry.Registry;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, JNCOMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<JeanBuildingRecipe>> JEAN_BUILDING_SERIALIZER =
            SERIALIZERS.register("jean_building", () -> JeanBuildingRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<SewingMachineRecipe>> SEWING_MACHINE_SERIALIZER =
            SERIALIZERS.register("sewing_machine", () -> SewingMachineRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
