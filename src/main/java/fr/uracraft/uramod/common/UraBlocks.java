package fr.uracraft.uramod.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.uracraft.uramod.Blocks.*;
import fr.uracraft.uramod.Blocks.FluidDisplay.FluidDisplayBlock;
import fr.uracraft.uramod.Blocks.wood_converter.WoodConverter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;


public class UraBlocks {

    public static Block ura_ore;
    public static Block silver_ore;
    public static Block neodymium_ore;
    public static Block ura_block;
    public static Block silver_block;
    public static Block neodymium_block;
    public static Block blue_screen_block;
    public static Block red_screen_block;
    public static Block yellow_screen_block;
    public static Block green_screen_block;
    public static Block cave_block;
    public static Block obsidian_stairs;
    public static Block dirt_stairs;
    public static Block bedrock_stairs;
    public static Block fake_water_block;
    public static Block fake_lava_block;
    public static Block glowstone_liquide_block;
    public static Block fluid_display;
    public static Block money_dispenser;
    public static Block dirt_slab;
    public static Block bedrock_slab;
    public static Block obsidian_slab;
    public static Block silver_box;
    public static Block ura_box;
    public static Block neodymium_box;
    public static Block iridium_box;
    public static Block random_ore;
    public static Block wood_converter;

    public static void blocks(FMLPreInitializationEvent event){
        ura_ore= new UraOre();
        silver_ore= new SilverOre();
        neodymium_ore= new NeodymiumOre();
        ura_block= new UraBlock();
        silver_block= new SilverBlock();
        neodymium_block= new NeodymiumBlock();
        blue_screen_block= new ScreenBlock("blue");
        red_screen_block= new ScreenBlock("red");
        yellow_screen_block= new ScreenBlock("yellow");
        green_screen_block= new ScreenBlock("green");
        cave_block= new CaveBlock();
        obsidian_stairs= new ObsidianStairs();
        dirt_stairs= new DirtStairs();
        bedrock_stairs= new BedrockStairs();
        fake_water_block= new FakeWaterBlock();
        fake_lava_block= new FakeLavaBlock();
        glowstone_liquide_block= new GlowstoneLiquideBlock();
        fluid_display= new FluidDisplayBlock();
        money_dispenser= new MoneyDispenser();
        dirt_slab= new GenericSlab(Material.grass, Blocks.dirt, "dirt_slab", 0.5F, 0.5F, "shovel", 0);
        bedrock_slab= new GenericSlab(Material.rock, Blocks.bedrock, "bedrock_slab", 3600000F, -1F, null, 0);
        obsidian_slab= new GenericSlab(Material.rock, Blocks.obsidian, "obsidian_slab", 1200F, 50F, "pickaxe", 3);
        silver_box= new GenericBox("silver");
        ura_box= new GenericBox("ura");
        neodymium_box= new GenericBox("neodymium");
        iridium_box= new GenericBox("iridium");
        random_ore= new RandomOre();
        wood_converter= new WoodConverter();

        GameRegistry.registerBlock(wood_converter,"wood_converter");
        GameRegistry.registerBlock(ura_ore,"ura_ore");
        GameRegistry.registerBlock(silver_ore,"silver_ore");
        GameRegistry.registerBlock(neodymium_ore,"neodymium_ore");
        GameRegistry.registerBlock(ura_block,"ura_block");
        GameRegistry.registerBlock(silver_block,"silver_block");
        GameRegistry.registerBlock(neodymium_block,"neodymium_block");
        GameRegistry.registerBlock(blue_screen_block,"blue_screen_block");
        GameRegistry.registerBlock(red_screen_block,"red_screen_block");
        GameRegistry.registerBlock(yellow_screen_block,"yellow_screen_block");
        GameRegistry.registerBlock(green_screen_block,"green_screen_block");
        GameRegistry.registerBlock(cave_block,"cave_block");
        GameRegistry.registerBlock(obsidian_stairs,"obsidian_stairs");
        GameRegistry.registerBlock(dirt_stairs,"dirt_stairs");
        GameRegistry.registerBlock(bedrock_stairs,"bedrock_stairs");
        GameRegistry.registerBlock(fake_water_block,"fake_water_block");
        GameRegistry.registerBlock(fake_lava_block,"fake_lava_block");
        GameRegistry.registerBlock(glowstone_liquide_block,"glowstone_liquide_block");
        GameRegistry.registerBlock(fluid_display,"fluid_display");
        GameRegistry.registerBlock(money_dispenser,"money_dispenser");
        GameRegistry.registerBlock(dirt_slab,"dirt_slab");
        GameRegistry.registerBlock(bedrock_slab,"bedrock_slab");
        GameRegistry.registerBlock(obsidian_slab,"obsidian_slab");
        GameRegistry.registerBlock(silver_box,"silver_box");
        GameRegistry.registerBlock(ura_box,"ura_box");
        GameRegistry.registerBlock(neodymium_box,"neodymium_box");
        GameRegistry.registerBlock(iridium_box,"iridium_box");
        GameRegistry.registerBlock(random_ore,"random_ore");
    }
}