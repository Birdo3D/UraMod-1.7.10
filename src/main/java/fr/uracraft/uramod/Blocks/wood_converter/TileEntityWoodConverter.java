package fr.uracraft.uramod.Blocks.wood_converter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWoodConverter extends TileEntity implements IInventory {

    private ItemStack[] contents = new ItemStack[3];


    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.contents.length; ++i)
        {
            if (this.contents[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.contents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        compound.setTag("Items", nbttaglist);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.contents = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) //Encore une fois pour les slots
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.contents.length) {
                this.contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }
    @Override
    public int getSizeInventory() {
        return this.contents.length;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return contents[p_70301_1_];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int amount) {
        if (this.contents[slotIndex] != null) {
            ItemStack itemstack;

            if (this.contents[slotIndex].stackSize <= amount) {
                itemstack = this.contents[slotIndex];
                this.contents[slotIndex] = null;
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.contents[slotIndex].splitStack(amount);

                if (this.contents[slotIndex].stackSize == 0) {
                    this.contents[slotIndex] = null;
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex) {
        if (this.contents[slotIndex] != null) {
            ItemStack itemstack = this.contents[slotIndex];
            this.contents[slotIndex] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack stack) {
        this.contents[slotIndex] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

    @Override
    public String getInventoryName() { //J'ai décider qu'on ne pouvait pas mettre de nom custom
        return "tile.container_wood_converter";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() { }

    @Override
    public void closeInventory() { }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {

        if(slot != 1) {
            return true;
        }

        return false;
    }

    public void onButtonAction(int id) {

        if (this.contents[0] != null && this.contents[1] == null) {
            switch (id) {
                case 200:
                    this.contents[1] = new ItemStack(Blocks.log, this.contents[0].stackSize);
                    this.contents[0] = null;
                    break;
                case 201:
                    this.contents[1] = new ItemStack(Blocks.log2, this.contents[0].stackSize);
                    this.contents[0] = null;
                    break;
                case 202:

                    this.contents[1] = new ItemStack(Blocks.log, this.contents[0].stackSize,1);
                    this.contents[0] = null;
                    break;
                case 203:

                    this.contents[1] = new ItemStack(Blocks.log, this.contents[0].stackSize,2);
                    this.contents[0] = null;
                    break;
                case 204:
                    this.contents[1] = new ItemStack(Blocks.log, this.contents[0].stackSize,3);
                    this.contents[0] = null;
                    break;
                case 205:
                    this.contents[1] = new ItemStack(Blocks.log2, this.contents[0].stackSize,1);
                    this.contents[0] = null;
                    break;

            }
        }
    }
}