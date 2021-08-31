package fr.uracraft.uramod.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class UraModCreativeTab extends CreativeTabs {

    public UraModCreativeTab(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return UraItems.ura_ingot;
    }

    @SideOnly(Side.CLIENT)
    public int func_151243_f() {
        return 0;
    }
}
