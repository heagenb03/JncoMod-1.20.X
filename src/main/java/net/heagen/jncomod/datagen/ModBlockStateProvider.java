package net.heagen.jncomod.datagen;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, JNCOMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //blockWithItem(ModBlocks.OAK_LOG_SPRAY_PAINT);
        blockWithItem(ModBlocks.MAGIC_DUST_ORE);
        blockWithItem(ModBlocks.JNCO_PORTAL);

        horizontalBlock(ModBlocks.JEAN_BUILDING_STATION.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/jean_building_station")));
        horizontalBlock(ModBlocks.SEWING_MACHINE_STATION.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/sewing_machine_station")));

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

}
