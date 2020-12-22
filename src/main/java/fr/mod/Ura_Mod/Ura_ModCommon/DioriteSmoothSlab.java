package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.IBlockAccess;

public class DioriteSmoothSlab extends GenericSlab {

    public DioriteSmoothSlab() {
        super(Material.rock, Ura_ModMain.diorite_smooth);
        setResistance(30);
        setHardness(2.0F);
        setBlockName("diorite_smooth_slab");
        setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    @Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return !(entity instanceof EntityDragon);
    }
}