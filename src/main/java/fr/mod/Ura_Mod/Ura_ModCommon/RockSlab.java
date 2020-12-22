package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RockSlab extends GenericSlab {

    public RockSlab() {
        super(Material.rock, Blocks.stone);
        setResistance(30);
        setHardness(2.0F);
        setBlockName("rock_slab");
        setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    @Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return !(entity instanceof EntityDragon);
    }
}