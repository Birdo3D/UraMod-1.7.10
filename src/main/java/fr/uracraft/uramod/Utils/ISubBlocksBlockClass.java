package fr.uracraft.uramod.Utils;

import net.minecraft.item.ItemBlock;

public class ISubBlocksBlockClass {

    public static interface ISubBlocksBlock {

        Class<? extends ItemBlock> getItemBlockClass();
    }
}