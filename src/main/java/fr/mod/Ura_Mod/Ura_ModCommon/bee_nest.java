package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import scala.reflect.internal.Trees;

public class bee_nest extends Block {

    private IIcon top, bottom, front;

    protected bee_nest(Material material) {
        super(material);
        this.setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    public void registerBlockIcons(IIconRegister iiconRegister)
    {
        this.blockIcon = iiconRegister.registerIcon(Ura_ModMain.MODID + ":bee_nest");
        this.top = iiconRegister.registerIcon(Ura_ModMain.MODID + ":bee_nest_top");
        this.bottom = iiconRegister.registerIcon(Ura_ModMain.MODID + ":bee_nest_bottom");
        this.front = iiconRegister.registerIcon(Ura_ModMain.MODID + ":bee_nest_front");
    }

    public IIcon getIcon(int side, int metadata)
    {
        if(side == 1)
        {
            return this.top;
        }
        else if(side == 5)
        {
            return this.front;
        }
        else if(side == 0)
        {
            return this.bottom;
        }
        return this.blockIcon;
    }
}
