package fr.mod.Ura_Mod.Ura_ModCommon;

        import cpw.mods.fml.common.registry.GameRegistry;
        import cpw.mods.fml.relauncher.Side;
        import cpw.mods.fml.relauncher.SideOnly;
        import net.minecraft.block.Block;
        import net.minecraft.block.BlockFurnace;
        import net.minecraft.block.material.Material;
        import net.minecraft.entity.player.EntityPlayer;
        import net.minecraft.init.Blocks;
        import net.minecraft.init.Items;
        import net.minecraft.inventory.IInventory;
        import net.minecraft.item.*;
        import net.minecraft.nbt.NBTTagCompound;
        import net.minecraft.nbt.NBTTagList;
        import net.minecraft.tileentity.TileEntity;

        import java.util.Random;

public class TileEntityUraFurnace extends TileEntity implements IInventory {

    private ItemStack[] contents = new ItemStack[4]; //0, 1 et 2 sont les inputs et 3 est l'output

    private int workingTime = 0; //Temps de cuisson actuel
    private int workingTimeNeeded = 200; //Temps de cuisson nécessaire
    public int BurningTimeLeft = 0;
    public boolean IsBurning = false;
    public static boolean Working = false;
    public int BurningTilmeAtStart = 0;

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
        compound.setShort("workingTime", (short) this.workingTime); //On les enregistrent en short
        compound.setShort("workingTimeNeeded", (short) this.workingTimeNeeded);
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

        this.workingTime = compound.getShort("workingTime"); //On lit nos valeurs
        this.workingTimeNeeded = compound.getShort("workingTimeNeeded");
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
        return "tile.contnaire_urafurnace";
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




    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return slot == 3 ? false : true;
    }

    public boolean isBurning() {
        return this.workingTime > 0;
    }
/*
    private boolean canSmelt() {
        boolean smelt = false;
        if (this.contents[0] == null || this.contents[1] == null) //Si les trois premiers slots sont vides
        {
            smelt = false; //On ne peut pas lancer le processus
        } else if (isItemFuel(this.contents[0]) || IsBurning) {
            if (!IsBurning) {
                this.BurningTimeLeft = getItemBurn_Time(this.contents[0]);
                this.contents[0].stackSize--;
            }
            ItemStack itemstack = UraFurnaceRecipe.smelting().getSmeltingResult(new ItemStack[]{this.contents[1], this.contents[3]}); //Il y a une erreur ici, c'est normal, on y vient après (c'est pour les recettes)
            if (itemstack == null) smelt = false; //rapport avec les recettes
            if (this.contents[3] == null) smelt = true; //vérifications du slot d'output
            if(this.contents[3] != null) {
                if (!this.contents[3].isItemEqual(itemstack)) smelt = false; //ici aussi
            }
            int result = contents[3].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.contents[3].getMaxStackSize(); //Et là aussi décidément
        }
        return smelt;
    }
    */
@SideOnly(Side.CLIENT)
public int getCookProgressScaled(int p_145953_1_)
{
    return this.BurningTimeLeft * p_145953_1_ / this.BurningTilmeAtStart;
}
    public  int Burning()
    {
        float burn = 14 / this.BurningTilmeAtStart * this.BurningTimeLeft;
        return Math.round(burn);
    }
