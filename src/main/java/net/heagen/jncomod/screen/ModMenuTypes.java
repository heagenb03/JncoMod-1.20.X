package net.heagen.jncomod.screen;

import net.heagen.jncomod.JNCOMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, JNCOMod.MOD_ID);

    public static final RegistryObject<MenuType<JeanBuildingStationMenu>> JEAN_BUILDING_MENU =
            registerMenuType(JeanBuildingStationMenu::new, "jean_building_menu");

    public static final RegistryObject<MenuType<SewingMachineStationMenu>> SEWING_MACHINE_MENU =
            registerMenuType(SewingMachineStationMenu::new, "sewing_machine_menu");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
