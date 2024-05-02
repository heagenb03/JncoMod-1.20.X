package net.heagen.jncomod.block;

import net.heagen.jncomod.JNCOMod;
import net.heagen.jncomod.block.custom.JNCOPortalBlock;
import net.heagen.jncomod.block.custom.JeanBuildingStationBlock;
import net.heagen.jncomod.block.custom.MagicDustOreBlock;
import net.heagen.jncomod.block.custom.SewingMachineStationBlock;
import net.heagen.jncomod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, JNCOMod.MOD_ID);

    public static final RegistryObject<Block> MAGIC_DUST_ORE = registerBlock("magic_dust_ore",
            () -> new MagicDustOreBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)
                    .strength(2f).requiresCorrectToolForDrops()));

    //public static final RegistryObject<Block> OAK_LOG_SPRAY_PAINT = registerBlock("oak_log_spray_paint",
            //() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> JEAN_BUILDING_STATION = registerBlock("jean_building_station",
            () -> new JeanBuildingStationBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

    public static final RegistryObject<Block> SEWING_MACHINE_STATION = registerBlock("sewing_machine_station",
            () -> new SewingMachineStationBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

    public static final RegistryObject<Block> JNCO_PORTAL = registerBlock("jnco_portal",
            () -> new JNCOPortalBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_PORTAL).noLootTable().noOcclusion().noCollission()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
