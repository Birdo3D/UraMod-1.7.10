package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import scala.reflect.internal.Trees;

public class Pumpkin extends Block {

    private IIcon top, bottom;

    protected Pumpkin(Material material) {
        super(material);
        this.setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    public void registerBlockIcons(IIconRegister iiconRegister)
    {
        this.blockIcon = iiconRegister.registerIcon(Ura_ModMain.MODID + ":pumpkin");
        this.top = iiconRegister.registerIcon(Ura_ModMain.MODID + ":pumpkin_top");
        this.bottom = iiconRegister.registerIcon(Ura_ModMain.MODID + ":pumpkin_face");
    }

    public IIcon getIcon(int side, int metadata)
    {
        if(side == 1)
        {
            return this.top;
        }
        else if(side == 5)
        {
            return this.bottom;
        }
        return this.blockIcon;
    }
}