private boolean canSmelt()
{
    if ( this.contents[1] == null || (this.BurningTimeLeft < 1 && !this.isItemFuel(this.contents[0]))) //Si les trois premiers slots sont vides
    {
        return false; //On ne peut pas lancer le processus
    }
    else
    {
        if (isItemFuel(this.contents[0]) || BurningTimeLeft > 0) {
            if (BurningTimeLeft < 1 && isItemFuel(this.contents[0])) {
                this.BurningTimeLeft = getItemBurn_Time(this.contents[0]);
                this.BurningTilmeAtStart = getItemBurn_Time(this.contents[0]);
                if(this.contents[0].getItem() == Items.lava_bucket){
                    this.contents[0] = new ItemStack(Items.bucket);
                }else {
                    --this.contents[0].stackSize;
                    if (this.contents[0].stackSize <= 0) {
                        this.contents[0] = null;
                    }
                }
            }
        }

        ItemStack itemstack = UraFurnaceRecipe.smelting().getSmeltingResult(new ItemStack[]{ this.contents[1]}); //Il y a une erreur ici, c'est normal, on y vient après (c'est pour les recettes)
        if (itemstack == null) return false; //rapport avec les recettes
        if (this.contents[3] == null) return true; //vérifications du slot d'output
        if (!this.contents[3].isItemEqual(itemstack)) return false; //ici aussi
        int result = contents[3].stackSize + itemstack.stackSize;
        return result <= getInventoryStackLimit() && result <= this.contents[3].getMaxStackSize(); //Et là aussi décidément
    }
}



    public void updateEntity() //Méthode exécutée à chaque tick
    {
        boolean flag = this.BurningTimeLeft > 0;
        boolean flag1 = false;

        if(this.BurningTimeLeft > 0){
            this.BurningTimeLeft--;
        }
        if(this.BurningTimeLeft < 1){
            this.IsBurning = false;
        }

        Working = isBurning();
        if (this.contents[2] != null) {

                if (this.contents[2].getItem() == Ura_ModMain.ura_upgarde) {
                    workingTimeNeeded = 75;
                } else if (this.contents[2].getItem() == Ura_ModMain.iridium_upgarde) {
                    workingTimeNeeded = 5;
                } else if (this.contents[2].getItem() == Ura_ModMain.neodyme_upgarde) {
                    workingTimeNeeded = 50;
                } else if (this.contents[2].getItem() == Ura_ModMain.diamond_upgarde) {
                    workingTimeNeeded = 150;
                } else if (this.contents[2].getItem() == Ura_ModMain.argent_upgarde) {
                    workingTimeNeeded = 125;
                }

        } else {
            workingTimeNeeded = 200;
        }
        if (this.isBurning() && this.canSmelt()) //Si on "cuit" et que notre recette et toujours bonne, on continue
        {
            ++this.workingTime; //incrémentation
        }
        if (this.canSmelt() && !this.isBurning()) //Si la recette est bonne mais qu'elle n'est toujours pas lancée, on la lance
        {
            this.workingTime = 1; //La méthode isBurning() renverra true maintenant (1>0)
        }
        if (this.canSmelt() && this.workingTime > this.workingTimeNeeded - 1) //Si on est arrivé au bout du temps de cuisson et que la recette est toujours bonne
        {

            this.workingTime = 0; //et on réinitialise le temps de cuisson
            this.smeltItem();
            if (this.contents[2] != null) {

                if (this.contents[2].getItemDamage() == this.contents[2].getMaxDamage()){
                    this.contents[2] = null;
                }else if (this.contents[2].getItem() == Ura_ModMain.ura_upgarde) {
                    this.contents[2].setItemDamage(this.contents[2].getItemDamage() + 1);
                } else if (this.contents[2].getItem() == Ura_ModMain.iridium_upgarde) {
                    this.contents[2].setItemDamage(this.contents[2].getItemDamage() + 1);
                } else if (this.contents[2].getItem() == Ura_ModMain.neodyme_upgarde) {
                    this.contents[2].setItemDamage(this.contents[2].getItemDamage() + 1);
                } else if (this.contents[2].getItem() == Ura_ModMain.diamond_upgarde){
                    this.contents[2].setItemDamage(this.contents[2].getItemDamage() + 1);
                } else if (this.contents[2].getItem() == Ura_ModMain.argent_upgarde){
                    this.contents[2].setItemDamage(this.contents[2].getItemDamage() + 1);
                }

            }
        }
            if (!this.canSmelt()) //Si la recette la recette n'est plus bonne
            {
                this.workingTime = 0; //le temps de cuisson est de 0
            }

        if (flag != this.BurningTimeLeft > 0)
        {
            flag1 = true;
            UraFurnace.updateFurnaceBlockState(this.BurningTimeLeft > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }

    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = UraFurnaceRecipe.smelting().getSmeltingResult(new ItemStack[]{ this.contents[1]}); //On récupère l'output de la recette
            if(itemstack.getItem() != Ura_ModMain.iridium_nugget && this.contents[1].getItem() != Ura_ModMain.ura_carrot ){
                if (this.contents[3] == null) //Si il y a rien dans le slot d'output
                {
                    this.contents[3] = itemstack.copy(); //On met directement l'ItemStack
                }
                else if (this.contents[3].getItem() == itemstack.getItem()) //Et si l'item que l'on veut est le même que celui qu'il y a déjà
                {
                    this.contents[3].stackSize += itemstack.stackSize; // Alors ont incrémente l'ItemStack
                }



            }else if(Random(300)==1&&itemstack.getItem() == Ura_ModMain.iridium_nugget){
                if (this.contents[3] == null) //Si il y a rien dans le slot d'output
                {
                    this.contents[3] = itemstack.copy(); //On met directement l'ItemStack
                }
                else if (this.contents[3].getItem() == itemstack.getItem()) //Et si l'item que l'on veut est le même que celui qu'il y a déjà
                {
                    this.contents[3].stackSize += itemstack.stackSize; // Alors ont incrémente l'ItemStack
                }

            }else if(Random(100)==1&&this.contents[1].getItem() == Ura_ModMain.ura_carrot){
                if (this.contents[3] == null) //Si il y a rien dans le slot d'output
                {
                    this.contents[3] = itemstack.copy(); //On met directement l'ItemStack
                }
                else if (this.contents[3].getItem() == itemstack.getItem()) //Et si l'item que l'on veut est le même que celui qu'il y a déjà
                {
                    this.contents[3].stackSize ++; // Alors ont incrémente l'ItemStack
                }
            }
            --this.contents[1].stackSize;


            if (this.contents[1].stackSize <= 0)
            {
                this.contents[1] = null;
            }

        }
    }
    static int Random(int max) {
        Random randomGen = new Random();
        return randomGen.nextInt(max);
    }
    @SideOnly(Side.CLIENT)
    public int getCookProgress()
    {
        return this.workingTime * 24 / this.workingTimeNeeded ;//41 correspond à la hauteur de la barre de progression car notre barre de progression se déroule de haut en bas
    }

    public static int getItemBurn_Time(ItemStack p_145952_0_)
    {
        if (p_145952_0_ == null)
        {
            return 0;
        }
        else
        {
            Item item = p_145952_0_.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab)
                {
                    return 150;
                }

                if (block.getMaterial() == Material.wood)
                {
                    return 300;
                }

                if (block == Blocks.coal_block)
                {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return GameRegistry.getFuelValue(p_145952_0_);
        }
    }

    public static boolean isItemFuel(ItemStack p_145954_0_)
    {
        /**
         * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
         * fuel
         */
        return getItemBurn_Time(p_145954_0_) > 0;
    }
}
