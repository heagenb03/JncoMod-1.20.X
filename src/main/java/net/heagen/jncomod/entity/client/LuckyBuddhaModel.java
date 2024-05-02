package net.heagen.jncomod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.heagen.jncomod.entity.animations.ModAnimationDefinitions;
import net.heagen.jncomod.entity.custom.LuckyBuddhaEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

import javax.swing.text.html.parser.Entity;

public class LuckyBuddhaModel<T extends LuckyBuddhaEntity> extends HierarchicalModel<T> {
	private final ModelPart buddha;
	private final ModelPart head;

	public LuckyBuddhaModel(ModelPart root) {
		this.buddha = root.getChild("buddha");
		this.head = root.getChild("buddha").getChild("body").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition buddha = partdefinition.addOrReplaceChild("buddha", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition body = buddha.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-33.0F, -44.0F, -32.0F, 73.0F, 37.0F, 55.0F, new CubeDeformation(0.0F))
				.texOffs(0, 92).addBox(-30.0F, -89.0F, -30.0F, 67.0F, 45.0F, 50.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 187).addBox(-14.0F, -123.0F, -22.0F, 34.0F, 34.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightarm = buddha.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(201, 0).addBox(37.0F, -89.0F, -23.0F, 15.0F, 27.0F, 28.0F, new CubeDeformation(0.0F))
				.texOffs(185, 138).addBox(40.0F, -63.0F, -23.0F, 12.0F, 8.0F, 49.0F, new CubeDeformation(0.0F))
				.texOffs(240, 73).addBox(39.0F, -66.0F, 12.0F, 14.0F, 3.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftarm = buddha.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(136, 195).addBox(-45.0F, -89.0F, -23.0F, 15.0F, 39.0F, 28.0F, new CubeDeformation(0.0F))
				.texOffs(184, 92).addBox(-45.0F, -50.0F, -19.0F, 14.0F, 6.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition feet = buddha.addOrReplaceChild("feet", CubeListBuilder.create().texOffs(222, 224).addBox(-27.0F, -24.0F, 23.0F, 16.0F, 17.0F, 19.0F, new CubeDeformation(0.0F))
				.texOffs(222, 195).addBox(12.0F, -17.0F, 23.0F, 22.0F, 10.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(LuckyBuddhaEntity entity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(entity, pNetHeadYaw, pHeadPitch, pAgeInTicks);

		this.animateWalk(ModAnimationDefinitions.LUCKY_BUDDHA_WALK, pLimbSwing, pLimbSwingAmount, 2f, 2.5f);
		this.animate(entity.idleAnimationState, ModAnimationDefinitions.LUCKY_BUDDHA_IDLE, pAgeInTicks, 1f);
		this.animate(entity.attackAnimationState, ModAnimationDefinitions.LUCKY_BUDDHA_MELEE_ATTACK, pAgeInTicks, 1f);
	}

	private void applyHeadRotation(LuckyBuddhaEntity pEntity, float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -4.0F, 4.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -1.0F, 1.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 500F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 500F);
	}

	@Override
	public ModelPart root() {
		return buddha;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		buddha.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}