package net.heagen.jncomod.block.entity;

import com.google.errorprone.annotations.Var;
import com.mojang.serialization.Decoder;
import net.heagen.jncomod.item.ModItems;
import net.minecraft.world.item.crafting.Recipe;
import net.heagen.jncomod.recipe.JeanBuildingRecipe;
import net.heagen.jncomod.screen.JeanBuildingStationMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class JeanBuildingStationBlockEntity extends BlockEntity implements MenuProvider {
   private final ItemStackHandler itemHandler = new ItemStackHandler(11) {
       @Override
       protected void onContentsChanged(int slot) {
           setChanged();
       }

       @Override
       public boolean isItemValid(int slot, @NotNull ItemStack stack) {
           return switch (slot) {
               case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 -> true;
               case 10 -> false;
               default -> super.isItemValid(slot, stack);
           };
       }
   };

   private static final int THREAD_INPUT_SLOT = 0;
   private static final int DENIM_WAIST_INPUT_SLOT = 1;
   private static final int DENIM_LEFT_FRONT_POCKET_INPUT_SLOT = 2;
   private static final int DENIM_RIGHT_FRONT_POCKET_INPUT_SLOT = 3;
   private static final int DENIM_LEFT_BACK_POCKET_INPUT_SLOT = 4;
   private static final int DENIM_RIGHT_BACK_POCKET_INPUT_SLOT = 5;
   private static final int DENIM_LEFT_PANT_LEG_INPUT_SLOT = 6;
   private static final int DENIM_RIGHT_PANT_LEG_INPUT_SLOT = 7;
   private static final int DENIM_LEFT_LEG_OPENING_INPUT_SLOT = 8;
   private static final int DENIM_RIGHT_LEG_OPENING_INPUT_SLOT = 9;
   private static final int OUTPUT_SLOT = 10;

   private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

   protected final ContainerData data;
   private int progress = 0;
   private int maxProgress = 35;

    public JeanBuildingStationBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.JEAN_BUILDING_STATION_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> JeanBuildingStationBlockEntity.this.progress;
                    case 1 -> JeanBuildingStationBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> JeanBuildingStationBlockEntity.this.progress = pValue;
                    case 1 -> JeanBuildingStationBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Jean Building Station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new JeanBuildingStationMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("jean_building_station.progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("jean_building_station.progress");

    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {
        if(isOutputSlotEmptyOrReceivable() && hasRecipe() && isInsertSlotsEmpty()) {
            increaseCraftingProcess();
            setChanged(level, pPos, pState);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
                resetProgress();
            }
    }

    private void craftItem() {
        Optional<JeanBuildingRecipe> recipe = getCurrentRecipe();
        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());

        this.itemHandler.extractItem(THREAD_INPUT_SLOT, 1, false);
        this.itemHandler.extractItem(DENIM_WAIST_INPUT_SLOT,1, false);
        this.itemHandler.extractItem(DENIM_LEFT_FRONT_POCKET_INPUT_SLOT,1, false);
        this.itemHandler.extractItem(DENIM_RIGHT_FRONT_POCKET_INPUT_SLOT,1, false);
        this.itemHandler.extractItem(DENIM_LEFT_BACK_POCKET_INPUT_SLOT,1, false);
        this.itemHandler.extractItem(DENIM_RIGHT_BACK_POCKET_INPUT_SLOT,1, false);
        this.itemHandler.extractItem(DENIM_LEFT_PANT_LEG_INPUT_SLOT,1, false);
        this.itemHandler.extractItem(DENIM_RIGHT_PANT_LEG_INPUT_SLOT,1, false);
        this.itemHandler.extractItem(DENIM_LEFT_LEG_OPENING_INPUT_SLOT,1, false);
        this.itemHandler.extractItem(DENIM_RIGHT_LEG_OPENING_INPUT_SLOT,1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(resultItem.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + resultItem.getCount()));
    }


    private void resetProgress() {
        this.progress = 0;
    }

    private boolean hasProgressFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProcess() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<JeanBuildingRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {return false;}
        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertAmountIntoOutputSlot(resultItem.getCount()) &&
                canInsertItemIntoOutputSlot(resultItem.getItem());
    }

    private Optional<JeanBuildingRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());

        for (int i = 0; i < this.itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(JeanBuildingRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean isInsertSlotsEmpty() {
        for (int i = 0; i < this.itemHandler.getSlots() - 1; i++) {
            if (this.itemHandler.getStackInSlot(i).isEmpty()){
                return false;
            }
        }
        return true;
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize() >=
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count;
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}
