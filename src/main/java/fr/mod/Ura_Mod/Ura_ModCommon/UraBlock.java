package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import scala.reflect.internal.Trees;

public class UraBlock extends Block {

    protected UraBlock(Material material) {
        super(material);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
}
