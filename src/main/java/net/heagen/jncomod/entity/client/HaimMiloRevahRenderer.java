package net.heagen.jncomod.entity.client;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.entity.custom.HaimMiloRevahEntity;
import net.heagen.jncomod.entity.custom.JacquesYaakovRevahEntity;
import net.heagen.jncomod.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HaimMiloRevahRenderer extends MobRenderer<HaimMiloRevahEntity, HaimMiloRevahModel<HaimMiloRevahEntity>> {
    private static final ResourceLocation HAIM_MILO_REVAH_LOCATION = new ResourceLocation(JNCOMod.MOD_ID, "textures/entity/haim_milo_revah.png");

    public HaimMiloRevahRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HaimMiloRevahModel<>(pContext.bakeLayer(ModModelLayers.HAIM_MILO_REVAH_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(HaimMiloRevahEntity pEntity) {
        return HAIM_MILO_REVAH_LOCATION;
    }

}