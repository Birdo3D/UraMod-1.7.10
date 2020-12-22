package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class AncientDebris extends Block {

    private IIcon top;

    public AncientDebris() {
        super(Material.rock);
        this.setResistance(1200F);
        this.setBlockName("ancient_debris");
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(30F);
        this.setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iiconRegister) {

        this.blockIcon = iiconRegister.registerIcon(Ura_ModMain.MODID + ":ancient_debris");
        this.top = iiconRegister.registerIcon(Ura_ModMain.MODID + ":ancient_debris_top");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        if (p_149691_1_ == 1) return this.top;
        if (p_149691_1_ == 0) return this.top;
        return this.blockIcon;
    }

}
