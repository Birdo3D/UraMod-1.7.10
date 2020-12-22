package fr.mod.Ura_Mod.blocks.shulkerbox;

import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityShulkerBox extends TileEntity implements IInventory
{
    private ItemStack[] contents = new ItemStack[27];
    private String customName;

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.readFromNBTWithoutPos(compound);
    }

    public void readFromNBTWithoutPos(NBTTagCompound compound)
    {
        if(compound.hasKey("CustomName", Constants.NBT.TAG_STRING))
            this.customName = compound.getString("CustomName");

        NBTTagList nbttaglist = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        this.contents = new ItemStack[this.getSizeInventory()];
        for(int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbtitem = nbttaglist.getCompoundTagAt(i);
            int j = nbtitem.getByte("Slot") & 255;

            if(j >= 0 && j < this.contents.length)
            {
                this.contents[j] = ItemStack.loadItemStackFromNBT(nbtitem);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        this.writeToNBTWithoutPos(compound);
    }

    public void writeToNBTWithoutPos(NBTTagCompound compound)
    {
        if(this.hasCustomInventoryName())
            compound.setString("CustomName", this.customName);
        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < this.contents.length; ++i)
        {
            if(this.contents[i] != null)
            {
                NBTTagCompound nbtitem = new NBTTagCompound();
                nbtitem.setByte("Slot", (byte)i);
                this.contents[i].writeToNBT(nbtitem);
                nbttaglist.appendTag(nbtitem);
            }
        }
        compound.setTag("Items", nbttaglist);
    }

    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbtagcompound = new NBTTagCompound();
        this.writeToNBT(nbtagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbtagcompound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        this.readFromNBT(pkt.func_148857_g());
    }

    @Override
    public int getSizeInventory()
    {
        // TODO Auto-generated method stub
        return this.contents.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return this.contents[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int amount)
    {
        if(this.contents[slotIndex] != null)
        {
            ItemStack itemStack;
            if(this.contents[slotIndex].stackSize <= amount)
            {
                itemStack = this.contents[slotIndex];
                this.contents[slotIndex] = null;
            }
            else
            {
                itemStack = this.contents[slotIndex].splitStack(amount);
                if(this.contents[slotIndex].stackSize == 0)
                    this.contents[slotIndex] = null;
            }
            this.markDirty();
            return itemStack;
        }
        else
            return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        ItemStack stack = this.getStackInSlot(slotIndex);
        if(stack != null) this.contents[slotIndex] = null;
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack stack)
    {
        this.contents[slotIndex] = stack;
    }

    @Override
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.customName : "tile.shulkerbox.name";
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        // TODO Auto-generated method stub
        return this.customName != null && this.customName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit()
    {
        // TODO Auto-generated method stub
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        // TODO Auto-generated method stub
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory()
    {
    }

    @Override
    public void closeInventory()
    {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        // TODO Auto-generated method stub
        return stack.getItem() != Item.getItemFromBlock(Ura_ModMain.shulker_box);
    }
}
