package fr.mod.Ura_Mod.Ura_ModCommon;

import java.util.Random;
;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ChorusPlant extends Block {

    public ChorusPlant() {
        super(Material.wood);
        setHardness(0.5F);
        setStepSound(soundTypeWood);
        setBlockName("chorus_plant");
        setBlockTextureName(Ura_ModMain.MODID + ":chorus_plant");
        setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    @Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return !(entity instanceof EntityDragon);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
        if (neighbour == this)
            world.func_147480_a(x, y, z, true);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    protected boolean canSilkHarvest() {
        return false;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune) {
        return Ura_ModMain.chorus;
    }
}