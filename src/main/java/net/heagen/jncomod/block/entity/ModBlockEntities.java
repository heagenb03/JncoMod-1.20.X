package net.heagen.jncomod.block.entity;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, JNCOMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<JeanBuildingStationBlockEntity>> JEAN_BUILDING_STATION_BE =
            BLOCK_ENTITIES.register("jean_building_station_block_entity", () ->
                    BlockEntityType.Builder.of(JeanBuildingStationBlockEntity::new,
                            ModBlocks.JEAN_BUILDING_STATION.get()).build(null));

    public static final RegistryObject<BlockEntityType<SewingMachineStationBlockEntity>> SEWING_MACHINE_STATION_BE =
            BLOCK_ENTITIES.register("sewing_machine_station_block_entity", () ->
                    BlockEntityType.Builder.of(SewingMachineStationBlockEntity::new,
                            ModBlocks.SEWING_MACHINE_STATION.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
