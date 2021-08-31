package fr.uracraft.uramod.Blocks.wood_converter;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerWoodConverter extends Container {
    TileEntityWoodConverter tile;

    public ContainerWoodConverter(InventoryPlayer inventoryPlayer, TileEntityWoodConverter tileEntityWoodConverter){
        this.tile = tileEntityWoodConverter;

        this.addSlotToContainer(new Slot(tileEntityWoodConverter, 0, 8, 36));
        this.addSlotToContainer(new SlotResult(tileEntityWoodConverter, 1, 148, 44));

        //player inventory
        int i;


        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }
    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return this.tile.isUseableByPlayer(entityPlayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slot_id)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slot_id);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slot_id == 2)
            {
                if (!this.mergeItemStack(itemstack1, 2, 38, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slot_id != 1 && slot_id != 0)
            {

            }
            else if (!this.mergeItemStack(itemstack1, 2, 38, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(entityPlayer, itemstack1);
        }

        return itemstack;
    }
}