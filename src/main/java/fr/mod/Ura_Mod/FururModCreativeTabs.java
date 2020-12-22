package fr.mod.Ura_Mod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class FururModCreativeTabs extends CreativeTabs {

    public FururModCreativeTabs(String label)
    {
        super(label);
    }
    @Override
    public Item getTabIconItem()
    {
        return Item.getItemFromBlock(Ura_ModMain.concrete_black);
    }
    @SideOnly(Side.CLIENT)
    public int func_151243_f()
    {
        return 0; // mettez ici votre metadata
    }
}