package fr.uracraft.uramod.Blocks.FluidDisplay;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class FluidDisplayTileEntity extends TileEntity {
    String fluidName = "";

    boolean flowing = false;

    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        writeToNBT(nbtTag);
        return (Packet)new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }

    public void toggleFlowing() {
        this.flowing = !this.flowing;
        markDirty();
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public boolean flowing() {
        return this.flowing;
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
        this.worldObj.func_147479_m(this.xCoord, this.yCoord, this.zCoord);
    }

    public boolean canUpdate() {
        return false;
    }

    public String getFluidName() {
        return this.fluidName;
    }

    public void setFluidName(String fluidName) {
        this.fluidName = fluidName;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.fluidName = par1NBTTagCompound.getString("fluidName");
        if (this.fluidName.equals("empty"))
            this.fluidName = "";
        this.flowing = par1NBTTagCompound.getBoolean("flowing");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        if (this.fluidName.equals(""))
            this.fluidName = "empty";
        par1NBTTagCompound.setString("fluidName", this.fluidName);
        par1NBTTagCompound.setBoolean("flowing", this.flowing);
    }
}
