package fr.mod.Ura_Mod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class UramodCreativeTabs extends CreativeTabs {

        public UramodCreativeTabs(String label)
        {
            super(label);
        }
    @Override
    public Item getTabIconItem()
    {
        return Item.getItemFromBlock(Ura_ModMain.UraOre);
    }
    @SideOnly(Side.CLIENT)
    public int func_151243_f()
    {
        return 0; // mettez ici votre metadata
    }
    }

