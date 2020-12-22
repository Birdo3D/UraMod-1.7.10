package fr.mod.Ura_Mod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class AcceleroPlanter extends BlockBase {

    public AcceleroPlanter(Material material) {
        super(material);
        this.setTickRandomly(true);
    }

    public int tickRate(World world) {
        return 30;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!world.isRemote) {
            for(int xco = -8; xco <= 8; xco ++) {
                for(int zco = -8; zco <= 8; zco ++) {
                    if (!world.blockExists(x+xco, y + 1, z+zco)) return;

                    Block b1 = world.getBlock(x+xco, y + 1, z+zco);

                    // If the block above it is farm land (AKA tilled dirt)
                    if (b1 instanceof BlockFarmland) {
                        if (!world.blockExists(x+xco, y + 2, z+zco))
                            return;

                        // Get the block above that one
                        Block plant_block = world.getBlock(x+xco, y + 2, z+zco);

                        if (plant_block instanceof IGrowable) {
                            IGrowable igrowable = (IGrowable) plant_block;
                            plant_block.updateTick(world,x+xco, y + 2, z+zco,rand);
/*
                            if (igrowable.func_149851_a(world, x+xco, y + 2, z+zco, world.isRemote)) {
                                if (!world.isRemote) {
                                    if (igrowable.func_149852_a(world, world.rand, x+xco, y + 2, z+zco)) {
                                        igrowable.func_149853_b(world, world.rand, x+xco, y + 2, z+zco);
                                    }
                                }
                            }
                            */
                        }
                    }
                }
            }
        }
    }
}
    /*
    public int tickRate()
    {
        return 10;
    }

    public void updateTick(World world, int x, int y, int z, Random prng) {
        System.out.println("Update tick");
        if (!world.blockExists(x, y + 1, z)) return;

        Block b1 = world.getBlock(x, y + 1, z);

        // If the block above it is farm land (AKA tilled dirt)
        if (b1 instanceof BlockFarmland) {
            if (!world.blockExists(x, y + 2, z))
                return;

            // Get the block above that one
            Block plant_block = world.getBlock(x, y + 2, z);

            if (plant_block instanceof BlockCrops||plant_block instanceof IPlantable ||
                    plant_block instanceof IGrowable) {

                System.out.println("growing " + x +" "+ y +" "+ z);
                plant_block.updateTick(world, x, y + 2, z, prng);



            }
    }}
 //   public int tickRate(World p_149738_1_)
  //  {
     //   return 4;
   // }
    @Override
    public boolean hasTileEntity(int metadata) //Permet de savoir si le bloc a un TileEntity
    {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }

    /**
     * Returns whether or not this block is of a type that needs random ticking. Called for ref-counting purposes by
     * ExtendedBlockStorage in order to broadly cull a chunk from the random chunk update list for efficiency's sake.
     */


/*
    public void updateTick(World world, int x, int y, int z, Random prng) {
        System.out.println("Update tick");
        if (!world.blockExists(x, y + 1, z)) return;

        Block b1 = world.getBlock(x, y + 1, z);

        // If the block above it is farm land (AKA tilled dirt)
        if (b1 instanceof BlockFarmland) {
            if (!world.blockExists(x, y + 2, z))
                return;

            // Get the block above that one
            Block plant_block = world.getBlock(x, y + 2, z);

            if (plant_block instanceof BlockCrops||plant_block instanceof IPlantable ||
                    plant_block instanceof IGrowable) {
                System.out.println("growing " + x +" "+ y +" "+ z);
                plant_block.updateTick(world, x, y + 2, z, prng);
                plant_block.updateTick(world, x, y + 2, z, prng);
                plant_block.updateTick(world, x, y + 2, z, prng);
                plant_block.updateTick(world, x, y + 2, z, prng);
                plant_block.updateTick(world, x, y + 2, z, prng);
                plant_block.updateTick(world, x, y + 2, z, prng);
                plant_block.updateTick(world, x, y + 2, z, prng);


            }
        }else {
            System.out.println("Update tick but isn't on farmland");
        }
       /* else if (b1 instanceof BlockDirt ||
                b1 instanceof BlockGrass ||
                b1 instanceof BlockSand ||
                b1 instanceof BlockSoulSand)
        {
            if (!world.blockExists(x, y + 2, z))
                return;

            // Get the block above that one
            Block b2 = world.getBlock(x, y + 2, z);

            if (b2 instanceof BlockSapling || b2 instanceof BlockNetherWart)
            {
                b2.updateTick(world, x, y + 2, z, prng);
            }
            
            // Reeds and saplings are a bit different
            else if (b2 instanceof BlockReed || b2 instanceof BlockCactus)
            {
                System.out.println("Cactus tick a");
                for (int l = 1; world.blockExists(x, y + 1 + l, z) && l < 3; l++)
                {
                    System.out.println("Cactus tick b");
                    world.getBlock(x, y + 1 + l, z)
                            .updateTick(world, x, y + 1 + l, z, prng);
                }
            }
        }
        else
            b1.updateTick(world, x, y + 1, z, prng);
    }
*/

