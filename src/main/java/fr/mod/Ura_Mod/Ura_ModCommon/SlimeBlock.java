package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class SlimeBlock extends Block{

    protected SlimeBlock(Material p_i45394_1_) {
        super(Material.clay);
        setHardness(0.0F);
        setBlockTextureName(Ura_ModMain.MODID + ":slime");
        //setStepSound(ModSounds.soundSlime);
        setBlockName("slime_block");
        setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - f, z + 1);
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 0;
    }

    public int damageDropped(int p_149692_1_)
    {
        return 1;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if(world.isRemote)
        {
            entity.motionY += 1;
            entity.fallDistance += 0D;
        }
    }
}