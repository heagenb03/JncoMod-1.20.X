package net.heagen.jncomod.entity.client;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.entity.custom.JacquesYaakovRevahEntity;
import net.heagen.jncomod.entity.custom.LuckyBuddhaEntity;
import net.heagen.jncomod.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class JacquesYaakovRevahRenderer extends MobRenderer<JacquesYaakovRevahEntity, JacquesYaakovRevahModel<JacquesYaakovRevahEntity>> {
    private static final ResourceLocation JACQUES_YAAKOV_REVAH_LOCATION = new ResourceLocation(JNCOMod.MOD_ID, "textures/entity/jacques_yaakov_revah.png");

    public JacquesYaakovRevahRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new JacquesYaakovRevahModel<>(pContext.bakeLayer(ModModelLayers.JACQUES_YAAKOV_REVAH_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(JacquesYaakovRevahEntity pEntity) {
        return JACQUES_YAAKOV_REVAH_LOCATION;
    }

}