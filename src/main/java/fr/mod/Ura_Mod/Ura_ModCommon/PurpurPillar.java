package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import scala.reflect.internal.Trees;

public class PurpurPillar extends Block {

    private IIcon top, bottom;

    protected PurpurPillar(Material material) {
        super(material);
        this.setBlockName("purpur_pillar");
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(Block.soundTypePiston);
        this.setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    public void registerBlockIcons(IIconRegister iiconRegister)
    {
        this.blockIcon = iiconRegister.registerIcon(Ura_ModMain.MODID + ":purpur_pillar");
        this.top = iiconRegister.registerIcon(Ura_ModMain.MODID + ":purpur_pillar_top");
        this.bottom = iiconRegister.registerIcon(Ura_ModMain.MODID + ":purpur_pillar_top");
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
