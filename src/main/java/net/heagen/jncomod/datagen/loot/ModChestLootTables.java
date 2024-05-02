package net.heagen.jncomod.datagen.loot;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.loot.packs.VanillaChestLoot;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class ModChestLootTables extends LootTableProvider implements LootTableSubProvider{
    private static final ResourceLocation DESERTED_RUINS_DUNGEON_TREASURE = new ResourceLocation(JNCOMod.MOD_ID, "chests/deserted_ruins_dungeon_treasure");

    public ModChestLootTables(PackOutput pOutput) {
        super(pOutput, Set.of(), VanillaLootTableProvider.create(pOutput).getTables());
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        builder.accept(DESERTED_RUINS_DUNGEON_TREASURE, desertedRuinsDungeonLootTable());
    }

    public static LootTable.Builder desertedRuinsDungeonLootTable() {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(
                UniformGenerator.between(3.0F, 6.0F))
                .add(LootItem.lootTableItem(Items.PAPER).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F))))
                .add(LootItem.lootTableItem(ModItems.MAGIC_DUST.get()).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
        );
    }
}
