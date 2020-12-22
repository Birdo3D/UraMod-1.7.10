package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.IBlockAccess;

public class GraniteSmoothSlab extends GenericSlab {

    public GraniteSmoothSlab() {
        super(Material.rock, Ura_ModMain.granite_smooth);
        setResistance(30);
        setHardness(2.0F);
        setBlockName("granite_smooth_slab");
        setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    @Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return !(entity instanceof EntityDragon);
    }
}