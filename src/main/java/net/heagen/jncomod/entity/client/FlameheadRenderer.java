package net.heagen.jncomod.entity.client;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.entity.custom.FlameheadEntity;
import net.heagen.jncomod.entity.custom.JacquesYaakovRevahEntity;
import net.heagen.jncomod.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FlameheadRenderer extends MobRenderer<FlameheadEntity, FlameheadModel<FlameheadEntity>> {
    private static final ResourceLocation FLAMEHEAD_LOCATION = new ResourceLocation(JNCOMod.MOD_ID, "textures/entity/flamehead.png");

    public FlameheadRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FlameheadModel<>(pContext.bakeLayer(ModModelLayers.FLAMEHEAD_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(FlameheadEntity pEntity) {
        return FLAMEHEAD_LOCATION;
    }

}