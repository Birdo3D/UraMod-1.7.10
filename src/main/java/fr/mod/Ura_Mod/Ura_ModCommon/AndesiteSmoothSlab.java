package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.IBlockAccess;

public class AndesiteSmoothSlab extends GenericSlab {

    public AndesiteSmoothSlab() {
        super(Material.rock, Ura_ModMain.andesite_smooth);
        setResistance(30);
        setHardness(2.0F);
        setBlockName("andesite_smooth_slab");
        setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    @Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return !(entity instanceof EntityDragon);
    }
}