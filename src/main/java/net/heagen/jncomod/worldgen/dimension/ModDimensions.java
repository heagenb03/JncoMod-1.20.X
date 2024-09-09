package net.heagen.jncomod.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions {
    public static final ResourceKey<LevelStem> JNCODIM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(JNCOMod.MOD_ID, "jncodim"));
    public static final ResourceKey<Level> JNCODIM_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(JNCOMod.MOD_ID, "jncodim"));
    public static final ResourceKey<DimensionType> JNCO_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(JNCOMod.MOD_ID, "jncodim_type"));

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(JNCO_DIM_TYPE, new DimensionType(
                OptionalLong.of(18000),
                true,
                false,
                false,
                false,
                1.0,
                false,
                false,
                -64,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0f,
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 15)));

    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        //OneBiome
        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(Biomes.BEACH)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));

        //Misode dimension generator for 7 climates parameters
        //MultipleBiomes
        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(Pair.of(
                                        Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.DESERTED_GROUNDS)),
                                Pair.of(Climate.parameters(0.8F, 0.0F, 0.75F, 0.0F, 0.0F, 0.05F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.BUDDHA_GROUNDS))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));
        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.JNCO_DIM_TYPE), noiseBasedChunkGenerator);

        context.register(JNCODIM_KEY, stem);
    }
}
