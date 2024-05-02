package net.heagen.jncomod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.heagen.jncomod.entity.animations.ModAnimationDefinitions;
import net.heagen.jncomod.entity.custom.FlameheadEntity;
import net.heagen.jncomod.entity.custom.JacquesYaakovRevahEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class FlameheadModel<T extends FlameheadEntity> extends HierarchicalModel<T> {
	private final ModelPart flamehead;
	private final ModelPart head;

	public FlameheadModel(ModelPart root) {
		this.flamehead = root.getChild("flamehead");
		this.head = root.getChild("flamehead").getChild("body").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition flamehead = partdefinition.addOrReplaceChild("flamehead", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = flamehead.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -22.0F, -1.0F, 10.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightleg = body.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(25, 7).addBox(-5.0F, -3.0F, -5.0F, 5.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jean = rightleg.addOrReplaceChild("jean", CubeListBuilder.create().texOffs(0, 46).addBox(0.0F, -4.0F, -2.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(12, 58).addBox(5.0F, -3.0F, -2.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(44, 0).addBox(-5.0F, -12.0F, -1.0F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftleg = body.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(21, 22).addBox(0.0F, -3.0F, -5.0F, 5.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jean2 = leftleg.addOrReplaceChild("jean2", CubeListBuilder.create().texOffs(40, 19).addBox(-6.0F, -4.0F, -2.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(57, 56).addBox(-6.0F, -3.0F, -2.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(36, 34).addBox(0.0F, -12.0F, -1.0F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightarm = body.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(18, 34).addBox(-9.0F, -22.0F, -1.0F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftarm = body.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 31).addBox(-9.0F, -22.0F, -1.0F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(14.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-18.0F, -30.0F, -3.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(14.0F, 0.0F, 0.0F));

		PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(28, 47).addBox(-15.0F, -32.0F, -1.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(48, 39).addBox(-15.0F, -33.0F, 0.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(49, 26).addBox(-13.0F, -32.0F, 0.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(48, 48).addBox(-17.0F, -32.0F, 0.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 53).addBox(-13.0F, -31.0F, -1.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(57, 6).addBox(-11.0F, -31.0F, -1.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(40, 47).addBox(-10.0F, -30.0F, -1.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(25, 19).addBox(-18.0F, -30.0F, 5.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 0).addBox(-19.0F, -30.0F, -1.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(41, 56).addBox(-18.0F, -31.0F, -1.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(17, 50).addBox(-17.0F, -31.0F, -1.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	private void applyHeadRotation(FlameheadEntity pEntity, float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -4.0F, 4.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -1.0F, 1.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 500F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 500F);
	}

	@Override
	public ModelPart root() {
		return flamehead;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		flamehead.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(T entity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(entity, pNetHeadYaw, pHeadPitch, pAgeInTicks);

		this.animateWalk(ModAnimationDefinitions.FLAMEHEAD_WALK, pLimbSwing, pLimbSwingAmount, 2f, 2.5f);
		this.animate(entity.idleAnimationState, ModAnimationDefinitions.FLAMEHEAD_IDLE, pAgeInTicks, 1f);
		this.animate(entity.attackAnimationState, ModAnimationDefinitions.FLAMEHEAD_MELEE_ATTACK, pAgeInTicks, 1f);
	}
}