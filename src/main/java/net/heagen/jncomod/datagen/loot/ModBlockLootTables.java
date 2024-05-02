package net.heagen.jncomod.datagen.loot;

import net.heagen.jncomod.block.ModBlocks;
import net.heagen.jncomod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        //this.dropSelf(ModBlocks.OAK_LOG_SPRAY_PAINT.get());
        this.dropSelf(ModBlocks.JEAN_BUILDING_STATION.get());
        this.dropSelf(ModBlocks.SEWING_MACHINE_STATION.get());

        this.add(ModBlocks.MAGIC_DUST_ORE.get(),
                block -> createOreDrop(ModBlocks.MAGIC_DUST_ORE.get(), ModItems.MAGIC_DUST.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
