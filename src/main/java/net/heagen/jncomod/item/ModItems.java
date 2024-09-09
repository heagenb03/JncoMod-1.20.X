package net.heagen.jncomod.item;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.entity.ModEntities;
import net.heagen.jncomod.item.custom.*;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraft.world.item.armortrim.TrimPatterns;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, JNCOMod.MOD_ID);

    public static final RegistryObject<Item> DENIM = ITEMS.register("denim",
            () -> new DenimItem(new Item.Properties()));

    public static final RegistryObject<Item> DARK_STONE_TWIN_CANNON_WAIST = ITEMS.register("dark_stone_twin_cannon_waist",
            () -> new DarkStoneTwinCannonWaistItem(new Item.Properties()));
    public static final RegistryObject<Item> DARK_STONE_TWIN_CANNON_LEFT_BACK_POCKET = ITEMS.register("dark_stone_twin_cannon_left_back_pocket",
            () -> new DarkStoneTwinCannonLeftBackPocketItem(new Item.Properties()));
    public static final RegistryObject<Item> DARK_STONE_TWIN_CANNON_RIGHT_BACK_POCKET = ITEMS.register("dark_stone_twin_cannon_right_back_pocket",
            () -> new DarkStoneTwinCannonRightBackPocketItem(new Item.Properties()));
    public static final RegistryObject<Item> DARK_STONE_TWIN_CANNON_LEFT_FRONT_POCKET = ITEMS.register("dark_stone_twin_cannon_left_front_pocket",
            () -> new DarkStoneTwinCannonLeftFrontPocketItem(new Item.Properties()));
    public static final RegistryObject<Item> DARK_STONE_TWIN_CANNON_RIGHT_FRONT_POCKET = ITEMS.register("dark_stone_twin_cannon_right_front_pocket",
            () -> new DarkStoneTwinCannonRightFrontPocketItem(new Item.Properties()));
    public static final RegistryObject<Item> DARK_STONE_TWIN_CANNON_LEFT_PANT_LEG = ITEMS.register("dark_stone_twin_cannon_left_pant_leg",
            () -> new DarkStoneTwinCannonLeftPantLegItem(new Item.Properties()));
    public static final RegistryObject<Item> DARK_STONE_TWIN_CANNON_RIGHT_PANT_LEG = ITEMS.register("dark_stone_twin_cannon_right_pant_leg",
            () -> new DarkStoneTwinCannonRightPantLegItem(new Item.Properties()));
    public static final RegistryObject<Item> DARK_STONE_TWIN_CANNON_LEFT_OPENING = ITEMS.register("dark_stone_twin_cannon_left_opening",
            () -> new DarkStoneTwinCannonLeftOpeningItem(new Item.Properties()));
    public static final RegistryObject<Item> DARK_STONE_TWIN_CANNON_RIGHT_OPENING = ITEMS.register("dark_stone_twin_cannon_right_opening",
            () -> new DarkStoneTwinCannonRightOpeningItem(new Item.Properties()));

    public static final RegistryObject<Item> DARK_STONE_TWIN_CANNON_LEGGINGS = ITEMS.register("dark_stone_twin_cannon_leggings",
            () -> new ModArmorItem(ModArmorMaterials.DARK_STONE_TWIN_CANNON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> DARK_STONE_TWIN_CANNON_BUDDHA_LEGGINGS = ITEMS.register("dark_stone_twin_cannon_buddha_leggings",
            () -> new ModArmorItem(ModArmorMaterials.DARK_STONE_TWIN_CANNON_BUDDHA, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> JET_BLACK_TWIN_CANNON_LEGGINGS = ITEMS.register("jet_black_twin_cannon_leggings",
            () -> new ModArmorItem(ModArmorMaterials.JET_BLACK_TWIN_CANNON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> JET_BLACK_TWIN_CANNON_BUDDHA_LEGGINGS = ITEMS.register("jet_black_twin_cannon_buddha_leggings",
            () -> new ModArmorItem(ModArmorMaterials.JET_BLACK_TWIN_CANNON_BUDDHA, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> THREAD_WHITE = ITEMS.register("thread_white",
            () -> new ThreadWhiteItem(new Item.Properties()));

    public static final RegistryObject<Item> MAGIC_THREAD_WHITE = ITEMS.register("magic_thread_white",
            () -> new MagicThreadWhiteItem(new Item.Properties()));

    public static final RegistryObject<Item> MAGIC_DUST = ITEMS.register("magic_dust",
            () -> new MagicDustItem(new Item.Properties()));

    public static final RegistryObject<Item> EMBROIDERY_BUDDHA = ITEMS.register("embroidery_buddha",
            () -> new EmbroideryBuddhaItem(new Item.Properties()));

    public static final RegistryObject<Item> LUCKY_BUDDHA_SPAWN_EGG = ITEMS.register("lucky_buddha_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.LUCKY_BUDDHA, 0x8888, 0x5555,
                    new Item.Properties()));

    public static final RegistryObject<Item> JACQUES_YAAKOV_REVAH_SPAWN_EGG = ITEMS.register("jacques_yaakov_revah_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.JACQUES_YAAKOV_REVAH, 0x1111, 0x7777,
                    new Item.Properties()));
    public static final RegistryObject<Item> HAIM_MILO_REVAH_SPAWN_EGG = ITEMS.register("haim_milo_revah_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.HAIM_MILO_REVAH, 0x9999, 0x1234,
                    new Item.Properties()));

    public static final RegistryObject<Item> FLAME_HEAD_SPAWN_EGG = ITEMS.register("flame_head_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.FLAMEHEAD, 0x2222, 0x3333,
                    new Item.Properties()));

    public static boolean makesFlameheadNeutral(ItemStack stack){
        return stack.getItem() instanceof ModArmorItem && ((ModArmorItem) stack.getItem()).getMaterial() ==
                ModArmorMaterials.DARK_STONE_TWIN_CANNON_BUDDHA
                || stack.getItem() instanceof ModArmorItem && ((ModArmorItem) stack.getItem()).getMaterial() ==
                ModArmorMaterials.DARK_STONE_TWIN_CANNON
                || stack.getItem() instanceof ModArmorItem && ((ModArmorItem) stack.getItem()).getMaterial() ==
                ModArmorMaterials.JET_BLACK_TWIN_CANNON
                || stack.getItem() instanceof ModArmorItem && ((ModArmorItem) stack.getItem()).getMaterial() ==
                ModArmorMaterials.JET_BLACK_TWIN_CANNON_BUDDHA;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}