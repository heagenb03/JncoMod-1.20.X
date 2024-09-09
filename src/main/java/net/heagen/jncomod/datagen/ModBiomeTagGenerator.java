package net.heagen.jncomod.datagen;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.util.ModTags;
import net.heagen.jncomod.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBiomeTagGenerator extends BiomeTagsProvider {
    public ModBiomeTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, JNCOMod.MOD_ID, existingFileHelper);
    }

    /**
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Biomes.MAGIC_DUST_ORE_BIOMES)
                .add(ModBiomes.DESERTED_GROUNDS)
                .add(ModBiomes.BUDDHA_GROUNDS);
    }
     **/
}
