package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class NeodymeOre extends Block {

    protected NeodymeOre(Material material) {
        super(material);
    }

    public Item getItemDropped(int metadata, Random random, int fortune)
    {
        return Ura_ModMain.NeodymeIngot;
    }
}