package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.common.network.IGuiHandler;
import fr.mod.Ura_Mod.UraChest.ContainerUraChest;
import fr.mod.Ura_Mod.UraChest.GuiUraChest;
import fr.mod.Ura_Mod.UraChest.TileEntityUraChest;
import fr.mod.Ura_Mod.block.TileEntityStoneCraftingTable;
import fr.mod.Ura_Mod.blocks.shulkerbox.ContainerShulkerBox;
import fr.mod.Ura_Mod.blocks.shulkerbox.GuiShulkerBox;
import fr.mod.Ura_Mod.blocks.shulkerbox.TileEntityShulkerBox;
import fr.mod.Ura_Mod.client.GuiCraftCrafting;
import fr.mod.Ura_Mod.client.GuiInventoryCrafting;
import fr.mod.Ura_Mod.container.ContainerInventoryCrafting;
import fr.mod.Ura_Mod.container.ContainerPortableCrafting;
import fr.mod.Ura_Mod.container.ContainerStoneCraftingTable;
import fr.mod.Ura_Mod.iriduim_smithing_table.IriduimSmithingTableContnaier;
import fr.mod.Ura_Mod.iriduim_smithing_table.IriduimSmithingTableGui;
import fr.mod.Ura_Mod.iriduim_smithing_table.IriduimSmithingTableTileEntity;
import fr.mod.Ura_Mod.items.backpack.Backpack;
import fr.mod.Ura_Mod.items.backpack.ContainerBackpack;
import fr.mod.Ura_Mod.items.backpack.GuiBackpack;
import fr.mod.Ura_Mod.items.backpack.InventoryBackpack;
import fr.mod.Ura_Mod.smithing_table.SmithingTableContnaier;
import fr.mod.Ura_Mod.smithing_table.SmithingTableGui;
import fr.mod.Ura_Mod.smithing_table.SmithingTableTileEntity;
import fr.mod.Ura_Mod.wood_converter.GuiWoodConverter;
import fr.mod.Ura_Mod.wood_converter.TileEntityWoodConverter;
import fr.mod.Ura_Mod.wood_converter.WoodConverterContnaier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_PORTABLE_CRAFTING = 1;
    public static final int GUI_INVENTORY_CRAFTING = 2;
    public static final int GUI_STONE_CRAFTING_TABLE = 3;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileEntityUraFurnace) {

            return new ContainerUraFurnace(entityPlayer.inventory,(TileEntityUraFurnace) tile);
        }
        if(tile instanceof TileEntityUraChest) {

            return new ContainerUraChest(entityPlayer.inventory,(TileEntityUraChest) tile);
        }
        if(tile instanceof TileEntityWoodConverter) {

            return new WoodConverterContnaier(entityPlayer.inventory,(TileEntityWoodConverter) tile);
        }
        if(tile instanceof IriduimSmithingTableTileEntity){
            return new IriduimSmithingTableContnaier(entityPlayer.inventory,(IriduimSmithingTableTileEntity)tile,world);
        }
        if(tile instanceof SmithingTableTileEntity){
            return new SmithingTableContnaier(entityPlayer.inventory,(SmithingTableTileEntity)tile,world);
        }
        if(tile instanceof TileEntityShulkerBox) {
            return new ContainerShulkerBox((TileEntityShulkerBox) tile, entityPlayer.inventory);
        }
        if (entityPlayer.getEquipmentInSlot(3) != null && (entityPlayer.getEquipmentInSlot(3).getItem() instanceof Backpack)) {
            return new ContainerBackpack(entityPlayer.inventory, new InventoryBackpack(entityPlayer.getEquipmentInSlot(3), 54));
        }

        if(id == GUI_PORTABLE_CRAFTING) {
            return new ContainerPortableCrafting(entityPlayer);
        } else if(id == GUI_INVENTORY_CRAFTING) {
            return new ContainerInventoryCrafting(entityPlayer);
        } else if(id == GUI_STONE_CRAFTING_TABLE) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityStoneCraftingTable) {
                return new ContainerStoneCraftingTable(entityPlayer, (TileEntityStoneCraftingTable) tileEntity);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileEntityUraFurnace) {

            return new GuiUraFurnace((TileEntityUraFurnace)tile,entityPlayer.inventory);
        }
        if(tile instanceof TileEntityUraChest) {

            return new GuiUraChest((TileEntityUraChest)tile,entityPlayer.inventory);
        }
        if(tile instanceof TileEntityWoodConverter) {

            return new GuiWoodConverter((TileEntityWoodConverter)tile,entityPlayer.inventory);
        }
        if(tile instanceof IriduimSmithingTableTileEntity){
            return new IriduimSmithingTableGui((IriduimSmithingTableTileEntity)tile,entityPlayer.inventory,world);
        }
        if(tile instanceof SmithingTableTileEntity){
            return new SmithingTableGui((SmithingTableTileEntity)tile,entityPlayer.inventory,world);
        }
        if(tile instanceof TileEntityShulkerBox) {
            return new GuiShulkerBox((TileEntityShulkerBox) tile, entityPlayer.inventory);
        }
        if (entityPlayer.getEquipmentInSlot(3) != null && (entityPlayer.getEquipmentInSlot(3).getItem() instanceof Backpack)) {
            return new GuiBackpack(entityPlayer.inventory, new InventoryBackpack(entityPlayer.getEquipmentInSlot(3), 54));
        }

        if(id == GUI_PORTABLE_CRAFTING) {
            return new GuiCraftCrafting(new ContainerPortableCrafting(entityPlayer));
        } else if(id == GUI_INVENTORY_CRAFTING) {
            return new GuiInventoryCrafting(new ContainerInventoryCrafting(entityPlayer));
        } else if(id == GUI_STONE_CRAFTING_TABLE) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityStoneCraftingTable) {
                return new GuiCraftCrafting(new ContainerStoneCraftingTable(entityPlayer, (TileEntityStoneCraftingTable) tileEntity));
            }
        }
        return null;
    }
}