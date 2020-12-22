package fr.mod.Ura_Mod.iriduim_smithing_table;

import fr.mod.Ura_Mod.wood_converter.TileEntityWoodConverter;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class IriduimSmitchingTableOutputSlot extends Slot {
    private final World theWorld;

    private final int blockPosX;

    private final int blockPosY;

    private final int blockPosZ;

    private final IriduimSmithingTableContnaier contnaier;

    /** The anvil this slot belongs to. */




    public IriduimSmitchingTableOutputSlot(IriduimSmithingTableContnaier iriduimSmithingTableContnaier,IInventory iInventory, int slotIndex, int slotX, int slotY, World world, int blockX, int blockY, int blockZ) {
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
        return false;
    }

    /**
     * Return whether this slot's stack can be taken from this slot.
     */


    @Override
    public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack) {
        System.out.println("pikkk");
if(!this.theWorld.isRemote && this.theWorld.getBlock(this.blockPosX, this.blockPosY, this.blockPosZ) instanceof IriduimSmithingTable){
    TileEntity tile = theWorld.getTileEntity(this.blockPosX, this.blockPosY, this.blockPosZ);
    if(tile instanceof IriduimSmithingTableTileEntity){
        IriduimSmithingTableTileEntity iriduimSmithingTableTileEntity = (IriduimSmithingTableTileEntity) tile;

        if(iriduimSmithingTableTileEntity.getStackInSlot(0) != null&& iriduimSmithingTableTileEntity.getStackInSlot(1) != null&& iriduimSmithingTableTileEntity.getStackInSlot(2) == null){
            if(iriduimSmithingTableTileEntity.getCraft(iriduimSmithingTableTileEntity.getStackInSlot(0),iriduimSmithingTableTileEntity.getStackInSlot(1))==itemStack) {
                iriduimSmithingTableTileEntity.setInventorySlotContents(0, null);

                    iriduimSmithingTableTileEntity.setInventorySlotContents(1, null);


            }
        }
contnaier.detectAndSendChanges();
    }
}
    }
}
