package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import scala.reflect.internal.Trees;

public class woodconverter extends Block {

    private IIcon top, bottom;

    protected woodconverter(Material material) {
        super(material);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public void registerBlockIcons(IIconRegister iiconRegister)
    {
        this.blockIcon = iiconRegister.registerIcon(Ura_ModMain.MODID + ":woodconv");
        this.top = iiconRegister.registerIcon(Ura_ModMain.MODID + ":woodconv_top");
        this.bottom = iiconRegister.registerIcon(Ura_ModMain.MODID + ":woodconv_bottom");
    }

    public IIcon getIcon(int side, int metadata)
    {
        if(side == 0)
        {
            return this.bottom;
        }
        else if(side == 1)
        {
            return this.top;
        }
        return this.blockIcon;
    }
}
