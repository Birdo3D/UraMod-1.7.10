package fr.mod.Ura_Mod.smithing_table;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.mod.Ura_Mod.Ura_ModCommon.UraFurnaceRecipe;
import fr.mod.Ura_Mod.iriduim_smithing_table.IriduimSmithingTableRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class SmithingTableTileEntity extends TileEntity implements IInventory {
    private ItemStack[] contents = new ItemStack[3]; //0, 1 et 2 sont les inputs et 3 est l'output

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.contents.length; ++i) //pour les slots
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
    public int getSizeInventory() { //Tout est dans le nom, retourne la taille de l'inventaire, pour notre bloc c'est quatre
        return this.contents.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex) { //Renvoie L'itemStack se trouvant dans le slot passé en argument
        return this.contents[slotIndex];
    }

    @Override //Comme dit plus haut, c'est expliqué dans le tutoriel de robin
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

    public ItemStack getStackInSlotOnClosing(int slotIndex) {
        if (this.contents[slotIndex] != null) {
            ItemStack itemstack = this.contents[slotIndex];
            this.contents[slotIndex] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void setInventorySlotContents(int slotIndex, ItemStack stack) {
        this.contents[slotIndex] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

    public String getInventoryName() { //J'ai décider qu'on ne pouvait pas mettre de nom custom
        return "tile.contnaier_smithing_table";
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }


    @SideOnly(Side.CLIENT)
    public boolean canSmeltItem() {
        if (this.contents[0] != null && this.contents[1] != null) {
            return SmithingTableRecipes.smelting().getSmeltingResult(new ItemStack[]{this.contents[0], this.contents[1]}) != null;
        }
        return false;
    }

    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return slot == 3 ? false : true;
    }


    private boolean canSmelt()
    {
        if ( this.contents[1] == null ||this.contents[0] == null ) //Si les trois premiers slots sont vides
        {
            return false; //On ne peut pas lancer le processus
        }
        else
        {


            ItemStack itemstack = SmithingTableRecipes.smelting().getSmeltingResult(new ItemStack[]{this.contents[0], this.contents[1]}); //Il y a une erreur ici, c'est normal, on y vient après (c'est pour les recettes)
            if (itemstack == null) return false; //rapport avec les recettes
            if (this.contents[2] == null) return true; //vérifications du slot d'output
        }
        return false;
    }
    public void updateEntity() //Méthode exécutée à chaque tick
    {
        if(canSmelt()){
            this.contents[2] = SmithingTableRecipes.smelting().getSmeltingResult(new ItemStack[]{this.contents[0], this.contents[1]});
        }
    }

    public boolean canCraft(ItemStack itemStack,ItemStack itemStack2){
        ItemStack itemstack = SmithingTableRecipes.smelting().getSmeltingResult(new ItemStack[]{itemStack, itemStack2}); //Il y a une erreur ici, c'est normal, on y vient après (c'est pour les recettes)
        if (itemstack != null) return true;
        return false;
    }
    public ItemStack getCraft(ItemStack itemStack,ItemStack itemStack2){
        ItemStack itemstack = SmithingTableRecipes.smelting().getSmeltingResult(new ItemStack[]{itemStack, itemStack2}); //Il y a une erreur ici, c'est normal, on y vient après (c'est pour les recettes)
        if (itemstack != null) return itemstack;
        return null;
    }



}
