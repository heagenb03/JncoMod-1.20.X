package net.heagen.jncomod.util;

import net.heagen.jncomod.JNCOMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(JNCOMod.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Blocks{

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(JNCOMod.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Biomes{
        private static TagKey<Biome> tag(String pName) {
            return TagKey.create(Registries.BIOME, new ResourceLocation(JNCOMod.MOD_ID, pName));
        }

        public static final TagKey<Biome> MAGIC_DUST_ORE_BIOMES = tag("magic_dust_ore_biomes");
    }
}
