package net.heagen.jncomod.datagen;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.block.ModBlocks;
import net.heagen.jncomod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JNCOMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.THREAD_WHITE);
        simpleItem(ModItems.MAGIC_THREAD_WHITE);

        simpleItem(ModItems.MAGIC_DUST);

        simpleItem(ModItems.EMBROIDERY_BUDDHA);

        simpleItem(ModItems.DENIM);

        simpleItem(ModItems.DARK_STONE_TWIN_CANNON_WAIST);
        simpleItem(ModItems.DARK_STONE_TWIN_CANNON_LEFT_FRONT_POCKET);
        simpleItem(ModItems.DARK_STONE_TWIN_CANNON_RIGHT_FRONT_POCKET);
        simpleItem(ModItems.DARK_STONE_TWIN_CANNON_LEFT_BACK_POCKET);
        simpleItem(ModItems.DARK_STONE_TWIN_CANNON_RIGHT_BACK_POCKET);
        simpleItem(ModItems.DARK_STONE_TWIN_CANNON_LEFT_PANT_LEG);
        simpleItem(ModItems.DARK_STONE_TWIN_CANNON_RIGHT_PANT_LEG);
        simpleItem(ModItems.DARK_STONE_TWIN_CANNON_LEFT_OPENING);
        simpleItem(ModItems.DARK_STONE_TWIN_CANNON_RIGHT_OPENING);

        simpleItem(ModItems.DARK_STONE_TWIN_CANNON_LEGGINGS);
        simpleItem(ModItems.DARK_STONE_TWIN_CANNON_BUDDHA_LEGGINGS);
        simpleItem(ModItems.JET_BLACK_TWIN_CANNON_LEGGINGS);
        simpleItem(ModItems.JET_BLACK_TWIN_CANNON_BUDDHA_LEGGINGS);

        complexBlock(ModBlocks.JEAN_BUILDING_STATION.get());
        complexBlock(ModBlocks.SEWING_MACHINE_STATION.get());

        withExistingParent(ModItems.LUCKY_BUDDHA_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.JACQUES_YAAKOV_REVAH_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.HAIM_MILO_REVAH_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.FLAME_HEAD_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    private ItemModelBuilder complexBlock(Block block) {
        return withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), new ResourceLocation(JNCOMod.MOD_ID,
                "block/" + ForgeRegistries.BLOCKS.getKey(block).getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
            new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(JNCOMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
