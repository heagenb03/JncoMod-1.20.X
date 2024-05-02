package net.heagen.jncomod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.entity.custom.LuckyBuddhaEntity;
import net.heagen.jncomod.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LuckyBuddhaRenderer extends MobRenderer<LuckyBuddhaEntity, LuckyBuddhaModel<LuckyBuddhaEntity>> {
    private static final ResourceLocation LUCKY_BUDDHA_LOCATION = new ResourceLocation(JNCOMod.MOD_ID, "textures/entity/lucky_buddha.png");

    public LuckyBuddhaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new LuckyBuddhaModel<>(pContext.bakeLayer(ModModelLayers.LUCKY_BUDDHA_LAYER)), 3f);
    }

    @Override
    public ResourceLocation getTextureLocation(LuckyBuddhaEntity pEntity) {
        return LUCKY_BUDDHA_LOCATION;
    }

}