package net.heagen.jncomod.item;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.block.ModBlocks;
import net.heagen.jncomod.painting.ModPaintings;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JNCOMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> JNCO_TAB = CREATIVE_MOD_TABS.register("jnco_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EMBROIDERY_BUDDHA.get()))
                    .title(Component.translatable("creativetab.jnco_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.EMBROIDERY_BUDDHA.get());

                        pOutput.accept(ModItems.DARK_STONE_TWIN_CANNON_WAIST.get());
                        pOutput.accept(ModItems.DARK_STONE_TWIN_CANNON_LEFT_BACK_POCKET.get());
                        pOutput.accept(ModItems.DARK_STONE_TWIN_CANNON_RIGHT_BACK_POCKET.get());
                        pOutput.accept(ModItems.DARK_STONE_TWIN_CANNON_LEFT_FRONT_POCKET.get());
                        pOutput.accept(ModItems.DARK_STONE_TWIN_CANNON_RIGHT_FRONT_POCKET.get());
                        pOutput.accept(ModItems.DARK_STONE_TWIN_CANNON_LEFT_PANT_LEG.get());
                        pOutput.accept(ModItems.DARK_STONE_TWIN_CANNON_RIGHT_PANT_LEG.get());
                        pOutput.accept(ModItems.DARK_STONE_TWIN_CANNON_LEFT_OPENING.get());
                        pOutput.accept(ModItems.DARK_STONE_TWIN_CANNON_RIGHT_OPENING.get());

                        pOutput.accept(ModItems.DARK_STONE_TWIN_CANNON_LEGGINGS.get());
                        pOutput.accept(ModItems.DARK_STONE_TWIN_CANNON_BUDDHA_LEGGINGS.get());

                        pOutput.accept(ModItems.JET_BLACK_TWIN_CANNON_LEGGINGS.get());
                        pOutput.accept(ModItems.JET_BLACK_TWIN_CANNON_BUDDHA_LEGGINGS.get());

                        pOutput.accept(ModItems.THREAD_WHITE.get());
                        pOutput.accept(ModItems.MAGIC_THREAD_WHITE.get());

                        pOutput.accept(ModItems.MAGIC_DUST.get());

                        pOutput.accept(ModBlocks.MAGIC_DUST_ORE.get());

                        pOutput.accept(ModBlocks.JEAN_BUILDING_STATION.get());
                        pOutput.accept(ModBlocks.SEWING_MACHINE_STATION.get());

                        pOutput.accept(ModItems.LUCKY_BUDDHA_SPAWN_EGG.get());
                        pOutput.accept(ModItems.JACQUES_YAAKOV_REVAH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.HAIM_MILO_REVAH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.FLAME_HEAD_SPAWN_EGG.get());

                        pOutput.accept(ModBlocks.JNCO_PORTAL.get());

                        //pOutput.accept(ModBlocks.OAK_LOG_SPRAY_PAINT.get());
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }

}
