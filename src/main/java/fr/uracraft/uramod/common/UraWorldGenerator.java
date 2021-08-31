package fr.uracraft.uramod.common;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class UraWorldGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        generateOverworld(world, chunkX * 16, chunkZ * 16, random);
    }


    private void addOre(Block block, Block blockSpawn, Random random, World world, int posX, int posZ, int minY, int maxY, int minV, int maxV, int spawnChance) {
        for (int i = 0; i < spawnChance; i++) {
            int chunkSize = 16;
            int Xpos = posX + random.nextInt(chunkSize);
            int Ypos = minY + random.nextInt(maxY - minY);
            int Zpos = posZ + random.nextInt(chunkSize);

            new WorldGenMinable(block, maxV, blockSpawn).generate(world, random, Xpos, Ypos, Zpos);
        }
    }

    private void generateOverworld(World world, int i, int j, Random random) {

        //Ura
        addOre(UraBlocks.ura_ore, Blocks.stone, random, world, i, j, 0, 14, 1, 4, 2);

        //Neodymium
        addOre(UraBlocks.neodymium_ore, Blocks.stone, random, world, i, j, 0, 14, 4, 4, 1);
        addOre(Blocks.stone, UraBlocks.neodymium_ore, random, world, i, j, 0, 14, 2, 4, 100);

        //RandomOre
        addOre(UraBlocks.random_ore, Blocks.stone, random, world, i, j, 0, 25, 4, 4, 1);
        addOre(Blocks.stone, UraBlocks.random_ore, random, world, i, j, 0, 25, 3, 4, 100);

        //Silver
        addOre(UraBlocks.silver_ore, Blocks.stone, random, world, i, j, 0, 25, 1, 8, 3);
        int x1 = i + random.nextInt(16);
        int y1 = random.nextInt(128);
        int z1 = j + random.nextInt(16);
    }

}