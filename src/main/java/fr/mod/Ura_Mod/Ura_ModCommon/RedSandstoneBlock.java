package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import scala.reflect.internal.Trees;

public class RedSandstoneBlock extends Block {

    private IIcon top, bottom;

    protected RedSandstoneBlock(Material material) {
        super(material);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public void registerBlockIcons(IIconRegister iiconRegister)
    {
        this.blockIcon = iiconRegister.registerIcon(Ura_ModMain.MODID + ":red_sandstone");
        this.top = iiconRegister.registerIcon(Ura_ModMain.MODID + ":red_sandstone_top");
        this.bottom = iiconRegister.registerIcon(Ura_ModMain.MODID + ":red_sandstone_bottom");
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
