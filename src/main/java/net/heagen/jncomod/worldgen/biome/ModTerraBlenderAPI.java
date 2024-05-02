package net.heagen.jncomod.worldgen.biome;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.worldgen.biome.custom.ModOverworldRegion;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerraBlenderAPI {
    public static void registerRegions() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(JNCOMod.MOD_ID, "overworld"), 5));
    }
}
