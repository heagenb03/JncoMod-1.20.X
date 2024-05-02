package net.heagen.jncomod.datagen;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> future,
                               CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, future, completableFuture, JNCOMod.MOD_ID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
    }
}
