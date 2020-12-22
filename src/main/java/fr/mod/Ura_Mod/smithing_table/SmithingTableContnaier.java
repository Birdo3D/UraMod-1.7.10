package fr.mod.Ura_Mod.smithing_table;

import fr.mod.Ura_Mod.Ura_ModCommon.SlotResult;
import fr.mod.Ura_Mod.iriduim_smithing_table.IriduimSmitchingTableOutputSlot;
import fr.mod.Ura_Mod.iriduim_smithing_table.IriduimSmitchingTableSlot;
import fr.mod.Ura_Mod.iriduim_smithing_table.IriduimSmithingTableTileEntity;
import fr.mod.Ura_Mod.wood_converter.TileEntityWoodConverter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SmithingTableContnaier extends Container {
    SmithingTableTileEntity tile;

    public SmithingTableContnaier(InventoryPlayer inventoryPlayer, SmithingTableTileEntity iriduimSmithingTableTileEntity, World world){
        this.tile = iriduimSmithingTableTileEntity;

        this.addSlotToContainer(new SmithingTableSlot(this,iriduimSmithingTableTileEntity, 0, 27, 47,world,tile.xCoord,tile.yCoord,tile.zCoord));
        this.addSlotToContainer(new SmithingTableSlot(this,iriduimSmithingTableTileEntity, 1, 76, 47,world,tile.xCoord,tile.yCoord,tile.zCoord));
        this.addSlotToContainer(new SmithingTableOutputSlot(this,iriduimSmithingTableTileEntity, 2, 134, 47,world,tile.xCoord,tile.yCoord,tile.zCoord));

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