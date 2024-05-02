package net.heagen.jncomod.event;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.entity.ModEntities;
import net.heagen.jncomod.entity.client.*;
import net.heagen.jncomod.entity.custom.FlameheadEntity;
import net.heagen.jncomod.entity.custom.HaimMiloRevahEntity;
import net.heagen.jncomod.entity.custom.JacquesYaakovRevahEntity;
import net.heagen.jncomod.entity.custom.LuckyBuddhaEntity;
import net.heagen.jncomod.entity.layers.ModModelLayers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JNCOMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.LUCKY_BUDDHA_LAYER, LuckyBuddhaModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.JACQUES_YAAKOV_REVAH_LAYER, JacquesYaakovRevahModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.HAIM_MILO_REVAH_LAYER, HaimMiloRevahModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.FLAMEHEAD_LAYER, FlameheadModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.LUCKY_BUDDHA.get(), LuckyBuddhaEntity.createAttributes().build());
        event.put(ModEntities.JACQUES_YAAKOV_REVAH.get(), JacquesYaakovRevahEntity.createAttributes().build());
        event.put(ModEntities.HAIM_MILO_REVAH.get(), HaimMiloRevahEntity.createAttributes().build());
        event.put(ModEntities.FLAMEHEAD.get(), FlameheadEntity.createAttributes().build());
    }

}
