package fr.mod.Ura_Mod.iriduim_smithing_table;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class IriduimSmitchingTableSlot extends Slot {
    private final World theWorld;

    private final int blockPosX;

    private final int blockPosY;

    private final int blockPosZ;

    private final IriduimSmithingTableContnaier contnaier;

    /** The anvil this slot belongs to. */


    public int getSlotStackLimit()
    {
        return 1;
    }

    public IriduimSmitchingTableSlot(IriduimSmithingTableContnaier iriduimSmithingTableContnaier, IInventory iInventory, int slotIndex, int slotX, int slotY, World world, int blockX, int blockY, int blockZ) {
        super(iInventory, slotIndex, slotX, slotY);
        this.contnaier = iriduimSmithingTableContnaier;
        this.theWorld = world;
        this.blockPosX = blockX;
        this.blockPosY = blockY;
        this.blockPosZ = blockZ;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return true;
    }

    /**
     * Return whether this slot's stack can be taken from this slot.
     */


    @Override
    public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack) {
if(!this.theWorld.isRemote && this.theWorld.getBlock(this.blockPosX, this.blockPosY, this.blockPosZ) instanceof IriduimSmithingTable){
    TileEntity tile = theWorld.getTileEntity(this.blockPosX, this.blockPosY, this.blockPosZ);
    if(tile instanceof IriduimSmithingTableTileEntity){
        IriduimSmithingTableTileEntity iriduimSmithingTableTileEntity = (IriduimSmithingTableTileEntity) tile;
     if(iriduimSmithingTableTileEntity.getStackInSlot(0) == null&& iriduimSmithingTableTileEntity.getStackInSlot(1) != null&& iriduimSmithingTableTileEntity.getStackInSlot(2) != null){
        if(iriduimSmithingTableTileEntity.canCraft(itemStack,iriduimSmithingTableTileEntity.getStackInSlot(1))) {
            iriduimSmithingTableTileEntity.setInventorySlotContents(2, null);
        }
     }
        if(iriduimSmithingTableTileEntity.getStackInSlot(0) != null&& iriduimSmithingTableTileEntity.getStackInSlot(1) == null&& iriduimSmithingTableTileEntity.getStackInSlot(2) != null){
            if(iriduimSmithingTableTileEntity.canCraft(iriduimSmithingTableTileEntity.getStackInSlot(0),itemStack)) {
                iriduimSmithingTableTileEntity.setInventorySlotContents(2, null);
            }
        }

contnaier.detectAndSendChanges();
    }
}
    }
}
