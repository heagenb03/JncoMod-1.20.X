package net.heagen.jncomod.entity.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.heagen.jncomod.entity.ModEntities;
import net.heagen.jncomod.entity.custom.FlameheadEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.schedule.Activity;

/**
public class FlameheadAi implements BehaviorControl {

    public static Brain<?> makeBrain(FlameheadEntity flameheadEntity, Brain<FlameheadEntity> pBrain) {
        initRetreatActivity(pBrain);
        pBrain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        pBrain.setDefaultActivity(Activity.IDLE);
        pBrain.useDefaultActivity();
        return pBrain;
    }

    private static void initRetreatActivity(Brain<FlameheadEntity> pBrain) {
        pBrain.addActivityAndRemoveMemoryWhenStopped(Activity.AVOID, 10, ImmutableList.of(SetWalkTargetAwayFrom
                        .entity(MemoryModuleType.AVOID_TARGET, 1.0F, 12, true),
                createIdleLookBehaviors(), createIdleMovementBehaviors(), EraseMemoryIf.create(FlameheadAi::wantsToStopFleeing,
                        MemoryModuleType.AVOID_TARGET)), MemoryModuleType.AVOID_TARGET);
    }

    private static RunOne<LivingEntity> createIdleLookBehaviors() {
        return new RunOne<>(ImmutableList.<Pair<? extends BehaviorControl<? super LivingEntity>, Integer>>builder().addAll(createLookBehaviors()).add(Pair.of(new DoNothing(30, 60), 1)).build());
    }

    private static ImmutableList<Pair<OneShot<LivingEntity>, Integer>> createLookBehaviors() {
        return ImmutableList.of(Pair.of(SetEntityLookTarget.create(EntityType.PLAYER, 8.0F), 1), Pair.of(SetEntityLookTarget.create(ModEntities.FLAMEHEAD.get(), 8.0F), 1), Pair.of(SetEntityLookTarget.create(8.0F), 1));
    }

    private static RunOne<FlameheadEntity> createIdleMovementBehaviors() {
        return new RunOne<>(ImmutableList.of(Pair.of(RandomStroll.stroll(0.6F), 2), Pair.of(InteractWith.of(ModEntities.FLAMEHEAD.get(), 8, MemoryModuleType.INTERACTION_TARGET, 0.6F, 2), 2), Pair.of(new DoNothing(30, 60), 1)));
    }


    private static boolean wantsToStopFleeing(FlameheadEntity p_35009_) {
        Brain<FlameheadEntity> brain = p_35009_.getBrain();
        if (!brain.hasMemoryValue(MemoryModuleType.AVOID_TARGET)) {
            return true;
        } else {
            LivingEntity livingentity = brain.getMemory(MemoryModuleType.AVOID_TARGET).get();
            EntityType<?> entitytype = livingentity.getType();
        }
        return false;
    }

    @Override
    public Behavior.Status getStatus() {
        return null;
    }

    @Override
    public boolean tryStart(ServerLevel pLevel, LivingEntity pEntity, long pGameTime) {
        return false;
    }

    @Override
    public void tickOrStop(ServerLevel pLevel, LivingEntity pEntity, long pGameTime) {

    }

    @Override
    public void doStop(ServerLevel pLevel, LivingEntity pEntity, long pGameTime) {

    }

    @Override
    public String debugString() {
        return null;
    }
}
**/