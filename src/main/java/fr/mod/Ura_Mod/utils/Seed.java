package fr.mod.Ura_Mod.utils;

import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class Seed {

    public static Block getFarmType(Item item){
        if (item instanceof ItemSeeds){
            ItemSeeds seeds = (ItemSeeds)item;
            if(seeds == Ura_ModMain.iridium_seed){
                return Ura_ModMain.iridium_culture;
            }else if (seeds == Ura_ModMain.ura_seed){
                return Ura_ModMain.ura_culture;
            }else if(seeds == Items.wheat_seeds){
                return Blocks.wheat;
            }
        }
        return null;
    }
}
