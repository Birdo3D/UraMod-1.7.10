package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInvisible extends Block {

    public BlockInvisible(Material material) {
        super(Material.rock);
        this.setResistance(-1F);
        this.setBlockName("invisible_barriere");
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(-1F);
        this.setBlockTextureName(Ura_ModMain.MODID+":invisible_block");
        this.setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        this.setCreativeTab(Ura_ModMain.UraModCreativeTabs);
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

}