package net.heagen.jncomod.datagen;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.block.ModBlocks;
import net.heagen.jncomod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, JNCOMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.JEAN_BUILDING_STATION.get())
                .add(ModBlocks.SEWING_MACHINE_STATION.get())
                .add(ModBlocks.MAGIC_DUST_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE);
                //.add(ModBlocks.OAK_LOG_SPRAY_PAINT.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.JEAN_BUILDING_STATION.get())
                .add(ModBlocks.SEWING_MACHINE_STATION.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.MAGIC_DUST_ORE.get());
    }

    @Override
    public String getName() {
        return "Block Tags";
    }
}
