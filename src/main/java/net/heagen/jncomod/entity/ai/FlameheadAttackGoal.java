package net.heagen.jncomod.entity.ai;

import net.heagen.jncomod.entity.custom.FlameheadEntity;
import net.heagen.jncomod.entity.custom.JacquesYaakovRevahEntity;
import net.heagen.jncomod.item.ModArmorMaterials;
import net.heagen.jncomod.item.ModItems;
import net.heagen.jncomod.item.custom.DenimItem;
import net.heagen.jncomod.item.custom.ModArmorItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class FlameheadAttackGoal extends MeleeAttackGoal {
    private final FlameheadEntity entity;
    //Based off animation time
    private int attackDelay = 10; //When attack happens
    private int ticksUntilNextAttack = 10; //Until animation ends
    private boolean shouldCountTillNextAttack = false;

    public FlameheadAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        entity = ((FlameheadEntity) pMob);
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        if (!isWearingJNCOS(pEnemy)) {
            if(isEnemyWithinAttackDistance(pEnemy, pDistToEnemySqr)) {
                shouldCountTillNextAttack = true;

                if(isTimeToStartAttackAnimation()) {
                    entity.setAttacking(true);
                }

                if(isTimeToAttack()) {
                    this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                    this.mob.doHurtTarget(pEnemy);
                    performAttack(pEnemy);
                }
            } else {
                resetAttackCooldown();
                shouldCountTillNextAttack = false;
                entity.setAttacking(false);
                entity.attackAnimationTimeout = 0;
            }
        } else if (isWearingJNCOS(pEnemy)){
            entity.stopBeingAngry();
        }
    }

    private boolean isWearingJNCOS(LivingEntity pEntity) {
        for (ItemStack itemstack : pEntity.getArmorSlots()) {
            if (ModItems.makesFlameheadNeutral(itemstack)){
                return true;
            }
        }

        return false;
    }


    private boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
        return pDistToEnemySqr <= this.getAttackReachSqr(pEnemy);
    }

    private boolean selfIsAttacked(){
        return entity.hurt(Objects.requireNonNull(this.entity.getLastDamageSource()), 0);
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2); //end of animation time / when attack happens
    }

    @Override
    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    @Override
    public int getTicksUntilNextAttack() {
        return ticksUntilNextAttack;
    }

    protected void performAttack(LivingEntity pEnemy) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(pEnemy);
    }

    @Override
    public void start() {
        super.start();
        this.attackDelay = 10;
        this.ticksUntilNextAttack = 10;
    }

    @Override
    public void tick() {
        super.tick();
        if(shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }
}
