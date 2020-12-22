package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class RandomOre extends Block {

    protected RandomOre(Material material) {
        super(material);
        this.setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        this.setBlockName("randomore");
        this.setBlockTextureName("uramod:randomore");
        this.setHardness(3.0F);
        this.setResistance(3.0F);
    }

    public Item getItemDropped(int metadata, Random random, int fortune)
    {
        return Ura_ModMain.randomoreitem;
    }
}