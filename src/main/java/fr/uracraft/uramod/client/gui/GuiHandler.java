package fr.uracraft.uramod.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import fr.uracraft.uramod.Blocks.wood_converter.ContainerWoodConverter;
import fr.uracraft.uramod.Blocks.wood_converter.GuiWoodConverter;
import fr.uracraft.uramod.Blocks.wood_converter.TileEntityWoodConverter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityWoodConverter)
            return new ContainerWoodConverter(player.inventory, (TileEntityWoodConverter) tileEntity);
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityWoodConverter)
            return new GuiWoodConverter((TileEntityWoodConverter) tileEntity, player.inventory);
        return null;
    }
}
