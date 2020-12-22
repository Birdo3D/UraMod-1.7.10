package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import scala.reflect.internal.Trees;

public class cartography_table extends Block {

    private IIcon top, bottom, front;

    protected cartography_table(Material material) {
        super(material);
        this.setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    public void registerBlockIcons(IIconRegister iiconRegister)
    {
        this.blockIcon = iiconRegister.registerIcon(Ura_ModMain.MODID + ":cartography_table_side2");
        this.top = iiconRegister.registerIcon(Ura_ModMain.MODID + ":cartography_table_top");
        this.bottom = iiconRegister.registerIcon(Ura_ModMain.MODID + ":cartography_table_bottom");
        this.front = iiconRegister.registerIcon(Ura_ModMain.MODID + ":cartography_table_side1");
    }

    public IIcon getIcon(int side, int metadata)
    {
        if(side == 1)
        {
            return this.top;
        }
        else if(side == 0)
        {
            return this.bottom;
        }
        else if(side == 5)
        {
            return this.bottom;
        }
        else if(side == 3)
        {
            return this.bottom;
        }
        else if(side == 4)
        {
            return this.bottom;
        }
        return this.blockIcon;
    }
}