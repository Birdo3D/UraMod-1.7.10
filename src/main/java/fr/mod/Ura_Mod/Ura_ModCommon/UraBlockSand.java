package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class UraBlockSand extends BlockFalling {

    public UraBlockSand(Material material) {
        super(material);
    }
    public int damageDropped(int p_damageDropped_1_) {
        return p_damageDropped_1_;
    }

    }
