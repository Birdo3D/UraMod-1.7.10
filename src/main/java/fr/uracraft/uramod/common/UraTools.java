package fr.uracraft.uramod.common;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class UraTools {

    public static Item.ToolMaterial ura_tools = EnumHelper.addToolMaterial("ura_tools", 5, 1800, 14.0F, 5.0F, 18);
    public static Item.ToolMaterial silver_tools = EnumHelper.addToolMaterial("silver_tools",4,1500,13.0F,4.0F,16);
    public static Item.ToolMaterial iridium_tools = EnumHelper.addToolMaterial("iridium_tools", 6, 2500, 17.0F, 7.0F, 15);
    public static Item.ToolMaterial hammer = EnumHelper.addToolMaterial("hammer",5,1800,1.5F,5.0F,17);
    public static Item.ToolMaterial iridium_excavator = EnumHelper.addToolMaterial("iridium_excavator",6, 2500, 17.0F, 7.0F, 15);
}
