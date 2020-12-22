package fr.mod.Ura_Mod;

import cpw.mods.fml.common.IWorldGenerator;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import fr.mod.Ura_Mod.xp.GenXpBush;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class WorldGeneratorUra implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
                generateOverworld(world, chunkX * 16, chunkZ * 16, random);
                //break;
    }


    private void addOre(Block block, Block blockSpawn, Random random, World world, int posX, int posZ, int minY, int maxY, int minV, int maxV, int spawnChance)
    {
        for(int i = 0; i < spawnChance; i++)
        {
            int chunkSize = 16;
            int Xpos = posX + random.nextInt(chunkSize);
            int Ypos = minY + random.nextInt(maxY - minY);
            int Zpos = posZ + random.nextInt(chunkSize);

            new WorldGenMinable(block, maxV, blockSpawn).generate(world, random, Xpos, Ypos, Zpos);
        }
    }

    private void generateOverworld(World world, int i, int j, Random random)
    {
        addOre(Ura_ModMain.UraOre, Blocks.stone, random, world, i, j, 0, 14, 1, 4, 2);
        addOre(Ura_ModMain.NeodymeOre,Blocks.stone,random,world,i,j,0,14,1,2,2);
        addOre(Ura_ModMain.ArgentOre, Blocks.stone, random, world, i, j, 0, 25, 1, 8, 3);
        addOre(Ura_ModMain.halloween_ore, Blocks.stone, random, world, i, j, 0, 14, 1, 4, 2);
        //addOre(Ura_ModMain.randomore, Blocks.stone, random, world, i, j, 0, 14, 1, 4, 2);
        //addOre(Ura_ModMain.ancient_debris,Blocks.stone,random,world,i,j,8,22,1,3,2);
        //addOre(Ura_ModMain.ancient_debris,Blocks.stone,random,world,i,j,8,119,1,2,2);
        addOre(Ura_ModMain.andesite,Blocks.stone,random,world,i,j,1,200,7,12,30);
        addOre(Ura_ModMain.diorite,Blocks.stone,random,world,i,j,1,200,7,12,30);
        addOre(Ura_ModMain.granite,Blocks.stone,random,world,i,j,1,200,7,12,30);
        int x1 = i + random.nextInt(16);
        int y1 = random.nextInt(128);
        int z1 = j + random.nextInt(16);
        if(world.getBiomeGenForCoords(i, j).equals(BiomeGenBase.taiga)||world.getBiomeGenForCoords(i, j).equals(BiomeGenBase.taigaHills)||world.getBiomeGenForCoords(i, j).equals(BiomeGenBase.megaTaiga)||world.getBiomeGenForCoords(i, j).equals(BiomeGenBase.coldTaiga)||world.getBiomeGenForCoords(i, j).equals(BiomeGenBase.coldTaigaHills)||world.getBiomeGenForCoords(i, j).equals(BiomeGenBase.megaTaigaHills)) {
            (new GenXpBush(Ura_ModMain.xp_bush)).generate(world, random, x1, y1, z1);
        }

    }

}

