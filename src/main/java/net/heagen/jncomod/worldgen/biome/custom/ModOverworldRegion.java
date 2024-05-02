package net.heagen.jncomod.worldgen.biome.custom;

import com.mojang.datafixers.util.Pair;
import net.heagen.jncomod.worldgen.biome.ModBiomes;
import net.heagen.jncomod.worldgen.dimension.ModDimensions;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }
}
