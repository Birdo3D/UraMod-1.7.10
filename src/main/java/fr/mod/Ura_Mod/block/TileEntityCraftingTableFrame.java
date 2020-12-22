package fr.mod.Ura_Mod.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityCraftingTableFrame extends TileEntityStoneCraftingTable {

    private Block visualBlock;
    private int visualMetadata;

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        String blockModId = tagCompound.getString("VisualBlockModId");
        String blockName = tagCompound.getString("VisualBlockName");
        visualBlock = GameRegistry.findBlock(blockModId, blockName);
        visualMetadata = tagCompound.getByte("VisualMetadata");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        GameRegistry.UniqueIdentifier identifier = GameRegistry.findUniqueIdentifierFor(visualBlock);
        if(identifier != null) {
            tagCompound.setString("VisualBlockModId", identifier.modId);
            tagCompound.setString("VisualBlockName", identifier.name);
            tagCompound.setByte("VisualMetadata", (byte) visualMetadata);
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        GameRegistry.UniqueIdentifier identifier = GameRegistry.findUniqueIdentifierFor(visualBlock);
        if(identifier != null) {
            tagCompound.setString("VisualBlockModId", identifier.modId);
            tagCompound.setString("VisualBlockName", identifier.name);
            tagCompound.setByte("VisualMetadata", (byte) visualMetadata);
        }
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tagCompound);
    }


    public Block getVisualBlock() {
        return visualBlock;
    }

    public int getVisualMetadata() {
        return visualMetadata;
    }

    public void setVisualBlock(Block visualBlock, int metadata) {
        this.visualBlock = visualBlock;
        this.visualMetadata = metadata;
        markDirty();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
}
