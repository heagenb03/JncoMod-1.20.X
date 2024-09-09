package net.heagen.jncomod.entity.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.heagen.jncomod.entity.ai.FlameheadAttackGoal;
import net.heagen.jncomod.entity.ai.LuckyBuddhaAttackGoal;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class FlameheadEntity extends Monster implements NeutralMob{
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(FlameheadEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;
    protected static final ImmutableList<SensorType<? extends Sensor<? super FlameheadEntity>>> SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.HURT_BY);
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.HURT_BY);

    public FlameheadEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 5;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 30F));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new FlameheadAttackGoal(this, 1.0D, true));

        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 25D)
                .add(Attributes.ATTACK_DAMAGE, 3D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.75f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f)
                .add(Attributes.FOLLOW_RANGE, 12D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.1D);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20; //Based off ticks (20) per animation time (second)
            attackAnimationState.start(this.tickCount);
        } else{
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    protected void updateWalkAnimation(float v) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(v * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.walkAnimation.update(f, 0.2F);
    }

    /**
    @Override
    protected Brain.Provider<FlameheadEntity> brainProvider() {
        return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> pDynamic) {
        return FlameheadAi.makeBrain(this, this.brainProvider().makeBrain(pDynamic));
    }

    @Override
    public Brain<FlameheadEntity> getBrain() {
        return (Brain<FlameheadEntity>)super.getBrain();
    }

     **/

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }


    @Override
    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    @Override
    public void setRemainingPersistentAngerTime(int pRemainingPersistentAngerTime) {
        this.remainingPersistentAngerTime = pRemainingPersistentAngerTime;
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID pPersistentAngerTarget) {
       this.persistentAngerTarget = pPersistentAngerTarget;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

}
