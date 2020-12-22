package fr.mod.Ura_Mod.wood_converter;

import fr.mod.Ura_Mod.UraChest.TileEntityUraChest;
import fr.mod.Ura_Mod.Ura_ModCommon.SlotResult;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class WoodConverterContnaier extends Container {
    TileEntityWoodConverter tile;

    public WoodConverterContnaier(InventoryPlayer inventoryPlayer, TileEntityWoodConverter tileEntityWoodConverter){
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
    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return this.tile.isUseableByPlayer(p_75145_1_);
    }

    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ == 2)
            {
                if (!this.mergeItemStack(itemstack1, 2, 38, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ != 1 && p_82846_2_ != 0)
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

            slot.onPickupFromSlot(p_82846_1_, itemstack1);
        }

        return itemstack;
    }
}



