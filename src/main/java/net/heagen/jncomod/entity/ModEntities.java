package net.heagen.jncomod.entity;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.entity.custom.FlameheadEntity;
import net.heagen.jncomod.entity.custom.HaimMiloRevahEntity;
import net.heagen.jncomod.entity.custom.JacquesYaakovRevahEntity;
import net.heagen.jncomod.entity.custom.LuckyBuddhaEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JNCOMod.MOD_ID);

    public static final RegistryObject<EntityType<LuckyBuddhaEntity>> LUCKY_BUDDHA = ENTITY_TYPES.register("lucky_buddha",
            () -> EntityType.Builder.of(LuckyBuddhaEntity::new, MobCategory.MONSTER)
                    .sized(4.3F, 7.35F).build("lucky_buddha"));

    public static final RegistryObject<EntityType<JacquesYaakovRevahEntity>> JACQUES_YAAKOV_REVAH = ENTITY_TYPES.register("jacques_yaakov_revah",
            () -> EntityType.Builder.of(JacquesYaakovRevahEntity::new, MobCategory.MONSTER)
                    .sized(0.9F, 1.95F).build("jacques_yaakov_revah"));

    public static final RegistryObject<EntityType<HaimMiloRevahEntity>> HAIM_MILO_REVAH = ENTITY_TYPES.register("haim_milo_revah",
            () -> EntityType.Builder.of(HaimMiloRevahEntity::new, MobCategory.MONSTER)
                    .sized(0.9F, 1.95F).build("haim_milo_revah"));

    public static final RegistryObject<EntityType<FlameheadEntity>> FLAMEHEAD = ENTITY_TYPES.register("flamehead",
            () -> EntityType.Builder.of(FlameheadEntity::new, MobCategory.MONSTER)
                    .sized(0.9F, 2F).build("flamehead"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
