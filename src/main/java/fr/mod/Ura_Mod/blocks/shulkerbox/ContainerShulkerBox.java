package fr.mod.Ura_Mod.blocks.shulkerbox;

import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerShulkerBox extends Container
{
    private final TileEntityShulkerBox tile;
    
    public ContainerShulkerBox(TileEntityShulkerBox tile, InventoryPlayer inventory) {
        this.tile = tile;
        tile.openInventory();
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new SlotShulker(tile, j+i*9, 8+j*18, 18+i*18));
            }
        }
        this.bindPlayerInventory(inventory);
    }
    
    private void bindPlayerInventory(InventoryPlayer inventory) {
        int i;
        for(i = 0; i<3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
 
        for(i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }
    
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack item = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);
        if(slot != null && slot.getHasStack()) {
            ItemStack slotstack = slot.getStack();
            item = slotstack.copy();
            
            if (item.getItem() == Item.getItemFromBlock(Ura_ModMain.shulker_box)) return null;
            
            if(slotIndex < this.tile.getSizeInventory()) {
                if(!this.mergeItemStack(slotstack, this.tile.getSizeInventory(), this.inventorySlots.size(), true))
                    return null;
            }
            else if(!this.mergeItemStack(slotstack, 0, this.tile.getSizeInventory(), false))
                return null;
            
            if(slotstack.stackSize == 0)
                slot.putStack((ItemStack)null);
            else
                slot.onSlotChanged();
        }
        return item;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tile.isUseableByPlayer(player);
    }
    
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        this.tile.closeInventory();
    }

}
