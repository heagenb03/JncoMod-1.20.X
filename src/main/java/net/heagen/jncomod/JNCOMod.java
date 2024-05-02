package net.heagen.jncomod;

import com.mojang.logging.LogUtils;
import net.heagen.jncomod.block.ModBlocks;
//import net.heagen.jncomod.block.entity.ModBlockEntities;
import net.heagen.jncomod.block.entity.ModBlockEntities;
import net.heagen.jncomod.effect.ModEffects;
import net.heagen.jncomod.entity.ModEntities;
import net.heagen.jncomod.entity.client.*;
import net.heagen.jncomod.item.ModCreativeModTabs;
import net.heagen.jncomod.item.ModItems;
import net.heagen.jncomod.painting.ModPaintings;
import net.heagen.jncomod.recipe.ModRecipes;
import net.heagen.jncomod.screen.JeanBuildingStationScreen;
import net.heagen.jncomod.screen.ModMenuTypes;
import net.heagen.jncomod.screen.SewingMachineStationScreen;
import net.heagen.jncomod.worldgen.biome.ModTerraBlenderAPI;
import net.heagen.jncomod.worldgen.biome.surface.ModSurfaceRules;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(JNCOMod.MOD_ID)
public class JNCOMod {
    public static final String MOD_ID = "jncomod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public JNCOMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);

        ModPaintings.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        ModEffects.register(modEventBus);

        ModEntities.register(modEventBus);

        ModTerraBlenderAPI.registerRegions();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.EMBROIDERY_BUDDHA);
            event.accept(ModItems.THREAD_WHITE);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            MenuScreens.register(ModMenuTypes.JEAN_BUILDING_MENU.get(), JeanBuildingStationScreen::new);
            MenuScreens.register(ModMenuTypes.SEWING_MACHINE_MENU.get(), SewingMachineStationScreen::new);

            EntityRenderers.register(ModEntities.LUCKY_BUDDHA.get(), LuckyBuddhaRenderer::new);
            EntityRenderers.register(ModEntities.JACQUES_YAAKOV_REVAH.get(), JacquesYaakovRevahRenderer::new);
            EntityRenderers.register(ModEntities.HAIM_MILO_REVAH.get(), HaimMiloRevahRenderer::new);
            EntityRenderers.register(ModEntities.FLAMEHEAD.get(), FlameheadRenderer::new);
        }
    }
}
