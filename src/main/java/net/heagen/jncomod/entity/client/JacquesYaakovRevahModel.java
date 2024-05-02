package net.heagen.jncomod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.heagen.jncomod.entity.animations.ModAnimationDefinitions;
import net.heagen.jncomod.entity.custom.JacquesYaakovRevahEntity;
import net.heagen.jncomod.entity.custom.LuckyBuddhaEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class JacquesYaakovRevahModel<T extends JacquesYaakovRevahEntity> extends HierarchicalModel<T> {
	private final ModelPart jacques_yaakov_revah;
	private final ModelPart head;

	public JacquesYaakovRevahModel(ModelPart root) {
		this.jacques_yaakov_revah = root.getChild("jacques_yaakov_revah");
		this.head = root.getChild("jacques_yaakov_revah").getChild("body").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition jacques_yaakov_revah = partdefinition.addOrReplaceChild("jacques_yaakov_revah", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = jacques_yaakov_revah.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -22.0F, -1.0F, 8.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -30.0F, -3.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightleg = body.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(32, 0).addBox(-6.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -11.0F, 1.0F));

		PartDefinition leftleg = body.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(16, 31).addBox(-1.0F, 0.0F, -1.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, 0.0F));

		PartDefinition leftarm = body.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 31).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -22.0F, 1.0F));

		PartDefinition rightarm = body.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(24, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -22.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	private void applyHeadRotation(JacquesYaakovRevahEntity pEntity, float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -4.0F, 4.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -1.0F, 1.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 500F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 500F);
	}

	@Override
	public ModelPart root() {
		return jacques_yaakov_revah;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		jacques_yaakov_revah.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(T entity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(entity, pNetHeadYaw, pHeadPitch, pAgeInTicks);

		this.animateWalk(ModAnimationDefinitions.FOUNDER_BROTHERS_WALK, pLimbSwing, pLimbSwingAmount, 2f, 2.5f);
		this.animate(entity.idleAnimationState, ModAnimationDefinitions.FOUNDER_BROTHERS_IDLE, pAgeInTicks, 1f);
		this.animate(entity.attackAnimationState, ModAnimationDefinitions.JACQUES_MELEE_ATTACK, pAgeInTicks, 1f);
	}
}