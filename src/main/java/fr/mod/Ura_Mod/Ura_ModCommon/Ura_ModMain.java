package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.mod.Ura_Mod.*;
import fr.mod.Ura_Mod.Enchantement.CobbleBreakerEnchantment;
import fr.mod.Ura_Mod.Enchantement.Mending;
import fr.mod.Ura_Mod.Enchantement.TelekinesisEnchantement;
import fr.mod.Ura_Mod.Enchantement.FrostWalker;
import fr.mod.Ura_Mod.Upgrade.*;
import fr.mod.Ura_Mod.UraChest.TileEntityUraChest;
import fr.mod.Ura_Mod.UraChest.UraChest;
import fr.mod.Ura_Mod.armor.*;
import fr.mod.Ura_Mod.blocks.AcceleroPlanter;
import fr.mod.Ura_Mod.blocks.shulkerbox.ShulkerBox;
import fr.mod.Ura_Mod.blocks.shulkerbox.TileEntityShulkerBox;
import fr.mod.Ura_Mod.events.EventHandler;
import fr.mod.Ura_Mod.iriduim_smithing_table.IriduimSmithingTable;
import fr.mod.Ura_Mod.iriduim_smithing_table.IriduimSmithingTableTileEntity;
import fr.mod.Ura_Mod.items.SeedPlanter;
import fr.mod.Ura_Mod.items.backpack.Backpack;
import fr.mod.Ura_Mod.proxy.CommonProxy;
import fr.mod.Ura_Mod.smithing_table.SmithingTable;
import fr.mod.Ura_Mod.smithing_table.SmithingTableTileEntity;
import fr.mod.Ura_Mod.tools.*;
import fr.mod.Ura_Mod.wood_converter.TileEntityWoodConverter;
import fr.mod.Ura_Mod.wood_converter.WoodConverter;
import fr.mod.Ura_Mod.wood_converter.WoodConverterPackets;
import fr.mod.Ura_Mod.xp.XpBerry;
import fr.mod.Ura_Mod.xp.XpBush;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import net.minecraftforge.common.config.Configuration;
import java.awt.*;

@Mod(modid = "uramod", name = "Ura Mod", version = "2.0.0")

public class Ura_ModMain {
    @Mod.Instance("uramod")
    public static Ura_ModMain instance;
    public static final String MODID = "uramod";

    @SidedProxy(clientSide = "fr.mod.Ura_Mod.proxy.ClientProxy",
            serverSide = "fr.mod.Ura_Mod.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static Block UraOre;
    public static Block ArgentOre;
    public static Block NeodymeOre;
    public static Block GreenScreenBlock;
    public static Block BlueScreenBlock;
    public static Block RedScreenBlock;
    public static Block YellowScreenBlock;
    public static Item UraIngot;
    public static Item ArgentIngot;
    public static Item NeodymeIngot;
    public static Block CaveBlock;
    public static Item iridium_sword, iridium_pickaxe;
    public static Item argent_sword, argent_pickaxe, argent_axe, argent_shovel, argent_hoe;
    public static Item ura_sword, ura_pickaxe, ura_axe, ura_shovel, ura_hoe;
    public static Item ura_helmet, ura_chestPlate, ura_leggings, ura_boots;
    public static Item argent_helmet, argent_chestPlate, argent_leggings, argent_boots;
    public static Item iridium_helmet, iridium_chestPlate, iridium_leggings, iridium_boots;
    public static Item netherite_helmet, netherite_chestPlate, netherite_leggings, netherite_boots;
    public static Item netherite_hoe, netherite_axe, netherite_shovel, netherite_sword, netherite_pickaxe;
    public static Item netherite_ingot, netherite_scrap;
    public static Block netherite_block, ancient_debris;
    public static ItemArmor.ArmorMaterial ura_armor = EnumHelper.addArmorMaterial("ura_armor", 45, new int[] {3, 8, 6, 3}, 10);
    public static ItemArmor.ArmorMaterial travel_armor = EnumHelper.addArmorMaterial("travel_armor", 45, new int[] {1, 3, 2, 1}, 15);
    public static ItemArmor.ArmorMaterial argent_armor = EnumHelper.addArmorMaterial("argent_armor", 30, new int[] {2, 6, 5, 2}, 9);
    public static ItemArmor.ArmorMaterial iridium_armor = EnumHelper.addArmorMaterial("iridium_armor", 60, new int[] {3, 8, 6, 3}, 25);
    public static ItemArmor.ArmorMaterial netherite_armor = EnumHelper.addArmorMaterial("netherite_armor", 33, new int[] {2, 5, 4, 1}, 10);
    public static Item.ToolMaterial ura_tool = EnumHelper.addToolMaterial("ura_tool", 5, 1800, 14.0F, 5.0F, 18);
    public static Item.ToolMaterial iridium_tool = EnumHelper.addToolMaterial("iridium_tool", 6, 2500, 17.0F, 7.0F, 15);
    public static Item.ToolMaterial argent_tool = EnumHelper.addToolMaterial("argent_ore",4,1500,13.0F,4.0F,16);
    public static Item.ToolMaterial hamer_tool = EnumHelper.addToolMaterial("hamer",5,1800,1.5F,5.0F,17);
    public static Item.ToolMaterial netherite_tools = EnumHelper.addToolMaterial("netherite_tools", 3, 2031, 12.0F, 3.0F, 14);
    public static Item finder;
    public static CreativeTabs UraModCreativeTabs = new UramodCreativeTabs("ura_mod_creative_tabs");
    public static CreativeTabs FururModCreativeTabs = new FururModCreativeTabs("furur_mod_creative_tabs");
    private static Block concrete_black_powder;
    private static Block concrete_blue_powder;
    private static Block concrete_brown_powder;
    private static Block concrete_cyan_powder;
    private static Block concrete_gray_powder;
    private static Block concrete_green_powder;
    private static Block concrete_light_blue_powder;
    private static Block concrete_lime_powder;
    private static Block concrete_magenta_powder;
    private static Block concrete_orange_powder;
    private static Block concrete_pink_powder;
    private static Block concrete_purple_powder;
    private static Block concrete_silver_powder;
    private static Block concrete_red_powder;
    private static Block concrete_white_powder;
    private static Block concrete_yellow_powder;
    public static Block concrete_black;
    private static Block concrete_blue;
    private static Block concrete_brown;
    private static Block concrete_cyan;
    private static Block concrete_gray;
    private static Block concrete_green;
    private static Block concrete_light_blue;
    private static Block concrete_lime;
    private static Block concrete_magenta;
    private static Block concrete_orange;
    private static Block concrete_pink;
    private static Block concrete_purple;
    private static Block concrete_silver;
    private static Block concrete_red;
    private static Block concrete_white;
    private static Block concrete_yellow;
    public static Block obsi_stairs, dirt_stairs, bedrock_stairs;
    public static Block barrier_block;
    public static Item iridium_nugget, iridium_ingot;
    public static Block ura_block,neodyme_block,argent_block;
    public static Item ura_nugget,argent_nugget;
    public static Block prismarin,dark_prismarin,prismarin_bricks,sea_lanterne,coarse_dirt,red_sandstone,slime_block,invisible_barriere,end_rode,purpur_block,purpur_pillar,magma_block,nether_wart_block,red_nether_brick,bone_block,glazed_terracotta_black,glazed_terracotta_blue,glazed_terracotta_brown,glazed_terracotta_cyan,glazed_terracotta_grey,glazed_terracotta_green,glazed_terracotta_light_blue,glazed_terracotta_lime,glazed_terracotta_magenta,glazed_terracotta_orange,glazed_terracotta_pink,glazed_terracotta_red,glazed_terracotta_silver,glazed_terracotta_white,glazed_terracotta_yellow,bubble_coral_block,brain_coral_block,tube_coral_block,horn_coral_block,fire_coral_block,dead_tube_coral_block,dead_horn_coral_block,dead_fire_coral_block,dead_bubble_coral_block,dead_brain_coral_block,shulker_box,stripped_acacia_log,stripped_birch_log,stripped_dark_oak_log,stripped_jungle_log,stripped_oak_log,stripped_spruce_log,acacia_log,birch_log,dark_oak_log,jungle_log,oak_log,spruce_log,pumpkin;
    public static Block bee_nest,bee_hive;
    public static Block bedrock;
    public static Block urafurnace,urachest,acceleroplanter,wood_converter;
    public static Item ura_particle,hammer,ura_bow,jump_chestplate,scuba_helmet,farmer_boots,seed_planter,speed_leggings,hang_glider,xp_berry;
    public static Item chisel;
    public static Item prismarin_crystal,prismarin_fragment,crossbow,sweetberry,honey_jars,honeycombs;
    public static Block wet_sponge,armor_stand,iron_trapdoor,hay_block,bamboo,bush,bell,composter,scaffolding,campfire,smoke_house,blast_furnace,lantern,loom,desk,mapping_table,blacksmith_table,archery_table,stonecutter,barrel,honey_block,honeycomb_block;
    public static Block granite, andesite, diorite;
    public static Block granite_stairs, andesite_stairs, diorite_stairs;
    public static Block granite_smooth, andesite_smooth, diorite_smooth;
    public static Block granite_smooth_stairs, andesite_smooth_stairs, diorite_smooth_stairs;
    public static Block ender_passerelle, stucture_block;
    public static Block terre_labouree;
    public static Block chorus_plant, chrous_flower;
    public static Block dragon_head;
    public static Item bouclier;
    public static Item chorus, chorus_eclate;
    public static Item ender_crystal, elytres;
    public static Item fleche_spectrale, fleche_spatiale;
    public static Item soufle_dragon;
    public static Block Box01, Box02, Box04, Box05;
    public static Item Key01, Key02, Key04, Key05;
    public static Block tube_coral, brain_coral, bubble_coral, fire_coral, horn_coral;
    public static Block dead_tube_coral, dead_brain_coral, dead_bubble_coral, dead_fire_coral,dead_horn_coral;
    public static Block tube_coral_fan, brain_coral_fan, bubble_coral_fan, fire_coral_fan, horn_coral_fan;
    public static Block dead_tube_coral_fan, dead_brain_coral_fan, dead_bubble_coral_fan, dead_fire_coral_fan, dead_horn_coral_fan;
    public static Item betterave_seeds, soupe_betteraves, betterave;
    public static Block betterave_culture;
    public static Block stone_stairs,mossy_cut_stone_stairs,mossy_stone_stairs,smooth_sandstone_stairs,smooth_red_sandstone_stairs,smooth_quartz_stairs,red_nether_bricks_stairs,end_bricks_stairs;
    public static Item iridium_particle, argent_particle;
    public static Item backpack;
    public static Item blockturner;
    public static Block iridium_culture, ura_culture;
    public static Item iridium_seed, ura_seed;
    public static Item neodyme_upgarde, iridium_upgarde, diamond_upgarde, argent_upgarde, ura_upgarde;
    public static Block lit_urafurnace;
    public static Enchantment telekinesis, cobblebreakerentantment, mending, frostWalker;
    public static Item portalble_carfting_table;
    public static Item iridium_potato, ura_carrot;
    public static Block xp_bush;
    public static SimpleNetworkWrapper network;
    public static Block iriduim_smithing_table;
    public static Fluid fakewaterfluid, fakelavafluid, glowstonefluid;
    public static Item fakewaterbucket, fakelavabucket, glowstoneliquidebucket;
    public static Block fakewaterblock, fakelavablock, glowstoneliquideblock;
    public static Item claim_stick;
    public static Block Purpur_Stair;
    public static Block frosted_ice;
    public static Block andesite_slab,andesite_smooth_slab,diorite_slab,diorite_smooth_slab,granite_slab,granite_smooth_slab;
    public static Block rock_slab,purpur_slab;
    public static Block spurce_door, birch_door, jungle_door, acacia_door, dark_oak_door;
    public static Item spurce_door_item, birch_door_item, jungle_door_item, acacia_door_item, dark_oak_door_item;
    public static Block andesite_wall,diorite_wall,granite_wall,polished_andesite_wall,polished_diorite_wall,polished_granite_wall;
    public static Block spurce_fence, birch_fence, jungle_fence, acacia_fence, dark_oak_fence;
    public static Block spurce_fencegate, birch_fencegate, jungle_fencegate, acacia_fencegate, darkoak_fencegate;
    public static Block erysfoly_glass;
    public static Item dollar_one, dollar_five, dollar_ten, dollar_twenty, dollar_fifty;
    public static Block money_dispenser;
    public static Item candy, halloween_ingot, halloween_helmet, halloween_chestplate, halloween_leggings, halloween_boots, halloween_pickaxe, halloween_hoe, halloween_axe, halloween_shovel, halloween_sword, halloween_nugget;
    public static Block halloween_ore, halloween_block;
    public static ItemArmor.ArmorMaterial halloween_armor = EnumHelper.addArmorMaterial("armor", 33, new int[] {2, 5, 4, 1}, 1);
    public static Item.ToolMaterial halloween_tools = EnumHelper.addToolMaterial("halloween_tools", 5, 1800, 14.0F, 5.0F, 18);
    public static ItemArmor.ArmorMaterial creeper_armor = EnumHelper.addArmorMaterial("creeper_armor", 5, new int[] {1, 1, 1, 1}, 15);
    public static ItemArmor.ArmorMaterial zombie_armor = EnumHelper.addArmorMaterial("zombie_armor", 5, new int[] {1, 1, 1, 1}, 15);
    public static ItemArmor.ArmorMaterial skeleton_armor = EnumHelper.addArmorMaterial("skeleton_armor", 5, new int[] {1, 1, 1, 1}, 15);
    public static ItemArmor.ArmorMaterial witch_armor = EnumHelper.addArmorMaterial("witch_armor", 5, new int[] {1, 1, 1, 1}, 15);
    public static Item creeper_helmet, creeper_chestplate, creeper_leggings, creeper_boots;
    public static Item zombie_helmet, zombie_chestplate, zombie_leggings, zombie_boots;
    public static Item skeleton_helmet, skeleton_chestplate, skeleton_leggings, skeleton_boots;
    public static Item witch_helmet, witch_chestplate, witch_leggings, witch_boots;
    public static Block randomore;
    public static Item randomoreitem;
    public static boolean autoJump;
    public static Configuration cfg;

    //public static Block ura_anvil;
    //private static Block ender_bricks_wall, bricks_walls,prismarine_walls,cut_stone_walls,mossed_cut_stone_walls,sandstone_walls,red_sandstone_walls,nether_bricks_walls,red_nether_bricks_walls;
    //private static Block beds;
    //private static Block mossy_cut_stone_slab,mossy_stone_slab,cut_sandstone_slab,cut_red_sandstone_slab,smooth_sandstone_slab,smooth_red_sandstone_slab,smooth_quartz_slab,red_nether_bricks_slab,ender_bricks_slab;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //Diamond Armor Reforged
        ItemArmor.ArmorMaterial customDiam = EnumHelper.addArmorMaterial("CUSTOM_DIAMOND", 5, new int[]{2, 5, 4, 1}, 10);
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_helmet, customDiam, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_chestplate, customDiam, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_leggings, customDiam, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_boots, customDiam, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_helmet, 2, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_chestplate, 5, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_leggings, 4, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_boots, 1, "damageReduceAmount", "field_77879_b");

        //Iron Armor Reforged
        ItemArmor.ArmorMaterial customIron = EnumHelper.addArmorMaterial("CUSTOM_IRON", 5, new int[]{2, 5, 3, 1}, 9);
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_helmet, customIron, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_chestplate, customIron, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_leggings, customIron, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_boots, customIron, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_helmet, 2, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_chestplate, 5, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_leggings, 3, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_boots, 1, "damageReduceAmount", "field_77879_b");

        //Gold Armor Reforged
        ItemArmor.ArmorMaterial customGold = EnumHelper.addArmorMaterial("CUSTOM_GOLD", 5, new int[]{1, 2, 1, 1}, 25);
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_helmet, customGold, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_chestplate, customGold, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_leggings, customGold, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_boots, customGold, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_helmet, 1, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_chestplate, 2, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_leggings, 1, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_boots, 1, "damageReduceAmount", "field_77879_b");

        //Leather Armor Reforged
        ItemArmor.ArmorMaterial customLeather = EnumHelper.addArmorMaterial("CUSTOM_LEATHER", 5, new int[]{1, 1, 1, 1}, 15);
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_helmet, customLeather, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_chestplate, customLeather, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_leggings, customLeather, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_boots, customLeather, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_helmet, 1, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_chestplate, 1, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_leggings, 1, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_boots, 1, "damageReduceAmount", "field_77879_b");

        //Chain Armor Reforged
        ItemArmor.ArmorMaterial customChain = EnumHelper.addArmorMaterial("CUSTOM_CHAIN", 5, new int[]{1, 2, 2, 1}, 12);
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_helmet, customChain, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_chestplate, customChain, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_leggings, customChain, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_boots, customChain, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_helmet, 1, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_chestplate, 2, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_leggings, 2, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_boots, 1, "damageReduceAmount", "field_77879_b");

        cfg = new Configuration(event.getSuggestedConfigurationFile());
        try {
            cfg.load();
            autoJump = cfg.getBoolean("autoJump", cfg.CATEGORY_GENERAL, false, "True if autoJump is actived.");

        }
        catch(Exception ex) {
            event.getModLog().fatal("Failed to load configuration");
        }
        finally {
            if(cfg.hasChanged())
                cfg.save();
        }

        // Fluid
        fakewaterfluid = new Fluid("fakewater").setDensity(4000).setViscosity(500).setTemperature(286).setLuminosity(0).setUnlocalizedName("fakewater");
        FluidRegistry.registerFluid(fakewaterfluid);
        fakewaterfluid = FluidRegistry.getFluid("fakewater");

        fakelavafluid = new Fluid("fakelava").setDensity(4000).setViscosity(6000).setTemperature(286).setLuminosity(15).setUnlocalizedName("fakelava");
        FluidRegistry.registerFluid(fakelavafluid);
        fakelavafluid = FluidRegistry.getFluid("fakelava");

        glowstonefluid = new Fluid("glowstoneliquide").setDensity(4000).setViscosity(500).setTemperature(286).setLuminosity(15).setUnlocalizedName("glowstoneliquide");
        FluidRegistry.registerFluid(glowstonefluid);
        glowstonefluid = FluidRegistry.getFluid("glowstoneliquide");

        if(event.getSide().isClient()){
            NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        }

        network = NetworkRegistry.INSTANCE.newSimpleChannel("WoodConverter");
        network.registerMessage(WoodConverterPackets.Handler.class, WoodConverterPackets.class, 0, Side.SERVER);

        portalble_carfting_table = new CraftingTable().setUnlocalizedName("portalble_carfting_table").setCreativeTab(UraModCreativeTabs).setTextureName(MODID+":crafting_table_top");
        GameRegistry.registerItem(portalble_carfting_table,"portalble_carfting_table");

        //xp

        xp_bush = new XpBush().setBlockName("XpBush").setCreativeTab(UraModCreativeTabs).setBlockTextureName("uramod:xp_berry_bush");
        GameRegistry.registerBlock(xp_bush,"xp_bush");

        // Enchantment
        cobblebreakerentantment = new CobbleBreakerEnchantment().setName("cobble_breaker");
        telekinesis = new TelekinesisEnchantement().setName("telekinesis");
        mending = new Mending();
        frostWalker = new FrostWalker();

        MinecraftForge.EVENT_BUS.register(new EventHandler());


        //food
//.setPotionEffect(Potion.blindness.id,5, 2, 0.05F).setPotionEffect(Potion.confusion.id,5, 2, 0.05F).setPotionEffect(Potion.waterBreathing.id,5, 2, 0.05F).setPotionEffect(Potion.damageBoost.id,5, 2, 0.05F).setPotionEffect(Potion.digSlowdown.id,5, 2, 0.05F).setPotionEffect(Potion.digSpeed.id,5, 2, 0.05F).setPotionEffect(Potion.harm.id,5, 2, 0.05F).setPotionEffect(Potion.heal.id,5, 2, 0.05F).setPotionEffect(Potion.fireResistance.id,5, 2, 0.05F).setPotionEffect(Potion.invisibility.id,5, 2, 0.05F).setPotionEffect(Potion.hunger.id,5, 2, 0.05F).setPotionEffect(Potion.jump.id,5, 2, 0.05F).setPotionEffect(Potion.moveSlowdown.id,5, 2, 0.05F).setPotionEffect(Potion.moveSpeed.id,5, 2, 0.05F).setPotionEffect(Potion.nightVision.id,5, 2, 0.05F).setPotionEffect(Potion.poison.id,5, 2, 0.05F).setPotionEffect(Potion.resistance.id,5, 2, 0.05F).setPotionEffect(Potion.regeneration.id,5, 2, 0.05F).setPotionEffect(Potion.weakness.id,5, 2, 0.05F).setPotionEffect(Potion.wither.id,5, 2, 0.05F)
        iridium_potato = new Iriduim_Food(4,0.5F,false).setUnlocalizedName("iridium_potato").setCreativeTab(UraModCreativeTabs).setTextureName(MODID+":iridium_potato");
        ura_carrot = new ItemFood(6, 1.2F, false).setUnlocalizedName("ura_carrot").setTextureName(MODID+":ura_carrot").setCreativeTab(UraModCreativeTabs);
        candy = new ItemFood(1, 1.2F, false).setUnlocalizedName("candy").setTextureName(MODID+":candy").setCreativeTab(UraModCreativeTabs);

        GameRegistry.registerItem(iridium_potato,"iridium_potato");
        GameRegistry.registerItem(ura_carrot,"ura_carrot");

        // UraMod Ingots & Nuggets

        UraIngot = new UraItem().setUnlocalizedName("ura_ingot").setTextureName("uramod:ura_ingot").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        ArgentIngot = new UraItem().setUnlocalizedName("argent_ingot").setTextureName("uramod:argent_ingot").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        NeodymeIngot = new UraItem().setUnlocalizedName("neodyme_ingot").setTextureName("uramod:neodyme_ingot").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        ura_nugget = new UraItem().setUnlocalizedName("ura_nugget").setTextureName(MODID+":ura_nugget").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        argent_nugget = new UraItem().setUnlocalizedName("argent_nugget").setTextureName(MODID+":argent_nugget").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        halloween_ingot = new UraItem().setUnlocalizedName("halloween_ingot").setTextureName("uramod:halloween_ingot").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        halloween_nugget = new UraItem().setUnlocalizedName("halloween_nugget").setTextureName(MODID+":halloween_nugget").setCreativeTab(Ura_ModMain.UraModCreativeTabs);

        // UraMod Blocks of ingots

        ura_block = new UraBlock(Material.rock).setBlockName("ura_block").setHardness(10.0F).setBlockTextureName(MODID + ":ura_block").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        neodyme_block = new UraBlock(Material.rock).setBlockName("neodyme_block").setHardness(10.0F).setBlockTextureName(MODID + ":neodyme_block").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        argent_block = new UraBlock(Material.rock).setBlockName("argent_block").setHardness(10.0F).setBlockTextureName(MODID + ":argent_block").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        halloween_block = new UraBlock(Material.rock).setBlockName("halloween_block").setHardness(10.0F).setBlockTextureName(MODID + ":halloween_block").setCreativeTab(Ura_ModMain.UraModCreativeTabs);

       // UraMod Ores

        UraOre = new UraBlock (Material.rock).setBlockName("ura_ore").setBlockTextureName("uramod:ura_ore").setHardness(10.0F).setCreativeTab(Ura_ModMain.UraModCreativeTabs).setHardness(3.0F);
        ArgentOre = new UraBlock (Material.rock).setBlockName("argent_ore").setBlockTextureName("uramod:argent_ore").setHardness(10.0F).setCreativeTab(Ura_ModMain.UraModCreativeTabs).setHardness(3.0F);
        NeodymeOre = new NeodymeOre(Material.rock).setBlockName("neodyme_ore").setBlockTextureName("uramod:neodyme_ore").setHardness(10.0F).setCreativeTab(Ura_ModMain.UraModCreativeTabs).setHardness(3.0F);
        halloween_ore = new UraBlock (Material.rock).setBlockName("halloween_ore").setBlockTextureName("uramod:halloween_ore").setHardness(10.0F).setCreativeTab(Ura_ModMain.UraModCreativeTabs).setHardness(3.0F);

        // UraMod Blocks & Items
       // ura_anvil = new UraAnvil().setCreativeTab(UraModCreativeTabs).setBlockName("ura_anvil");
        claim_stick = new UraItem().setUnlocalizedName("claim_stick").setTextureName("uramod:claim_stick").setCreativeTab(UraModCreativeTabs).setMaxStackSize(1).setMaxDamage(0);
        neodyme_upgarde = new Neodyme_Upgrade().setUnlocalizedName("neodyme_upgrade").setTextureName("uramod:nedoyme_upgrade").setCreativeTab(UraModCreativeTabs).setMaxStackSize(1);
        iridium_upgarde = new Iriduim_Upgrade().setUnlocalizedName("iridium_upgrade").setTextureName("uramod:iridium_upgrade").setCreativeTab(UraModCreativeTabs).setMaxStackSize(1);
        diamond_upgarde = new Diamond_Upgrade().setUnlocalizedName("diamond_upgrade").setTextureName("uramod:diamond_upgrade").setCreativeTab(UraModCreativeTabs).setMaxStackSize(1);
        argent_upgarde = new Argent_Upgrade().setUnlocalizedName("argent_upgrade").setTextureName("uramod:argent_upgrade").setCreativeTab(UraModCreativeTabs).setMaxStackSize(1);
        ura_upgarde = new Ura_Upgrade().setUnlocalizedName("ura_upgarde").setTextureName("uramod:ura_upgrade").setCreativeTab(UraModCreativeTabs).setMaxStackSize(1);
        CaveBlock = new UraBlock(Material.glass).setBlockName("cave_block").setBlockTextureName("uramod:cave_block").setLightOpacity(0).setHardness(0.3F).setResistance(1.5F).setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        GreenScreenBlock = new  UraBlock (Material.glass).setBlockName("green_screen_block").setBlockTextureName("uramod:green_screen_block").setLightLevel(1.0F).setHardness(10.0F).setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        BlueScreenBlock = new  UraBlock (Material.glass).setBlockName("blue_screen_block").setBlockTextureName("uramod:blue_screen_block").setLightLevel(1.0F).setHardness(10.0F).setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        RedScreenBlock = new  UraBlock (Material.glass).setBlockName("red_screen_block").setBlockTextureName("uramod:red_screen_block").setLightLevel(1.0F).setHardness(10.0F).setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        YellowScreenBlock = new  UraBlock (Material.glass).setBlockName("yellow_screen_block").setBlockTextureName("uramod:yellow_screen_block").setLightLevel(1.0F).setLightOpacity(0).setHardness(10.0F).setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        obsi_stairs = new UraBlockStairs(Blocks.obsidian ,0).setBlockName("obsidian_stairs").setResistance(2000.0F).setCreativeTab(Ura_ModMain.UraModCreativeTabs).setHardness(10.0F);
        dirt_stairs = new UraBlockStairs(Blocks.dirt ,0).setBlockName("dirt_stairs").setCreativeTab(Ura_ModMain.UraModCreativeTabs).setHardness(1.0F);
        bedrock_stairs = new UraBlockStairs(Blocks.bedrock,0).setBlockName("bedrock_stairs").setHardness(-1F).setResistance(-1F).setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        bedrock = new UraBlock(Material.rock).setBlockName("bedrock").setBlockTextureName(MODID + ":bedrock").setHardness(-1.0F).setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        urafurnace = new UraFurnace(Material.rock,false).setBlockName("urafurnace").setHardness(10.0F).setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        lit_urafurnace = new UraFurnace(Material.rock, true).setBlockName("lit_urafurnace").setHardness(10.0F).setLightLevel(0.875F);
        urachest = new UraChest(Material.rock).setBlockName("urachest").setHardness(10.0F).setCreativeTab(UraModCreativeTabs);
        acceleroplanter = new AcceleroPlanter(Material.rock).setBlockName("acceleroplanter").setBlockTextureName(MODID + ":acceleroplanter").setHardness(10.0F).setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        wood_converter = new WoodConverter(Material.wood).setBlockName("wood_converter").setBlockTextureName(MODID + ":wood_converter").setHardness(10.0F).setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        iriduim_smithing_table = new IriduimSmithingTable(Material.iron).setBlockName("iriduim_smithing_table").setCreativeTab(UraModCreativeTabs);
        Box01= new UraBlock(Material.rock).setBlockName("Neodyme_Box").setBlockTextureName(MODID + ":Box01").setHardness(-1F).setCreativeTab(Ura_ModMain.UraModCreativeTabs).setResistance(-1F);
        Box02= new UraBlock(Material.rock).setBlockName("Silver_Box").setBlockTextureName(MODID + ":Box02").setHardness(-1F).setCreativeTab(Ura_ModMain.UraModCreativeTabs).setResistance(-1F);
        Box04= new UraBlock(Material.rock).setBlockName("Ura_Box").setBlockTextureName(MODID + ":Box04").setHardness(-1F).setCreativeTab(Ura_ModMain.UraModCreativeTabs).setResistance(-1F);
        Box05= new UraBlock(Material.rock).setBlockName("Iridium_Box").setBlockTextureName(MODID + ":Box05").setHardness(-1F).setCreativeTab(Ura_ModMain.UraModCreativeTabs).setResistance(-1F);
        erysfoly_glass= new ErysfolyGlass();
        dollar_one = new UraItem().setUnlocalizedName("dollar_one").setTextureName("uramod:dollarone").setCreativeTab(UraModCreativeTabs).setMaxStackSize(16);
        dollar_five = new UraItem().setUnlocalizedName("dollar_five").setTextureName("uramod:dollarfive").setCreativeTab(UraModCreativeTabs).setMaxStackSize(16);
        dollar_ten = new UraItem().setUnlocalizedName("dollar_ten").setTextureName("uramod:dollarten").setCreativeTab(UraModCreativeTabs).setMaxStackSize(16);
        dollar_twenty = new UraItem().setUnlocalizedName("dollar_twenty").setTextureName("uramod:dollartwenty").setCreativeTab(UraModCreativeTabs).setMaxStackSize(16);
        dollar_fifty = new UraItem().setUnlocalizedName("dollar_fifty").setTextureName("uramod:dollarfifty").setCreativeTab(UraModCreativeTabs).setMaxStackSize(16);
        money_dispenser = new MoneyDispenser();
        randomore = new RandomOre(Material.rock);
        randomoreitem = new RandomOreItem();

        if(fakewaterfluid.getBlock() == null)
        {
            fakewaterblock = new BlockFluidFakewater(fakewaterfluid, Material.water).setBlockName("fakewater");
            GameRegistry.registerBlock(fakewaterblock, "fakewater");
            fakewaterfluid.setBlock(fakewaterblock);
        }
        else
        {
            fakewaterblock = fakewaterfluid.getBlock();
        }

        if(fakelavafluid.getBlock() == null)
        {
            fakelavablock = new BlockFluidFakelava(fakelavafluid, Material.water).setBlockName("fakelava");
            GameRegistry.registerBlock(fakelavablock, "fakelava");
            fakelavafluid.setBlock(fakelavablock);
        }
        else
        {
            fakelavablock = fakelavafluid.getBlock();
        }

        if(glowstonefluid.getBlock() == null)
        {
            glowstoneliquideblock = new BlockFluidGlowstoneliquide(glowstonefluid, Material.water).setBlockName("glowstoneliquide");
            GameRegistry.registerBlock(glowstoneliquideblock, "glowstoneliquide");
            glowstonefluid.setBlock(glowstoneliquideblock);
        }
        else
        {
            glowstoneliquideblock = glowstonefluid.getBlock();
        }

        fakewaterbucket = new ItemBucketFakeWater(fakewaterblock).setUnlocalizedName("fakewaterbucket").setTextureName("uramod:fakewaterbucket");
        fakelavabucket = new ItemBucketFakeWater(fakelavablock).setUnlocalizedName("fakelavabucket").setTextureName("uramod:fakelavabucket");
        glowstoneliquidebucket = new ItemBucketFakeWater(glowstoneliquideblock).setUnlocalizedName("glowstoneliquidebucket").setTextureName("uramod:glowstoneliquidebucket");

        // Items & blocks 1.8 to 1.16

        prismarin = new UraBlock(Material.rock).setBlockName("prismarin").setBlockTextureName(MODID + ":prismarine").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        dark_prismarin = new UraBlock(Material.rock).setBlockName("dark_prismarin").setBlockTextureName(MODID + ":dark_prismarine").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        prismarin_bricks = new UraBlock(Material.rock).setBlockName("prismarin_bricks").setBlockTextureName(MODID + ":prismarine_bricks").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        sea_lanterne = new UraBlock(Material.rock).setBlockName("sea_lantern").setHardness(10.0F).setBlockTextureName(MODID + ":sea").setLightLevel(1.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        coarse_dirt = new UraBlock(Material.rock).setBlockName("coarse_dirt").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        red_sandstone = new RedSandstoneBlock(Material.rock).setBlockName("red_sandstone").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        slime_block = new SlimeBlock(Material.rock);
        invisible_barriere = new BlockInvisible(Material.rock);
        end_rode = new UraBlock(Material.rock).setBlockName("end_rod").setBlockTextureName(MODID + ":end_rod").setLightLevel(1.0F).setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        purpur_block = new UraBlock(Material.rock).setBlockName("purpur_block").setBlockTextureName(MODID + ":purpur_block").setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        Purpur_Stair = new UraBlockStairs(purpur_block ,0).setBlockName("Purpur_Stair").setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        purpur_pillar = new PurpurPillar(Material.rock);
        magma_block = new UraBlock(Material.rock).setBlockName("magma_block").setBlockTextureName(MODID + ":magma_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        nether_wart_block = new UraBlock(Material.rock).setBlockName("nether_wart_block").setBlockTextureName(MODID + ":nether_wart_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        red_nether_brick = new UraBlock(Material.rock).setBlockName("red_nether_brick").setBlockTextureName(MODID + ":red_nether_bricks").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        bone_block = new BonesBlock(Material.rock).setBlockName("bone_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_black = new UraBlock(Material.rock).setBlockName("glazed_terracotta_black").setBlockTextureName(MODID + ":glazed_terracotta_black").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_blue = new UraBlock(Material.rock).setBlockName("glazed_terracotta_blue").setBlockTextureName(MODID + ":glazed_terracotta_blue").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_brown = new UraBlock(Material.rock).setBlockName("glazed_terracotta_brown").setBlockTextureName(MODID + ":glazed_terracotta_brown").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_cyan = new UraBlock(Material.rock).setBlockName("glazed_terracotta_cyan").setBlockTextureName(MODID + ":glazed_terracotta_cyan").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_grey = new UraBlock(Material.rock).setBlockName("glazed_terracotta_grey").setBlockTextureName(MODID + ":glazed_terracotta_gray").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_green = new UraBlock(Material.rock).setBlockName("glazed_terracotta_green").setBlockTextureName(MODID + ":glazed_terracotta_green").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_light_blue = new UraBlock(Material.rock).setBlockName("glazed_terracotta_light_blue").setBlockTextureName(MODID + ":glazed_terracotta_light_blue").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_lime = new UraBlock(Material.rock).setBlockName("glazed_terracotta_lime").setBlockTextureName(MODID + ":glazed_terracotta_lime").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_magenta = new UraBlock(Material.rock).setBlockName("glazed_terracotta_magenta").setBlockTextureName(MODID + ":glazed_terracotta_magenta").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_orange = new UraBlock(Material.rock).setBlockName("glazed_terracotta_orange").setBlockTextureName(MODID + ":glazed_terracotta_orange").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_pink = new UraBlock(Material.rock).setBlockName("glazed_terracotta_pink").setBlockTextureName(MODID + ":glazed_terracotta_pink").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_red = new UraBlock(Material.rock).setBlockName("glazed_terracotta_red").setBlockTextureName(MODID + ":glazed_terracotta_red").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_silver = new UraBlock(Material.rock).setBlockName("glazed_terracotta_silver").setBlockTextureName(MODID + ":glazed_terracotta_silver").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_white = new UraBlock(Material.rock).setBlockName("glazed_terracotta_white").setBlockTextureName(MODID + ":glazed_terracotta_white").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        glazed_terracotta_yellow = new UraBlock(Material.rock).setBlockName("glazed_terracotta_yellow").setBlockTextureName(MODID + ":glazed_terracotta_yellow").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        bubble_coral_block = new UraBlock(Material.rock).setBlockName("bubble_coral_block").setBlockTextureName(MODID + ":bubble_coral_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        brain_coral_block = new UraBlock(Material.rock).setBlockName("brain_coral_block").setBlockTextureName(MODID + ":brain_coral_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        tube_coral_block = new UraBlock(Material.rock).setBlockName("tube_coral_block").setBlockTextureName(MODID + ":tube_coral_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        horn_coral_block = new UraBlock(Material.rock).setBlockName("horn_coral_block").setBlockTextureName(MODID + ":horn_coral_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        fire_coral_block = new UraBlock(Material.rock).setBlockName("fire_coral_block").setBlockTextureName(MODID + ":fire_coral_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        dead_tube_coral_block = new UraBlock(Material.rock).setBlockName("dead_tube_coral_block").setBlockTextureName(MODID + ":dead_tube_coral_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        dead_horn_coral_block = new UraBlock(Material.rock).setBlockName("dead_horn_coral_block").setBlockTextureName(MODID + ":dead_horn_coral_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        dead_fire_coral_block = new UraBlock(Material.rock).setBlockName("dead_fire_coral_block").setBlockTextureName(MODID + ":dead_fire_coral_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        dead_bubble_coral_block = new UraBlock(Material.rock).setBlockName("dead_bubble_coral_block").setBlockTextureName(MODID + ":dead_bubble_coral_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        dead_brain_coral_block = new UraBlock(Material.rock).setBlockName("dead_brain_coral_block").setBlockTextureName(MODID + ":dead_brain_coral_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        shulker_box = new ShulkerBox(Material.rock);
        stripped_acacia_log = new UraStrippedLogs("acacia", 1).setBlockName("stripped_acacia_log");
        stripped_birch_log = new UraStrippedLogs("birch", 2).setBlockName("stripped_birch_log");
        stripped_dark_oak_log = new UraStrippedLogs("dark_oak", 3).setBlockName("stripped_dark_oak_log");
        stripped_jungle_log = new UraStrippedLogs("jungle", 1).setBlockName("stripped_jungle_log");
        stripped_oak_log = new UraStrippedLogs("oak", 1).setBlockName("stripped_oak_log");
        stripped_spruce_log = new UraStrippedLogs("spruce", 1).setBlockName("stripped_spruce_log");
        acacia_log = new UraBlock(Material.rock).setBlockName("acacia_log").setBlockTextureName(MODID + ":acacia_log").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        birch_log = new UraBlock(Material.rock).setBlockName("birch_log").setBlockTextureName(MODID + ":birch_log").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        dark_oak_log = new UraBlock(Material.rock).setBlockName("dark_oak_log").setBlockTextureName(MODID + ":dark_oak_log").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        jungle_log = new UraBlock(Material.rock).setBlockName("jungle_log").setBlockTextureName(MODID + ":jungle_log").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        oak_log = new UraBlock(Material.rock).setBlockName("oak_log").setBlockTextureName(MODID + ":oak_log").setHardness(10.0F).setBlockTextureName(MODID + ":oak_log").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        spruce_log = new UraBlock(Material.rock).setBlockName("spruce_log").setBlockTextureName(MODID + ":spruce_log").setHardness(10.0F).setBlockTextureName(MODID + ":spruce_log").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        pumpkin = new Pumpkin(Material.rock).setBlockName("pumpkin").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        bee_hive = new bee_hive(Material.wood).setBlockName("bee_hive").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        bee_nest = new bee_nest(Material.wood).setBlockName("bee_nest").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        wet_sponge = new UraBlock(Material.rock).setBlockName("wet_sponge").setBlockTextureName(MODID + ":wet_sponge").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        armor_stand = new UraBlock(Material.rock).setBlockName("armor_stand").setBlockTextureName(MODID + ":armor_stand").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        iron_trapdoor = new IronTrapdoor();
        hay_block = new hayBlock(Material.rock).setBlockName("hay_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        bamboo = new UraBlock(Material.rock).setBlockName("bamboo").setBlockTextureName(MODID + ":bamboo").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        bush = new UraBlock(Material.rock).setBlockName("bush").setBlockTextureName(MODID + ":bush").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        bell = new UraBlock(Material.rock).setBlockName("bell").setBlockTextureName(MODID + ":bell").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        composter = new UraBlock(Material.rock).setBlockName("composter").setBlockTextureName(MODID + ":composter").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        scaffolding = new UraBlock(Material.rock).setBlockName("scaffolding").setBlockTextureName(MODID + ":scaffolding").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        campfire = new UraBlock(Material.rock).setBlockName("campfire").setBlockTextureName(MODID + ":campfire").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        smoke_house = new smoker(Material.rock).setBlockName("smoke_house").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        blast_furnace = new blast_furnace(Material.rock).setBlockName("blast_furnace").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        lantern = new UraBlock(Material.rock).setBlockName("lantern").setBlockTextureName(MODID + ":lantern").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        loom = new UraBlock(Material.rock).setBlockName("loom").setBlockTextureName(MODID + ":loom").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        desk = new UraBlock(Material.rock).setBlockName("desk").setBlockTextureName(MODID + ":desk").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        mapping_table = new cartography_table(Material.rock).setBlockName("mapping_table").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        blacksmith_table = new SmithingTable(Material.iron).setBlockName("blacksmith_table").setCreativeTab(FururModCreativeTabs);
        archery_table = new fletching_table(Material.rock).setBlockName("archery_table").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        stonecutter = new UraBlock(Material.rock).setBlockName("stonecutter").setBlockTextureName(MODID + ":stonecutter").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        barrel = new barrel(Material.rock).setBlockName("barrel").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        honey_block = new UraBlock(Material.rock).setBlockName("honey_block").setBlockTextureName(MODID + ":honey_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        honeycomb_block = new UraBlock(Material.rock).setBlockName("honeycomb_block").setBlockTextureName(MODID + ":honeycomb_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        barrier_block = new UraBlock(Material.rock).setBlockName("barrier_block").setLightOpacity(0).setBlockTextureName("uramod:invisible_block").setHardness(-1.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        concrete_black_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_black_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_black").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_blue_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_blue_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_blue").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_brown_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_brown_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_brown").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_cyan_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_cyan_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_cyan").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_gray_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_gray_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_gray").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_green_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_green_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_green").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_light_blue_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_light_blue_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_light_blue").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_lime_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_lime_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_lime").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_magenta_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_magenta_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_magenta").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_orange_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_orange_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_orange").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_pink_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_pink_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_pink").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_purple_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_purple_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_purple").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_red_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_red_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_red").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_silver_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_silver_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_silver").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_white_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_white_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_white").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_yellow_powder= new UraBlockSand(Material.rock).setStepSound(Block.soundTypeSand).setBlockName("concrete_yellow_powder").setHardness(10.0F).setBlockTextureName(MODID + ":concrete_powder_yellow").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(5.0F);
        concrete_black= new UraBlock(Material.rock).setBlockName("concrete_black").setBlockTextureName(MODID + ":concrete_black").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_blue= new UraBlock(Material.rock).setBlockName("concrete_blue").setBlockTextureName(MODID + ":concrete_blue").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_brown= new UraBlock(Material.rock).setBlockName("concrete_brown").setBlockTextureName(MODID + ":concrete_brown").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_cyan= new UraBlock(Material.rock).setBlockName("concrete_cyan").setBlockTextureName(MODID + ":concrete_cyan").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_gray= new UraBlock(Material.rock).setBlockName("concrete_gray").setBlockTextureName(MODID + ":concrete_gray").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_green= new UraBlock(Material.rock).setBlockName("concrete_green").setBlockTextureName(MODID + ":concrete_green").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_light_blue= new UraBlock(Material.rock).setBlockName("concrete_light_blue").setBlockTextureName(MODID + ":concrete_light_blue").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        concrete_lime= new UraBlock(Material.rock).setBlockName("concrete_lime").setBlockTextureName(MODID + ":concrete_lime").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_magenta= new UraBlock(Material.rock).setBlockName("concrete_magenta").setBlockTextureName(MODID + ":concrete_magenta").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_orange= new UraBlock(Material.rock).setBlockName("concrete_orange").setBlockTextureName(MODID + ":concrete_orange").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_pink= new UraBlock(Material.rock).setBlockName("concrete_pink").setBlockTextureName(MODID + ":concrete_pink").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_purple= new UraBlock(Material.rock).setBlockName("concrete_purple").setBlockTextureName(MODID + ":concrete_purple").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_red= new UraBlock(Material.rock).setBlockName("concrete_red").setBlockTextureName(MODID + ":concrete_red").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_silver= new UraBlock(Material.rock).setBlockName("concrete_silver").setBlockTextureName(MODID + ":concrete_silver").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_white= new UraBlock(Material.rock).setBlockName("concrete_white").setBlockTextureName(MODID + ":concrete_white").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        concrete_yellow= new UraBlock(Material.rock).setBlockName("concrete_yellow").setBlockTextureName(MODID + ":concrete_yellow").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        ender_passerelle= new UraBlock(Material.rock).setBlockName("ender_passerelle").setBlockTextureName(MODID + ":ender_passerelle").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        stucture_block= new UraBlock(Material.rock).setBlockName("stucture_block").setBlockTextureName(MODID + ":stucture_block").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);









        chrous_flower= new ChorusFlower();
        chorus_plant= new ChorusPlant();
        dragon_head= new UraBlock(Material.rock).setBlockName("dragon_head").setBlockTextureName(MODID + ":dragon_head").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        granite= new UraBlock(Material.rock).setBlockName("granite").setBlockTextureName(MODID + ":granite").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        granite_stairs = new UraBlockStairs(Ura_ModMain.granite ,0).setBlockName("granite_stairs").setResistance(2000.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        granite_slab = new GraniteSlab();
        andesite= new UraBlock(Material.rock).setBlockName("andesite").setBlockTextureName(MODID + ":andesite").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        andesite_stairs = new UraBlockStairs(Ura_ModMain.andesite ,0).setBlockName("andesite_stairs").setResistance(2000.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        andesite_slab = new AndesiteSlab();
        diorite= new UraBlock(Material.rock).setBlockName("diorite").setBlockTextureName(MODID + ":diorite").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        diorite_stairs = new UraBlockStairs(Ura_ModMain.diorite ,0).setBlockName("diorite_stairs").setResistance(2000.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        diorite_slab = new DioriteSlab();
        granite_smooth= new UraBlock(Material.rock).setBlockName("granite_smooth").setBlockTextureName(MODID + ":granite_smooth").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        granite_smooth_stairs = new UraBlockStairs(Ura_ModMain.granite_smooth ,0).setBlockName("granite_smooth_stairs").setResistance(2000.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        granite_smooth_slab = new GraniteSmoothSlab();
        andesite_smooth= new UraBlock(Material.rock).setBlockName("andesite_smooth").setBlockTextureName(MODID + ":andesite_smooth").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        andesite_smooth_stairs = new UraBlockStairs(Ura_ModMain.andesite_smooth ,0).setBlockName("andesite_smooth_stairs").setResistance(2000.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        andesite_smooth_slab = new AndesiteSmoothSlab();
        diorite_smooth= new UraBlock(Material.rock).setBlockName("diorite_smooth").setBlockTextureName(MODID + ":diorite_smooth").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        diorite_smooth_stairs = new UraBlockStairs(Ura_ModMain.diorite_smooth ,0).setBlockName("diorite_smooth_stairs").setResistance(2000.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        diorite_smooth_slab = new DioriteSmoothSlab();
        tube_coral= new UraBlock(Material.rock).setBlockName("tube_coral").setBlockTextureName(MODID + ":tube_coral").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        brain_coral= new UraBlock(Material.rock).setBlockName("brain_coral").setBlockTextureName(MODID + ":brain_coral").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        bubble_coral= new UraBlock(Material.rock).setBlockName("bubble_coral").setBlockTextureName(MODID + ":bubble_coral").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        fire_coral= new UraBlock(Material.rock).setBlockName("fire_coral").setBlockTextureName(MODID + ":fire_coral").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        horn_coral= new UraBlock(Material.rock).setBlockName("horn_coral").setBlockTextureName(MODID + ":horn_coral").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        dead_tube_coral= new UraBlock(Material.rock).setBlockName("dead_tube_coral").setBlockTextureName(MODID + ":dead_tube_coral").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        dead_brain_coral= new UraBlock(Material.rock).setBlockName("dead_brain_coral").setBlockTextureName(MODID + ":dead_brain_coral").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        dead_bubble_coral= new UraBlock(Material.rock).setBlockName("dead_bubble_coral").setBlockTextureName(MODID + ":dead_bubble_coral").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        dead_fire_coral= new UraBlock(Material.rock).setBlockName("dead_fire_coral").setBlockTextureName(MODID + ":dead_fire_coral").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        dead_horn_coral= new UraBlock(Material.rock).setBlockName("dead_horn_coral").setBlockTextureName(MODID + ":dead_horn_coral").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        tube_coral_fan= new UraBlock(Material.rock).setBlockName("tube_coral_fan").setBlockTextureName(MODID + ":tube_coral_fan").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        brain_coral_fan= new UraBlock(Material.rock).setBlockName("brain_coral_fan").setBlockTextureName(MODID + ":brain_coral_fan").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        bubble_coral_fan= new UraBlock(Material.rock).setBlockName("bubble_coral_fan").setBlockTextureName(MODID + ":bubble_coral_fan").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        fire_coral_fan= new UraBlock(Material.rock).setBlockName("fire_coral_fan").setBlockTextureName(MODID + ":fire_coral_fan").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        horn_coral_fan= new UraBlock(Material.rock).setBlockName("horn_coral_fan").setBlockTextureName(MODID + ":horn_coral_fan").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        dead_tube_coral_fan= new UraBlock(Material.rock).setBlockName("dead_tube_coral_fan").setBlockTextureName(MODID + ":dead_tube_coral_fan").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        dead_brain_coral_fan= new UraBlock(Material.rock).setBlockName("dead_brain_coral_fan").setBlockTextureName(MODID + ":dead_brain_coral_fan").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        dead_bubble_coral_fan= new UraBlock(Material.rock).setBlockName("dead_bubble_coral_fan").setBlockTextureName(MODID + ":dead_bubble_coral_fan").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        dead_fire_coral_fan= new UraBlock(Material.rock).setBlockName("dead_fire_coral_fan").setBlockTextureName(MODID + ":dead_fire_coral_fan").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        dead_horn_coral_fan= new UraBlock(Material.rock).setBlockName("dead_horn_coral_fan").setBlockTextureName(MODID + ":dead_horn_coral_fan").setHardness(10.0F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(3.0F);
        stone_stairs = new UraBlockStairs(Blocks.stone ,0).setBlockName("stone_stairs").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        mossy_cut_stone_stairs = new UraBlockStairs(Blocks.mossy_cobblestone ,0).setBlockName("mossy_cut_stone_stairs").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        mossy_stone_stairs = new UraBlockStairs(Blocks.mossy_cobblestone ,0).setBlockName("mossy_stone_stairs").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        smooth_sandstone_stairs = new UraBlockStairs(Blocks.sandstone ,0).setBlockName("smooth_sandstone_stairs").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        smooth_red_sandstone_stairs = new UraBlockStairs(Blocks.sandstone ,0).setBlockName("smooth_red_sandstone_stairs").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        smooth_quartz_stairs = new UraBlockStairs(Blocks.quartz_block ,0).setBlockName("smooth_quartz_stairs").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        red_nether_bricks_stairs = new UraBlockStairs(Blocks.nether_brick ,0).setBlockName("red_nether_bricks_stairs").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        end_bricks_stairs = new UraBlockStairs(Blocks.end_stone ,0).setBlockName("end_bricks_stairs").setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(10.0F);
        netherite_helmet = new ItemNetheriteArmor(netherite_armor, 0).setUnlocalizedName("netherite_helmet").setTextureName(MODID + ":netherite_helmet").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        netherite_chestPlate = new ItemNetheriteArmor(netherite_armor, 1).setUnlocalizedName("netherite_chestPlate").setTextureName(MODID + ":netherite_chestplate").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        netherite_leggings = new ItemNetheriteArmor(netherite_armor, 2).setUnlocalizedName("netherite_leggings").setTextureName(MODID + ":netherite_leggings").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        netherite_boots = new ItemNetheriteArmor(netherite_armor, 3).setUnlocalizedName("netherite_boots").setTextureName(MODID + ":netherite_boots").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        netherite_ingot = new UraItem().setUnlocalizedName("netherite_ingot").setTextureName(MODID + ":netherite_ingot").setCreativeTab(FururModCreativeTabs);
        netherite_scrap = new UraItem().setUnlocalizedName("netherite_scrap").setTextureName(MODID + ":netherite_scrap").setCreativeTab(FururModCreativeTabs);
        netherite_block = new UraBlock(Material.rock).setBlockName("netherite_block").setBlockTextureName(MODID + ":netherite_block").setResistance(1200F).setCreativeTab(Ura_ModMain.FururModCreativeTabs).setHardness(50.0F);;
        ancient_debris = new AncientDebris();
        betterave = new ItemFood(1, 1.2F, false).setUnlocalizedName("betterave").setTextureName(MODID+":betterave").setCreativeTab(FururModCreativeTabs);
        bouclier = new UraItem().setUnlocalizedName("bouclier").setTextureName(MODID + ":bouclier").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        chorus = new ChorusFruit();
        ender_crystal = new EnderCrystal().setUnlocalizedName("ender_crystal").setTextureName(MODID + ":ender_crystal").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        chorus_eclate = new UraItem().setUnlocalizedName("chorus_eclate").setTextureName(MODID + ":chorus_eclate").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        elytres = new Elytra();
        fleche_spectrale = new UraItem().setUnlocalizedName("fleche_spectrale").setTextureName(MODID + ":fleche_spectrale").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        fleche_spatiale = new UraItem().setUnlocalizedName("fleche_spatiale").setTextureName(MODID + ":fleche_spectrale").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        soufle_dragon = new UraItem().setUnlocalizedName("soufle_dragon").setTextureName(MODID + ":soufle_dragon").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        soupe_betteraves = new ItemFood(6, 7.2F, false).setUnlocalizedName("soupe_betteraves").setTextureName(MODID+":soupe_betteraves").setCreativeTab(FururModCreativeTabs);
        purpur_slab= new PurpurSlab();
        rock_slab= new RockSlab();
        frosted_ice= new FrostedIce();
        spurce_door= new SpurceDoor().setBlockName("spurce_door").setBlockTextureName(MODID + ":spruce_door");
        spurce_door_item= new SpurceDoorItem();
        birch_door= new BirchDoor().setBlockName("birch_door").setBlockTextureName(MODID + ":birch_door");
        birch_door_item= new BirchDoorItem();
        jungle_door= new JungleDoor().setBlockName("jungle_door").setBlockTextureName(MODID + ":jungle_door");
        jungle_door_item= new JungleDoorItem();
        acacia_door= new AcaciaDoor().setBlockName("acacia_door").setBlockTextureName(MODID + ":acacia_door");
        acacia_door_item= new AcaciaDoorItem();
        dark_oak_door= new DarkOakDoor().setBlockName("dark_oak_door").setBlockTextureName(MODID + ":dark_oak_door");
        dark_oak_door_item= new DarkOakDoorItem();
        andesite_wall= new UraWall(andesite).setBlockName("andesite_wall").setCreativeTab(FururModCreativeTabs);
        diorite_wall= new UraWall(diorite).setBlockName("diorite_wall").setCreativeTab(FururModCreativeTabs);
        granite_wall= new UraWall(granite).setBlockName("granite_wall").setCreativeTab(FururModCreativeTabs);
        polished_andesite_wall= new UraWall(andesite_smooth).setBlockName("polished_andesite_wall").setCreativeTab(FururModCreativeTabs);
        polished_diorite_wall= new UraWall(diorite_smooth).setBlockName("polished_diorite_wall").setCreativeTab(FururModCreativeTabs);
        polished_granite_wall= new UraWall(granite_smooth).setBlockName("polished_granite_wall").setCreativeTab(FururModCreativeTabs);
        acacia_fence = new Fence(4).setBlockName("acacia_fence");
        birch_fence = new Fence(2).setBlockName("birch_fence");
        dark_oak_fence = new Fence(5).setBlockName("dark_oak_fence");
        jungle_fence = new Fence(3).setBlockName("jungle_fence");
        spurce_fence = new Fence(1).setBlockName("spurce_fence");
        acacia_fencegate = new FenceGate("acacia_fencegate", 4);
        birch_fencegate = new FenceGate("birch_fencegate", 2);
        darkoak_fencegate = new FenceGate("darkoak_fencegate", 5);
        jungle_fencegate = new FenceGate("jungle_fencegate", 3);
        spurce_fencegate = new FenceGate("spurce_fencegate", 1);

        GameRegistry.registerItem(randomoreitem,"randomoreitem");
        GameRegistry.registerBlock(randomore,"randomore");
        GameRegistry.registerBlock(netherite_block,"netherite_block");
        GameRegistry.registerBlock(ancient_debris,"ancient_debris");
        GameRegistry.registerBlock(halloween_block,"halloween_block");
        GameRegistry.registerItem(halloween_nugget,"halloween_nugget");
        GameRegistry.registerItem(candy,"candy");
        GameRegistry.registerBlock(halloween_ore,"halloween_ore");
        GameRegistry.registerItem(halloween_ingot,"halloween_ingot");
        GameRegistry.registerBlock(money_dispenser,"money_dispenser");
        GameRegistry.registerItem(dollar_one,"dollar_one");
        GameRegistry.registerItem(dollar_five,"dollar_five");
        GameRegistry.registerItem(dollar_ten,"dollar_ten");
        GameRegistry.registerItem(dollar_twenty,"dollar_twenty");
        GameRegistry.registerItem(dollar_fifty,"dollar_fifty");
        GameRegistry.registerBlock(erysfoly_glass,"erysfoly_glass");
        GameRegistry.registerBlock(acacia_fencegate,"acacia_fencegate");
        GameRegistry.registerBlock(birch_fencegate,"birch_fencegate");
        GameRegistry.registerBlock(darkoak_fencegate,"darkoak_fencegate");
        GameRegistry.registerBlock(jungle_fencegate,"jungle_fencegate");
        GameRegistry.registerBlock(spurce_fencegate,"spurce_fencegate");
        GameRegistry.registerBlock(acacia_fence,"acacia_fence");
        GameRegistry.registerBlock(birch_fence,"birch_fence");
        GameRegistry.registerBlock(dark_oak_fence,"dark_oak_fence");
        GameRegistry.registerBlock(jungle_fence,"jungle_fence");
        GameRegistry.registerBlock(spurce_fence,"spurce_fence");
        GameRegistry.registerBlock(polished_granite_wall,"polished_granite_wall");
        GameRegistry.registerBlock(polished_diorite_wall,"polished_diorite_wall");
        GameRegistry.registerBlock(polished_andesite_wall,"polished_andesite_wall");
        GameRegistry.registerBlock(granite_wall,"granite_wall");
        GameRegistry.registerBlock(diorite_wall,"diorite_wall");
        GameRegistry.registerBlock(andesite_wall,"andesite_wall");
        GameRegistry.registerBlock(spurce_door,"spurce_door");
        GameRegistry.registerItem(spurce_door_item,"spurce_door_item");
        GameRegistry.registerBlock(birch_door,"birch_door");
        GameRegistry.registerItem(birch_door_item,"birch_door_item");
        GameRegistry.registerBlock(jungle_door,"jungle_door");
        GameRegistry.registerItem(jungle_door_item,"jungle_door_item");
        GameRegistry.registerBlock(acacia_door,"acacia_door");
        GameRegistry.registerItem(acacia_door_item,"acacia_door_item");
        GameRegistry.registerBlock(dark_oak_door,"dark_oak_door");
        GameRegistry.registerItem(dark_oak_door_item,"dark_oak_door_item");
        GameRegistry.registerBlock(frosted_ice,"frosted_ice");
        GameRegistry.registerBlock(rock_slab,"rock_slab");
        GameRegistry.registerBlock(purpur_slab,"purpur_slab");
        GameRegistry.registerBlock(Purpur_Stair,"Purpur_Stair");
        GameRegistry.registerBlock(iriduim_smithing_table,"iriduim_smithing_table");
        GameRegistry.registerItem(netherite_ingot,"netherite_ingot");
        GameRegistry.registerItem(netherite_scrap,"netherite_scrap");
        GameRegistry.registerItem(netherite_helmet,"netherite_helmet");
        GameRegistry.registerItem(netherite_chestPlate,"netherite_chestPlate");
        GameRegistry.registerItem(netherite_leggings,"netherite_leggings");
        GameRegistry.registerItem(netherite_boots,"netherite_boots");
        GameRegistry.registerItem(ura_upgarde,"ura_upgrade");
        GameRegistry.registerItem(neodyme_upgarde,"neodyme_upgarde");
        GameRegistry.registerItem(diamond_upgarde,"diamond_upgarde");
        GameRegistry.registerItem(iridium_upgarde,"iridium_upgarde");
        GameRegistry.registerItem(argent_upgarde,"argent_upgarde");

        GameRegistry.registerItem(claim_stick, "claim_stick", MODID);
        GameRegistry.registerItem(fakewaterbucket, "fakewaterbucket", MODID);
        GameRegistry.registerItem(fakelavabucket, "fakelavabucket", MODID);
        GameRegistry.registerItem(glowstoneliquidebucket, "glowstoneliquidebucket", MODID);

        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("fakewater", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(fakewaterbucket), FluidContainerRegistry.EMPTY_BUCKET);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("fakelava", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(fakelavabucket), FluidContainerRegistry.EMPTY_BUCKET);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("glowstoneliquide", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(glowstoneliquidebucket), FluidContainerRegistry.EMPTY_BUCKET);

        GameRegistry.registerBlock(end_bricks_stairs,"end_bricks_stairs");
        GameRegistry.registerBlock(red_nether_bricks_stairs,"red_nether_bricks_stairs");
        GameRegistry.registerBlock(smooth_quartz_stairs,"smooth_quartz_stairs");
        GameRegistry.registerBlock(smooth_red_sandstone_stairs,"smooth_red_sandstone_stairs");
        GameRegistry.registerBlock(smooth_sandstone_stairs,"smooth_sandstone_stairs");
        GameRegistry.registerBlock(mossy_stone_stairs,"mossy_stone_stairs");
        GameRegistry.registerBlock(mossy_cut_stone_stairs,"mossy_cut_stone_stairs");
        GameRegistry.registerBlock(stone_stairs,"stone_stairs");
        GameRegistry.registerBlock(tube_coral_fan,"tube_coral_fan");
        GameRegistry.registerBlock(brain_coral_fan,"brain_coral_fan");
        GameRegistry.registerBlock(bubble_coral_fan,"bubble_coral_fan");
        GameRegistry.registerBlock(fire_coral_fan,"fire_coral_fan");
        GameRegistry.registerBlock(horn_coral_fan,"horn_coral_fan");
        GameRegistry.registerBlock(dead_tube_coral_fan,"dead_tube_coral_fan");
        GameRegistry.registerBlock(dead_brain_coral_fan,"dead_brain_coral_fan");
        GameRegistry.registerBlock(dead_bubble_coral_fan,"dead_bubble_coral_fan");
        GameRegistry.registerBlock(dead_fire_coral_fan,"dead_fire_coral_fan");
        GameRegistry.registerBlock(dead_horn_coral_fan,"dead_horn_coral_fan");
        GameRegistry.registerBlock(tube_coral,"tube_coral");
        GameRegistry.registerBlock(brain_coral,"brain_coral");
        GameRegistry.registerBlock(bubble_coral,"bubble_coral");
        GameRegistry.registerBlock(fire_coral,"fire_coral");
        GameRegistry.registerBlock(horn_coral,"horn_coral");
        GameRegistry.registerBlock(dead_tube_coral,"dead_tube_coral");
        GameRegistry.registerBlock(dead_brain_coral,"dead_brain_coral");
        GameRegistry.registerBlock(dead_bubble_coral,"dead_bubble_coral");
        GameRegistry.registerBlock(dead_fire_coral,"dead_fire_coral");
        GameRegistry.registerBlock(dead_horn_coral,"dead_horn_coral");
        GameRegistry.registerBlock(Box05,"Iridium_Box");
        GameRegistry.registerBlock(Box04,"Ura_Box");
        GameRegistry.registerBlock(Box02,"Silver_Box");
        GameRegistry.registerBlock(Box01,"Neodyme_Box");
        GameRegistry.registerBlock(ender_passerelle,"ender_passerelle");
        GameRegistry.registerBlock(stucture_block,"stucture_block");
        GameRegistry.registerBlock(terre_labouree,"terre_labouree");
        GameRegistry.registerBlock(chrous_flower,"chrous_flower");
        GameRegistry.registerBlock(chorus_plant,"chorus_plant");
        GameRegistry.registerBlock(dragon_head,"dragon_head");
        //GameRegistry.registerBlock(ura_anvil,"ura_anvil");
        GameRegistry.registerBlock(diorite_smooth_stairs,"diorite_smooth_stairs");
        GameRegistry.registerBlock(diorite_smooth,"diorite_smooth");
        GameRegistry.registerBlock(diorite_smooth_slab,"diorite_smooth_slab");
        GameRegistry.registerBlock(andesite_smooth_stairs,"andesite_smooth_stairs");
        GameRegistry.registerBlock(andesite_smooth,"andesite_smooth");
        GameRegistry.registerBlock(andesite_smooth_slab,"andesite_smooth_slab");
        GameRegistry.registerBlock(granite_smooth_stairs,"granite_smooth_stairs");
        GameRegistry.registerBlock(granite_smooth,"granite_smooth");
        GameRegistry.registerBlock(granite_smooth_slab,"granite_smooth_slab");
        GameRegistry.registerBlock(diorite_stairs,"diorite_stairs");
        GameRegistry.registerBlock(diorite,"diorite");
        GameRegistry.registerBlock(diorite_slab,"diorite_slab");
        GameRegistry.registerBlock(andesite_stairs,"andesite_stairs");
        GameRegistry.registerBlock(andesite,"andesite");
        GameRegistry.registerBlock(andesite_slab,"andesite_slab");
        GameRegistry.registerBlock(granite_stairs,"granite_stairs");
        GameRegistry.registerBlock(granite,"granite");
        GameRegistry.registerBlock(granite_slab,"granite_slab");
        GameRegistry.registerBlock(blacksmith_table,"blacksmith_table");
        GameRegistry.registerBlock(archery_table,"archery_table");
        GameRegistry.registerBlock(stonecutter,"stonecutter");
        GameRegistry.registerBlock(barrel,"barrel");
        GameRegistry.registerBlock(honey_block,"honey_block");
        GameRegistry.registerBlock(honeycomb_block,"honeycomb_block");
        GameRegistry.registerBlock(composter,"composter");
        GameRegistry.registerBlock(scaffolding,"scaffolding");
        GameRegistry.registerBlock(campfire,"campfire");
        GameRegistry.registerBlock(smoke_house,"smoke_house");
        GameRegistry.registerBlock(blast_furnace,"blast_furnace");
        GameRegistry.registerBlock(lantern,"lantern");
        GameRegistry.registerBlock(loom,"loom,");
        GameRegistry.registerBlock(desk,"desk");
        GameRegistry.registerBlock(mapping_table,"mapping_table");
        GameRegistry.registerBlock(wet_sponge,"wet_sponge");
        GameRegistry.registerBlock(armor_stand,"armor_stand");
        GameRegistry.registerBlock(iron_trapdoor,"iron_trapdoor");
        GameRegistry.registerBlock(hay_block,"hay_block");
        GameRegistry.registerBlock(bamboo,"bamboo");
        GameRegistry.registerBlock(bush,"bush");
        GameRegistry.registerBlock(bell,"bell");
        GameRegistry.registerBlock(urafurnace,"urafurnace");
        GameRegistry.registerBlock(lit_urafurnace,"lit_urafurnace");
        GameRegistry.registerBlock(urachest,"urachest");
        GameRegistry.registerBlock(acceleroplanter,"acceleroplanter");
        GameRegistry.registerBlock(wood_converter,"wood_converter");
        GameRegistry.registerBlock(bedrock,"bedrock");
        GameRegistry.registerBlock(bee_hive,"bee_hive");
        GameRegistry.registerBlock(bee_nest,"bee_nest");
        GameRegistry.registerBlock(prismarin,"prismarin");
        GameRegistry.registerBlock(dark_prismarin,"dark_prismarin");
        GameRegistry.registerBlock(prismarin_bricks,"prismarin_bricks");
        GameRegistry.registerBlock(sea_lanterne,"sea_lanterne");
        GameRegistry.registerBlock(coarse_dirt,"coarse_dirt");
        GameRegistry.registerBlock(red_sandstone,"red_sandstone");
        GameRegistry.registerBlock(slime_block,"slime_block");
        GameRegistry.registerBlock(invisible_barriere,"invisible_barriere");
        GameRegistry.registerBlock(end_rode,"end_rode");
        GameRegistry.registerBlock(purpur_block,"purpur_block");
        GameRegistry.registerBlock(purpur_pillar,"purpur_pillar");
        GameRegistry.registerBlock(magma_block,"magma_block");
        GameRegistry.registerBlock(nether_wart_block,"nether_wart_block");
        GameRegistry.registerBlock(red_nether_brick,"red_nether_brick");
        GameRegistry.registerBlock(bone_block,"bone_block");
        GameRegistry.registerBlock(glazed_terracotta_black,"glazed_terracotta_black");
        GameRegistry.registerBlock(glazed_terracotta_blue,"glazed_terracotta_blue");
        GameRegistry.registerBlock(glazed_terracotta_brown,"glazed_terracotta_brown");
        GameRegistry.registerBlock(glazed_terracotta_cyan,"glazed_terracotta_cyan");
        GameRegistry.registerBlock(glazed_terracotta_grey,"glazed_terracotta_grey");
        GameRegistry.registerBlock(glazed_terracotta_green,"glazed_terracotta_green");
        GameRegistry.registerBlock(glazed_terracotta_light_blue,"glazed_terracotta_light_blue");
        GameRegistry.registerBlock(glazed_terracotta_lime,"glazed_terracotta_lime");
        GameRegistry.registerBlock(glazed_terracotta_magenta,"glazed_terracotta_magenta");
        GameRegistry.registerBlock(glazed_terracotta_orange,"glazed_terracotta_orange");
        GameRegistry.registerBlock(glazed_terracotta_pink,"glazed_terracotta_pink");
        GameRegistry.registerBlock(glazed_terracotta_red,"glazed_terracotta_red");
        GameRegistry.registerBlock(glazed_terracotta_silver,"glazed_terracotta_silver");
        GameRegistry.registerBlock(bubble_coral_block,"bubble_coral_block");
        GameRegistry.registerBlock(brain_coral_block,"brain_coral_block");
        GameRegistry.registerBlock(tube_coral_block,"tube_coral_block");
        GameRegistry.registerBlock(horn_coral_block,"horn_coral_block");
        GameRegistry.registerBlock(fire_coral_block,"fire_coral_block");
        GameRegistry.registerBlock(dead_tube_coral_block,"dead_tube_coral_block");
        GameRegistry.registerBlock(dead_horn_coral_block,"dead_horn_coral_block");
        GameRegistry.registerBlock(dead_fire_coral_block,"dead_fire_coral_block");
        GameRegistry.registerBlock(dead_bubble_coral_block,"dead_bubble_coral_block");
        GameRegistry.registerBlock(dead_brain_coral_block,"dead_brain_coral_block");
        GameRegistry.registerBlock(shulker_box,"shulker_box");
        GameRegistry.registerBlock(stripped_acacia_log,"stripped_acacia_log");
        GameRegistry.registerBlock(stripped_birch_log,"stripped_birch_log");
        GameRegistry.registerBlock(stripped_dark_oak_log,"stripped_dark_oak_log");
        GameRegistry.registerBlock(stripped_jungle_log,"stripped_jungle_log");
        GameRegistry.registerBlock(stripped_oak_log,"stripped_oak_log");
        GameRegistry.registerBlock(stripped_spruce_log,"stripped_spruce_log");
        GameRegistry.registerBlock(acacia_log,"acacia_log");
        GameRegistry.registerBlock(birch_log,"birch_log");
        GameRegistry.registerBlock(dark_oak_log,"dark_oak_log");
        GameRegistry.registerBlock(jungle_log,"jungle_log");
        GameRegistry.registerBlock(oak_log,"oak_log");
        GameRegistry.registerBlock(spruce_log,"spruce_log");
        GameRegistry.registerBlock(pumpkin,"pumpkin");
        GameRegistry.registerItem(ura_nugget,"ura_nugget");
        GameRegistry.registerItem(argent_nugget,"argent_nugget");
        GameRegistry.registerBlock(bedrock_stairs,"bedrock_stairs");
        GameRegistry.registerBlock(ura_block,"ura_block");
        GameRegistry.registerBlock(neodyme_block,"neodyme_block");
        GameRegistry.registerBlock(argent_block,"argent_block");
        GameRegistry.registerBlock(obsi_stairs,"obsidian_stairs");
        GameRegistry.registerBlock(dirt_stairs,"dirt_stairs");
        GameRegistry.registerBlock(concrete_black,"concrete_black");
        GameRegistry.registerBlock(concrete_black_powder,"concrete_black_powder");
        GameRegistry.registerBlock(concrete_blue,"concrete_blue");
        GameRegistry.registerBlock(concrete_blue_powder,"concrete_blue_powder");
        GameRegistry.registerBlock(concrete_brown,"concrete_brown");
        GameRegistry.registerBlock(concrete_brown_powder,"concrete_brown_powder");
        GameRegistry.registerBlock(concrete_cyan,"concrete_cyan");
        GameRegistry.registerBlock(concrete_cyan_powder,"concrete_cyan_powder");
        GameRegistry.registerBlock(concrete_gray,"concrete_gray");
        GameRegistry.registerBlock(concrete_gray_powder,"concrete_gray_powder");
        GameRegistry.registerBlock(concrete_green,"concrete_green");
        GameRegistry.registerBlock(concrete_green_powder,"concrete_green_powder");
        GameRegistry.registerBlock(concrete_light_blue,"concrete_light_blue");
        GameRegistry.registerBlock(concrete_light_blue_powder,"concrete_light_blue_powder");
        GameRegistry.registerBlock(concrete_lime,"concrete_lime");
        GameRegistry.registerBlock(concrete_lime_powder,"concrete_lime_powder");
        GameRegistry.registerBlock(concrete_magenta,"concrete_magenta");
        GameRegistry.registerBlock(concrete_magenta_powder,"concrete_magenta_powder");
        GameRegistry.registerBlock(concrete_orange,"concrete_orange");
        GameRegistry.registerBlock(concrete_orange_powder,"concrete_orange_powder");
        GameRegistry.registerBlock(concrete_pink,"concrete_pink");
        GameRegistry.registerBlock(concrete_pink_powder,"concrete_pink_powder");
        GameRegistry.registerBlock(concrete_purple,"concrete_purple");
        GameRegistry.registerBlock(concrete_purple_powder,"concrete_purple_powder");
        GameRegistry.registerBlock(concrete_red,"concrete_red");
        GameRegistry.registerBlock(concrete_red_powder,"concrete_red_powder");
        GameRegistry.registerBlock(concrete_silver,"concrete_silver");
        GameRegistry.registerBlock(concrete_silver_powder,"concrete_silver_powder");
        GameRegistry.registerBlock(concrete_white,"concrete_white");
        GameRegistry.registerBlock(concrete_white_powder,"concrete_white_powder");
        GameRegistry.registerBlock(concrete_yellow,"concrete_yellow");
        GameRegistry.registerBlock(concrete_yellow_powder,"concrete_yellow_powder");
        GameRegistry.registerBlock(GreenScreenBlock,"green_screen_block");
        GameRegistry.registerBlock(YellowScreenBlock,"yellow_screen_block");
        GameRegistry.registerBlock(RedScreenBlock,"red_screen_block");
        GameRegistry.registerBlock(BlueScreenBlock,"blue_screen_block");
        GameRegistry.registerBlock(CaveBlock,"cave_block");
        GameRegistry.registerItem(UraIngot,"ura_ingot");
        GameRegistry.registerItem(ArgentIngot,"argent_ingot");
        GameRegistry.registerItem(NeodymeIngot,"neodyme_ingot");
        GameRegistry.registerBlock(UraOre, "ura_ore");
        GameRegistry.registerBlock(ArgentOre, "argent_ore");
        GameRegistry.registerBlock(NeodymeOre, "neodyme_ore");
        GameRegistry.registerWorldGenerator(new  WorldGeneratorUra(),2);

    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ura_culture = new Iridium_Croops().setBlockName("ura_culture").setBlockTextureName(MODID+":ura_carrots");
        iridium_culture = new Ura_Croops().setBlockName("iridium_culture").setBlockTextureName(MODID+":iridium_potatoes");
        betterave_culture = new Betterave_Croops().setBlockName("betterave_culture").setBlockTextureName(MODID+":beetroots");
        ura_seed = new Item_Seed(ura_culture, Blocks.farmland).setCreativeTab(UraModCreativeTabs).setTextureName(MODID+":ura_seed").setUnlocalizedName("ura_seed");
        iridium_seed = new Item_Seed(iridium_culture, Blocks.farmland).setCreativeTab(UraModCreativeTabs).setTextureName(MODID+":iridium_seed").setUnlocalizedName("iridium_seed");
        betterave_seeds = new Item_Seed(betterave_culture, Blocks.farmland).setCreativeTab(FururModCreativeTabs).setTextureName(MODID+":betterave_seeds").setUnlocalizedName("betterave_seeds");

        GameRegistry.registerTileEntity(TileEntityShulkerBox.class, "uramod:teshulkerbox");
        GameRegistry.registerTileEntity(IriduimSmithingTableTileEntity.class, "UraMod:IriduimSmithingTableTileEntity");
        GameRegistry.registerTileEntity(SmithingTableTileEntity.class, "UraMod:SmithingTableTileEntity");
        GameRegistry.registerTileEntity(TileEntityUraFurnace.class, "UraMod:TileEntityUraFurnace");
        GameRegistry.registerTileEntity(TileEntityUraChest.class, "UraMod:TileEntityUraChest");
        GameRegistry.registerTileEntity(TileEntityWoodConverter.class, "UraMod:TileEntityWoodConverter");
        iridium_nugget = new UraItem().setUnlocalizedName("iridium_nugget").setTextureName(MODID + ":iridium_nugget").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        iridium_ingot = new UraItem().setUnlocalizedName("iridium_ingot").setTextureName(MODID + ":iridium_ingot").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        ura_sword = new ItemModSword(ura_tool).setUnlocalizedName("ura_sword").setTextureName(MODID + ":ura_sword").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        ura_pickaxe = new ItemModPickaxe(ura_tool).setUnlocalizedName("ura_pickaxe").setTextureName(MODID + ":ura_pickaxe").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        ura_axe = new ItemModAxe(ura_tool).setUnlocalizedName("ura_axe").setTextureName(MODID + ":ura_axe").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        ura_shovel = new ItemModShovel(ura_tool).setUnlocalizedName("ura_shovel").setTextureName(MODID + ":ura_shovel").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        ura_hoe = new ItemModHoe(ura_tool).setUnlocalizedName("ura_hoe").setTextureName(MODID + ":ura_hoe").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        ura_helmet = new ItemUraArmor(ura_armor, 0).setUnlocalizedName("ura_helmet").setTextureName(MODID + ":ura_helmet").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        ura_chestPlate = new ItemUraArmor(ura_armor, 1).setUnlocalizedName("ura_chestPlate").setTextureName(MODID + ":ura_chestplate").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        ura_leggings = new ItemUraArmor(ura_armor, 2).setUnlocalizedName("ura_leggings").setTextureName(MODID + ":ura_leggings").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        ura_boots = new ItemUraArmor(ura_armor, 3).setUnlocalizedName("ura_boots").setTextureName(MODID + ":ura_boots").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        argent_helmet = new ItemArgentArmor(argent_armor, 0).setUnlocalizedName("argent_helmet").setTextureName(MODID + ":argent_helmet").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        argent_chestPlate = new ItemArgentArmor(argent_armor, 1).setUnlocalizedName("argent_chestPlate").setTextureName(MODID + ":argent_chestplate").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        argent_leggings = new ItemArgentArmor(argent_armor, 2).setUnlocalizedName("argent_leggings").setTextureName(MODID + ":argent_leggings").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        argent_boots = new ItemArgentArmor(argent_armor, 3).setUnlocalizedName("argent_boots").setTextureName(MODID + ":argent_boots").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        iridium_helmet = new ItemIridiumArmor(iridium_armor, 0).setUnlocalizedName("iridium_helmet").setTextureName(MODID + ":iridium_helmet").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        iridium_chestPlate = new ItemIridiumArmor(iridium_armor, 1).setUnlocalizedName("iridium_chestPlate").setTextureName(MODID + ":iridium_chestplate").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        iridium_leggings = new ItemIridiumArmor(iridium_armor, 2).setUnlocalizedName("iridium_leggings").setTextureName(MODID + ":iridium_leggings").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        iridium_boots = new ItemIridiumArmor(iridium_armor, 3).setUnlocalizedName("iridium_boots").setTextureName(MODID + ":iridium_boots").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        argent_hoe = new ItemModHoe(ura_tool).setUnlocalizedName("argent_hoe").setTextureName(MODID + ":argent_hoe").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        finder = new ItemModFinder().setUnlocalizedName("finder").setTextureName("uramod:finder").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        argent_sword = new ItemModSword(argent_tool).setUnlocalizedName("argent_sword").setTextureName(MODID + ":argent_sword").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        argent_pickaxe = new ItemModPickaxe(argent_tool).setUnlocalizedName("argent_pickaxe").setTextureName(MODID + ":argent_pickaxe").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        argent_axe = new ItemModAxe(argent_tool).setUnlocalizedName("argent_axe").setTextureName(MODID + ":argent_axe").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        argent_shovel = new ItemModShovel(argent_tool).setUnlocalizedName("argent_shovel").setTextureName(MODID + ":argent_shovel").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        xp_berry = new XpBerry().setUnlocalizedName("xp_berry").setTextureName(MODID + ":xp_berrys").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        hang_glider = new UraItem().setUnlocalizedName("hang_glider").setTextureName(MODID + ":hang_glider").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        ura_particle = new UraItem().setUnlocalizedName("ura_particle").setTextureName(MODID + ":ura_particle").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        hammer = new ItemModHamer(hamer_tool).setUnlocalizedName("hammer").setTextureName(MODID + ":hammer").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        ura_bow = new UraBow().setUnlocalizedName("ura_bow").setTextureName(MODID + ":ura_bow").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        jump_chestplate = new ItemTravelArmor(travel_armor, 1).setUnlocalizedName("jump_chestplate").setTextureName(MODID + ":jump_chestplate").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        scuba_helmet = new ItemTravelArmor(travel_armor, 0).setUnlocalizedName("scuba_helmet").setTextureName(MODID + ":scuba_helmet").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        farmer_boots = new ItemTravelArmor(travel_armor, 3).setUnlocalizedName("farmer_boots").setTextureName(MODID + ":farmer_boots").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        seed_planter = new SeedPlanter().setUnlocalizedName("seed_planter").setTextureName(MODID + ":seed_planter").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        speed_leggings = new ItemTravelArmor(travel_armor, 2).setUnlocalizedName("speed_leggings").setTextureName(MODID + ":travel_leggings").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        chisel = new UraItem().setUnlocalizedName("chisel").setTextureName(MODID + ":chisel").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        prismarin_crystal = new UraItem().setUnlocalizedName("prismarin_crystal").setTextureName(MODID + ":prismarine_crystals").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        prismarin_fragment = new UraItem().setUnlocalizedName("prismarin_fragment").setTextureName(MODID + ":prismarine_shard").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        crossbow = new UraItem().setUnlocalizedName("crossbow").setTextureName(MODID + ":crossbow").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        sweetberry = new UraItem().setUnlocalizedName("sweetberry").setTextureName(MODID + ":sweet_berries").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        honey_jars = new UraItem().setUnlocalizedName("honey_jars").setTextureName(MODID + ":honey_bottle").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        honeycombs = new UraItem().setUnlocalizedName("honeycombs").setTextureName(MODID + ":honeycomb").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        Key01 = new UraItem().setUnlocalizedName("Neodyme_Key").setTextureName(MODID + ":Key01").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        Key02 = new UraItem().setUnlocalizedName("Silver_Key").setTextureName(MODID + ":Key02").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        Key04 = new UraItem().setUnlocalizedName("Ura_Key").setTextureName(MODID + ":Key04").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        Key05 = new UraItem().setUnlocalizedName("Iridium_Key").setTextureName(MODID + ":Key05").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        argent_particle = new UraItem().setUnlocalizedName("argent_particle").setTextureName(MODID + ":argent_particle").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        iridium_sword = new ItemModSword(iridium_tool).setUnlocalizedName("iridium_sword").setTextureName(MODID + ":iridium_sword").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        iridium_pickaxe = new ItemModPickaxe(iridium_tool).setUnlocalizedName("iridium_pickaxe").setTextureName(MODID + ":iridium_pickaxe").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        backpack = new Backpack();
        iridium_particle = new UraItem().setUnlocalizedName("iridium_particle").setTextureName(MODID + ":iridium_particle").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        blockturner = new BlockTurner().setUnlocalizedName("blockturner").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        halloween_helmet = new ItemHalloweenArmor(halloween_armor, 0).setUnlocalizedName("halloween_helmet").setTextureName(MODID + ":halloween_helmet").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        halloween_chestplate = new ItemHalloweenArmor(halloween_armor, 1).setUnlocalizedName("halloween_chestplate").setTextureName(MODID + ":halloween_chestplate").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        halloween_leggings = new ItemHalloweenArmor(halloween_armor, 2).setUnlocalizedName("halloween_leggings").setTextureName(MODID + ":halloween_leggings").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        halloween_boots = new ItemHalloweenArmor(halloween_armor, 3).setUnlocalizedName("halloween_boots").setTextureName(MODID + ":halloween_boots").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        halloween_sword = new ItemModSword(halloween_tools).setUnlocalizedName("halloween_sword").setTextureName(MODID + ":halloween_sword").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        halloween_pickaxe = new ItemModPickaxe(halloween_tools).setUnlocalizedName("halloween_pickaxe").setTextureName(MODID + ":halloween_pickaxe").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        halloween_axe = new ItemModAxe(halloween_tools).setUnlocalizedName("halloween_axe").setTextureName(MODID + ":halloween_axe").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        halloween_shovel = new ItemModShovel(halloween_tools).setUnlocalizedName("halloween_shovel").setTextureName(MODID + ":halloween_shovel").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        halloween_hoe = new ItemModHoe(halloween_tools).setUnlocalizedName("halloween_hoe").setTextureName(MODID + ":halloween_hoe").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        netherite_sword = new ItemModSword(netherite_tools).setUnlocalizedName("netherite_sword").setTextureName(MODID + ":netherite_sword").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        netherite_pickaxe = new ItemModPickaxe(netherite_tools).setUnlocalizedName("netherite_pickaxe").setTextureName(MODID + ":netherite_pickaxe").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        netherite_axe = new ItemModAxe(netherite_tools).setUnlocalizedName("netherite_axe").setTextureName(MODID + ":netherite_axe").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        netherite_shovel = new ItemModShovel(netherite_tools).setUnlocalizedName("netherite_shovel").setTextureName(MODID + ":netherite_shovel").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        netherite_hoe = new ItemModHoe(netherite_tools).setUnlocalizedName("netherite_hoe").setTextureName(MODID + ":netherite_hoe").setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        creeper_helmet = new ItemCreeperArmor(creeper_armor, 0).setUnlocalizedName("creeper_helmet").setTextureName(MODID + ":creeper_helmet").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        creeper_chestplate = new ItemCreeperArmor(creeper_armor, 1).setUnlocalizedName("creeper_chestplate").setTextureName(MODID + ":creeper_chestplate").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        creeper_leggings = new ItemCreeperArmor(creeper_armor, 2).setUnlocalizedName("creeper_leggings").setTextureName(MODID + ":creeper_leggings").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        creeper_boots = new ItemCreeperArmor(creeper_armor, 3).setUnlocalizedName("creeper_boots").setTextureName(MODID + ":creeper_boots").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        zombie_helmet = new ItemZombieArmor(zombie_armor, 0).setUnlocalizedName("zombie_helmet").setTextureName(MODID + ":zombie_helmet").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        zombie_chestplate = new ItemZombieArmor(zombie_armor, 1).setUnlocalizedName("zombie_chestplate").setTextureName(MODID + ":zombie_chestplate").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        zombie_leggings = new ItemZombieArmor(zombie_armor, 2).setUnlocalizedName("zombie_leggings").setTextureName(MODID + ":zombie_leggings").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        zombie_boots = new ItemZombieArmor(zombie_armor, 3).setUnlocalizedName("zombie_boots").setTextureName(MODID + ":zombie_boots").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        skeleton_helmet = new ItemSkeletonArmor(skeleton_armor, 0).setUnlocalizedName("skeleton_helmet").setTextureName(MODID + ":skeleton_helmet").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        skeleton_chestplate = new ItemSkeletonArmor(skeleton_armor, 1).setUnlocalizedName("skeleton_chestplate").setTextureName(MODID + ":skeleton_chestplate").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        skeleton_leggings = new ItemSkeletonArmor(skeleton_armor, 2).setUnlocalizedName("skeleton_leggings").setTextureName(MODID + ":skeleton_leggings").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        skeleton_boots = new ItemSkeletonArmor(skeleton_armor, 3).setUnlocalizedName("skeleton_boots").setTextureName(MODID + ":skeleton_boots").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        witch_helmet = new ItemWitchArmor(witch_armor, 0).setUnlocalizedName("witch_helmet").setTextureName(MODID + ":witch_helmet").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        witch_chestplate = new ItemWitchArmor(witch_armor, 1).setUnlocalizedName("witch_chestplate").setTextureName(MODID + ":witch_chestplate").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        witch_leggings = new ItemWitchArmor(witch_armor, 2).setUnlocalizedName("witch_leggings").setTextureName(MODID + ":witch_leggings").setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        witch_boots = new ItemWitchArmor(witch_armor, 3).setUnlocalizedName("witch_boots").setTextureName(MODID + ":witch_boots").setCreativeTab(Ura_ModMain.UraModCreativeTabs);

        //Entities
        EntityRegistry.registerGlobalEntityID(EntityMobHalloween.class, "mobHalloween", EntityRegistry.findGlobalUniqueEntityId(), new Color(0, 255, 0).getRGB(), new Color(255, 0, 0).getRGB());
        EntityRegistry.registerModEntity(EntityMobHalloween.class, "mobHalloween", 420, this.instance, 40, 1, true);

        BiomeGenBase[] biomes = new BiomeGenBase[37];
        biomes[0] = BiomeGenBase.beach;
        biomes[1] = BiomeGenBase.birchForest;
        biomes[2] = BiomeGenBase.birchForestHills;
        biomes[3] = BiomeGenBase.coldBeach;
        biomes[4] = BiomeGenBase.coldTaiga;
        biomes[5] = BiomeGenBase.coldTaigaHills;
        biomes[6] = BiomeGenBase.deepOcean;
        biomes[7] = BiomeGenBase.desert;
        biomes[8] = BiomeGenBase.desertHills;
        biomes[9] = BiomeGenBase.extremeHills;
        biomes[10] = BiomeGenBase.extremeHillsEdge;
        biomes[11] = BiomeGenBase.extremeHillsPlus;
        biomes[12] = BiomeGenBase.forest;
        biomes[13] = BiomeGenBase.forestHills;
        biomes[14] = BiomeGenBase.frozenOcean;
        biomes[15] = BiomeGenBase.frozenRiver;
        biomes[16] = BiomeGenBase.hell;
        biomes[17] = BiomeGenBase.iceMountains;
        biomes[18] = BiomeGenBase.icePlains;
        biomes[19] = BiomeGenBase.jungle;
        biomes[20] = BiomeGenBase.jungleEdge;
        biomes[21] = BiomeGenBase.jungleHills;
        biomes[22] = BiomeGenBase.megaTaiga;
        biomes[23] = BiomeGenBase.megaTaigaHills;
        biomes[24] = BiomeGenBase.mesa;
        biomes[25] = BiomeGenBase.mesaPlateau;
        biomes[26] = BiomeGenBase.mesaPlateau_F;
        biomes[27] = BiomeGenBase.mushroomIsland;
        biomes[28] = BiomeGenBase.mushroomIslandShore;
        //biomes[29] = BiomeGenBase.ocean;
        //biomes[30] = BiomeGenBase.river;
        biomes[29] = BiomeGenBase.roofedForest;
        biomes[30] = BiomeGenBase.savanna;
        biomes[31] = BiomeGenBase.savannaPlateau;
        //biomes[34] = BiomeGenBase.sky;
        biomes[32] = BiomeGenBase.stoneBeach;
        biomes[33] = BiomeGenBase.swampland;
        biomes[34] = BiomeGenBase.taiga;
        biomes[35] = BiomeGenBase.taigaHills;
        biomes[36] = BiomeGenBase.plains;

        EntityRegistry.addSpawn(EntityMobHalloween.class, 100, 1, 1, EnumCreatureType.monster, biomes);

        GameRegistry.registerItem(creeper_helmet,"creeper_helmet");
        GameRegistry.registerItem(creeper_chestplate,"creeper_chestplate");
        GameRegistry.registerItem(creeper_leggings,"creeper_leggings");
        GameRegistry.registerItem(creeper_boots,"creeper_boots");
        GameRegistry.registerItem(zombie_helmet,"zombie_helmet");
        GameRegistry.registerItem(zombie_chestplate,"zombie_chestplate");
        GameRegistry.registerItem(zombie_leggings,"zombie_leggings");
        GameRegistry.registerItem(zombie_boots,"zombie_boots");
        GameRegistry.registerItem(skeleton_helmet,"skeleton_helmet");
        GameRegistry.registerItem(skeleton_chestplate,"skeleton_chestplate");
        GameRegistry.registerItem(skeleton_leggings,"skeleton_leggings");
        GameRegistry.registerItem(skeleton_boots,"skeleton_boots");
        GameRegistry.registerItem(witch_helmet,"witch_helmet");
        GameRegistry.registerItem(witch_chestplate,"witch_chestplate");
        GameRegistry.registerItem(witch_leggings,"witch_leggings");
        GameRegistry.registerItem(witch_boots,"witch_boots");
        GameRegistry.registerItem(netherite_sword,"netherite_sword");
        GameRegistry.registerItem(netherite_pickaxe,"netherite_pickaxe");
        GameRegistry.registerItem(netherite_axe,"netherite_axe");
        GameRegistry.registerItem(netherite_shovel,"netherite_shovel");
        GameRegistry.registerItem(netherite_hoe,"netherite_hoe");
        GameRegistry.registerItem(halloween_sword,"halloween_sword");
        GameRegistry.registerItem(halloween_pickaxe,"halloween_pickaxe");
        GameRegistry.registerItem(halloween_axe,"halloween_axe");
        GameRegistry.registerItem(halloween_shovel,"halloween_shovel");
        GameRegistry.registerItem(halloween_hoe,"halloween_hoe");
        NetworkRegistry.INSTANCE.registerGuiHandler("uramod", new GuiHandler());
        GameRegistry.registerItem(halloween_helmet,"halloween_helmet");
        GameRegistry.registerItem(halloween_chestplate,"halloween_chestplate");
        GameRegistry.registerItem(halloween_leggings,"halloween_leggings");
        GameRegistry.registerItem(halloween_boots,"halloween_boots");
        GameRegistry.registerBlock(betterave_culture,"betterave_culture");
        GameRegistry.registerItem(betterave_seeds,"betterave_seeds");
        GameRegistry.registerBlock(iridium_culture,"iridium_culture");
        GameRegistry.registerBlock(ura_culture,"ura_culture");
        GameRegistry.registerItem(ura_seed,"ura_seed");
        GameRegistry.registerItem(iridium_seed,"iridium_seed");
        //MinecraftForge.addGrassSeed(new ItemStack(iridium_seed),1);
        //MinecraftForge.addGrassSeed(new ItemStack(ura_seed),3);
        GameRegistry.registerItem(blockturner,"blockturner");
        GameRegistry.registerItem(iridium_particle,"iridium_particle");
        GameRegistry.registerItem(backpack,"backpack");
        GameRegistry.registerItem(iridium_sword,"iridium_sword");
        GameRegistry.registerItem(iridium_pickaxe,"iridium_pickaxe");
        GameRegistry.registerItem(argent_particle,"argent_particle");
        GameRegistry.registerItem(betterave,"betterave");
        GameRegistry.registerItem(bouclier,"bouclier");
        GameRegistry.registerItem(chorus,"chorus");
        GameRegistry.registerItem(ender_crystal,"ender_crystal");
        GameRegistry.registerItem(chorus_eclate,"chorus_eclate");
        GameRegistry.registerItem(elytres,"elytres");
        GameRegistry.registerItem(fleche_spectrale,"fleche_spectrale");
        GameRegistry.registerItem(fleche_spatiale,"fleche_spatiale");
        GameRegistry.registerItem(soufle_dragon,"soufle_dragon");
        GameRegistry.registerItem(soupe_betteraves,"soupe_betteraves");
        GameRegistry.registerItem(Key01,"Neodyme_Key");
        GameRegistry.registerItem(Key02,"Silver_Key");
        GameRegistry.registerItem(Key04,"Ura_Key");
        GameRegistry.registerItem(Key05,"Iridium_Key");
        GameRegistry.registerItem(prismarin_crystal,"prismarin_crystal");
        GameRegistry.registerItem(prismarin_fragment,"prismarin_fragment");
        GameRegistry.registerItem(crossbow,"crossbow");
        GameRegistry.registerItem(sweetberry,"sweetberry");
        GameRegistry.registerItem(honey_jars,"honey_jars");
        GameRegistry.registerItem(honeycombs,"honeycombs");
        GameRegistry.registerItem(chisel,"chisel");
        GameRegistry.registerItem(xp_berry,"xp_berry");
        GameRegistry.registerItem(hang_glider,"hang_glider");
        GameRegistry.registerItem(ura_particle,"ura_particle");
        GameRegistry.registerItem(hammer,"hammer");
        GameRegistry.registerItem(ura_bow,"ura_bow");
        GameRegistry.registerItem(jump_chestplate,"jump_chestplate");
        GameRegistry.registerItem(scuba_helmet,"scuba_helmet");
        GameRegistry.registerItem(farmer_boots,"farmer_boots");
        GameRegistry.registerItem(seed_planter,"seed_planter");
        GameRegistry.registerItem(speed_leggings,"speed_leggings");
        GameRegistry.registerItem(iridium_nugget,"iridium_nugget");
        GameRegistry.registerItem(iridium_ingot,"iridium_ingot");
        GameRegistry.registerItem(finder,"finder");
        GameRegistry.registerItem(ura_helmet,"ura_helmet");
        GameRegistry.registerItem(ura_chestPlate,"ura_chestPlate");
        GameRegistry.registerItem(ura_leggings,"ura_leggings");
        GameRegistry.registerItem(ura_boots,"ura_boots");
        GameRegistry.registerItem(argent_helmet,"argent_helmet");
        GameRegistry.registerItem(argent_chestPlate,"argent_chestPlate");
        GameRegistry.registerItem(argent_leggings,"argent_leggings");
        GameRegistry.registerItem(argent_boots,"argent_boots");
        GameRegistry.registerItem(iridium_helmet,"iridium_helmet");
        GameRegistry.registerItem(iridium_chestPlate,"iridium_chestPlate");
        GameRegistry.registerItem(iridium_leggings,"iridium_leggings");
        GameRegistry.registerItem(iridium_boots,"iridium_boots");
        GameRegistry.registerItem(ura_axe,"ura_axe");
        GameRegistry.registerItem(ura_pickaxe,"ura_pickaxe");
        GameRegistry.registerItem(ura_hoe,"ura_hoe");
        GameRegistry.registerItem(ura_shovel,"ura_shovel");
        GameRegistry.registerItem(ura_sword,"ura_sword");
        GameRegistry.registerItem(argent_axe,"argent_axe");
        GameRegistry.registerItem(argent_pickaxe,"argent_pickaxe");
        GameRegistry.registerItem(argent_hoe,"argent_hoe");
        GameRegistry.registerItem(argent_shovel,"argent_shovel");
        GameRegistry.registerItem(argent_sword,"argent_sword");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //craft//
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //experience_bottle
        GameRegistry.addShapelessRecipe(new ItemStack(Items.experience_bottle), new Object[]{ new ItemStack(Items.glass_bottle ), new ItemStack(xp_berry )});
        //Wood_converter
        GameRegistry.addRecipe(new ItemStack(wood_converter), new Object[]{"BWS","OEA","JED",'B',Blocks.log2,'W',Items.wooden_axe,'S',new ItemStack(Blocks.log,1, 1).getItem(),'O',Blocks.log,'E',Items.ender_pearl,'A',Blocks.log2,'J',new ItemStack(Blocks.log,1, 3).getItem(),'D',new ItemStack(Blocks.log2,1, 1).getItem()});
        //Obsidian_stairs
        GameRegistry.addRecipe(new ItemStack(obsi_stairs, 4), new Object[]{"O  ","OO ", "OOO",'O',Blocks.obsidian});
        //Dirt_stairs
        GameRegistry.addRecipe(new ItemStack(dirt_stairs, 4), new Object[]{"O  ","OO ", "OOO",'O',Blocks.dirt});
        //Yellow Luminous Block
        GameRegistry.addRecipe(new ItemStack(YellowScreenBlock), new Object[]{"YYY","YGY","YYY",'Y',new ItemStack(Items.dye,1,11),'G',Blocks.glowstone});
        //Red Luminous Block
        GameRegistry.addRecipe(new ItemStack(RedScreenBlock), new Object[]{"YYY","YGY","YYY",'Y',new ItemStack(Items.dye,1,1),'G',Blocks.glowstone});
        //Blue Luminous Block
        GameRegistry.addRecipe(new ItemStack(BlueScreenBlock), new Object[]{"YYY","YGY","YYY",'Y',new ItemStack(Items.dye,1,4),'G',Blocks.glowstone});
        //Green Luminous Block
        GameRegistry.addRecipe(new ItemStack(GreenScreenBlock), new Object[]{"YYY","YGY","YYY",'Y',new ItemStack(Items.dye,1,2),'G',Blocks.glowstone});
        //Find Block
        GameRegistry.addRecipe(new ItemStack(CaveBlock),new Object[]{"GGG","GNG","GGG",'G',Blocks.glass,'N',NeodymeIngot});
        //Diamond Upgarde
        GameRegistry.addRecipe(new ItemStack(diamond_upgarde),new Object[]{"OOO","ODO","OOO",'O',Blocks.obsidian,'D',Blocks.diamond_block});
        //Silver Upgrade
        GameRegistry.addRecipe(new ItemStack(argent_upgarde),new Object[]{"OOO","ODO","OOO",'O',Blocks.obsidian,'D',argent_block});
        //Ura Upgrade
        GameRegistry.addRecipe(new ItemStack(ura_upgarde),new Object[]{"OOO","ODO","OOO",'O',Blocks.obsidian,'D',ura_block});
        //Iridium Upgrade
        GameRegistry.addRecipe(new ItemStack(iridium_upgarde),new Object[]{"OOO","ODO","OOO",'O',Blocks.obsidian,'D',iridium_ingot});
        //Neodyme Upgrade
        GameRegistry.addRecipe(new ItemStack(neodyme_upgarde),new Object[]{"OOO","ODO","OOO",'O',Blocks.obsidian,'D',neodyme_block});
        //Ura Furnace
        GameRegistry.addRecipe(new ItemStack(urafurnace),new Object[]{"OOO","ODO","OOO",'O',UraIngot,'D',Blocks.furnace});
        //Hammer
        GameRegistry.addRecipe(new ItemStack(hammer),new Object[]{"OOO","OOO"," S ",'O',UraIngot,'S',Items.stick});
        //Backpack
        GameRegistry.addRecipe(new ItemStack(backpack),new Object[]{"OOO","OBO","OOO",'O',Items.leather , 'B' ,Blocks.chest});
        //Ura Bow
        GameRegistry.addRecipe(new ItemStack(ura_bow),new Object[]{" US","B  "," US",'U',UraIngot,'S',Items.string,'B',Items.stick});
        //Hang Glider
        GameRegistry.addRecipe(new ItemStack(hang_glider),new Object[]{"PSP","ISI","LSL",'P',Items.paper,'S',Items.stick,'I',Items.iron_ingot,'L',Items.leather});
        //Finder
        GameRegistry.addRecipe(new ItemStack(finder),new Object[]{"CFC","GNG","CFC",'C',Blocks.chest,'F',Blocks.furnace,'G',Blocks.glass,'N',NeodymeIngot});
        //Portative Crafting Table
        GameRegistry.addRecipe(new ItemStack(portalble_carfting_table),new Object[]{"LLL","LTL","LML",'L',Items.leather,'T',Blocks.crafting_table,'M',Items.map});
        //iridium table
        GameRegistry.addRecipe(new ItemStack(iriduim_smithing_table),new Object[]{"HUL","CPB","USU",'H',argent_helmet,'U',ura_block,'L',argent_leggings,'C',argent_chestPlate,'P',argent_pickaxe,'B',argent_boots,'S',argent_sword});
        //Scuba Helmet
        GameRegistry.addRecipe(new ItemStack(scuba_helmet),new Object[]{"LLL","LIL","LLL",'L',Items.leather,'I',Items.water_bucket});
        //Jump Chestplate
        GameRegistry.addRecipe(new ItemStack(jump_chestplate),new Object[]{"LLL","LIL","LLL",'L',Items.leather,'I',Items.slime_ball});
        //Speed leggings
        GameRegistry.addRecipe(new ItemStack(speed_leggings),new Object[]{"LLL","LIL","LLL",'L',Items.leather,'I',Items.feather});
        //Farm boots
        GameRegistry.addRecipe(new ItemStack(farmer_boots),new Object[]{"LLL","LIL","LLL",'L',Items.leather,'I',ura_hoe});
        //Seed Planter
        GameRegistry.addRecipe(new ItemStack(seed_planter),new Object[]{"UBU","SDS","SSS",'U',UraIngot,'B',Items.water_bucket,'S',Items.wheat_seeds,'D',Blocks.dirt});
        //Accelero Planter
        GameRegistry.addRecipe(new ItemStack(acceleroplanter),new Object[]{"ABA","SDW","AAA",'A',ArgentIngot,'S',Items.wheat_seeds,'W',Items.wheat,'D',Blocks.dirt,'B',Items.water_bucket});
        //Ura Barrel
        GameRegistry.addRecipe(new ItemStack(urachest),new Object[]{"UUU","UCU","UUU",'U',UraIngot,'C',Blocks.chest});
        //Fake Lava
        GameRegistry.addShapelessRecipe(new ItemStack(fakelavabucket), new Object[]{ new ItemStack(Items.lava_bucket ), new ItemStack(Items.milk_bucket )});
        //Fake Water
        GameRegistry.addShapelessRecipe(new ItemStack(fakewaterbucket), new Object[]{ new ItemStack(Items.water_bucket ), new ItemStack(Items.diamond_sword )});
        //Glowstone liquide
        GameRegistry.addRecipe(new ItemStack(glowstoneliquidebucket),new Object[]{"GGG","GBG","GGG",'B',Items.bucket,'G',Blocks.glowstone});
        //Claim Stick
        GameRegistry.addShapelessRecipe(new ItemStack(claim_stick), new Object[]{ new ItemStack(Items.stick ), new ItemStack(Items.stick )});
        //BonesBlock
        GameRegistry.addRecipe(new ItemStack(bone_block), new Object[]{"BBB", "BBB", "BBB", 'B', new ItemStack(Items.dye,1,15)});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye,9,15), new Object[]{ new ItemStack(bone_block )});
        //Purpur Block
        GameRegistry.addRecipe(new ItemStack(purpur_block),new Object[]{"CC ","CC ","   ",'C',chorus_eclate});
        //End Rod
        GameRegistry.addRecipe(new ItemStack(end_rode),new Object[]{" B "," C ","   ",'B',Items.blaze_rod,'C',chorus_eclate});
        //Purpur Stairs
        GameRegistry.addRecipe(new ItemStack(purpur_block, 4),new Object[]{"P  ","PP ","PPP",'P',purpur_block});
        GameRegistry.addRecipe(new ItemStack(purpur_block, 4),new Object[]{"P  ","PP ","PPP",'P',purpur_pillar});
        //Purpur Pillar
        GameRegistry.addRecipe(new ItemStack(purpur_pillar,2),new Object[]{" P "," P ","   ",'P',purpur_slab});
        //Slime Block
        GameRegistry.addRecipe(new ItemStack(slime_block,1),new Object[]{"SSS","SSS","SSS",'S',Items.slime_ball});
        //Purpur Slab
        GameRegistry.addRecipe(new ItemStack(purpur_slab,4),new Object[]{"   "," PP","   ",'P',purpur_block});
        //Rock Slab
        GameRegistry.addRecipe(new ItemStack(rock_slab,4),new Object[]{"   "," PP","   ",'P',Blocks.stone});
        //Iron Trapdoor
        GameRegistry.addRecipe(new ItemStack(iron_trapdoor,2),new Object[]{"   ","II ","II ",'I',Items.iron_ingot});
        //Soupe de betterave
        GameRegistry.addRecipe(new ItemStack(soupe_betteraves,1),new Object[]{"BBB","BBB"," I ",'B',betterave,'I',Items.bowl});
        //Teinture rouge
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye,1,1), new Object[]{ new ItemStack(betterave)});
        //Sea Lantern
        GameRegistry.addRecipe(new ItemStack(sea_lanterne),new Object[]{"FCF","CCC","FCF",'F', prismarin_fragment,'C', prismarin_crystal});

        GameRegistry.addRecipe(new ItemStack(ura_block), new Object[]{"XXX", "XXX", "XXX", 'X', UraIngot,});
        GameRegistry.addRecipe(new ItemStack(argent_block), new Object[]{"XXX", "XXX", "XXX", 'X', ArgentIngot,});
        GameRegistry.addRecipe(new ItemStack(neodyme_block), new Object[]{"XXX", "XXX", "XXX", 'X', NeodymeIngot,});

        GameRegistry.addRecipe(new ItemStack(ura_nugget), new Object[]{"XXX", "XXX", "XXX", 'X', ura_particle,});
        GameRegistry.addRecipe(new ItemStack(argent_nugget), new Object[]{"XXX", "XXX", "XXX", 'X', argent_particle,});
        GameRegistry.addRecipe(new ItemStack(iridium_nugget), new Object[]{"XXX", "XXX", "XXX", 'X', iridium_particle,});

        GameRegistry.addRecipe(new ItemStack(UraIngot), new Object[]{"XXX", "XXX", "XXX", 'X', ura_nugget,});
        GameRegistry.addRecipe(new ItemStack(ArgentIngot), new Object[]{"XXX", "XXX", "XXX", 'X', argent_nugget,});
        GameRegistry.addRecipe(new ItemStack(iridium_ingot), new Object[]{"XXX", "XXX", "XXX", 'X', iridium_nugget,});

        //Ingots
        GameRegistry.addShapelessRecipe(new ItemStack(UraIngot, 9), new Object[]{ new ItemStack(ura_block )});
        GameRegistry.addShapelessRecipe(new ItemStack(ArgentIngot, 9), new Object[]{ new ItemStack(argent_block )});
        GameRegistry.addShapelessRecipe(new ItemStack(NeodymeIngot, 9), new Object[]{ new ItemStack(neodyme_block )});

        //Prismarin
        GameRegistry.addRecipe(new ItemStack(prismarin), new Object[]{"   ","OO ", "OO ",'O',prismarin_fragment});
        GameRegistry.addRecipe(new ItemStack(prismarin_bricks), new Object[]{"OOO","OOO", "OOO",'O',prismarin_fragment});
        GameRegistry.addRecipe(new ItemStack(dark_prismarin), new Object[]{"OOO","OTO", "OOO",'O',prismarin_fragment,'T',new ItemStack(Items.dye, 1, 0)});

        //Granite
        GameRegistry.addRecipe(new ItemStack(granite_smooth, 4), new Object[]{"OO ","OO ", "   ",'O',granite});
        GameRegistry.addRecipe(new ItemStack(granite_stairs, 4), new Object[]{"O  ","OO ", "OOO",'O',granite});
        GameRegistry.addRecipe(new ItemStack(granite_smooth_stairs, 4), new Object[]{"O  ","OO ", "OOO",'O',granite_smooth});
        GameRegistry.addRecipe(new ItemStack(granite_slab,4),new Object[]{"   ","PPP","   ",'P',granite});
        GameRegistry.addRecipe(new ItemStack(granite_smooth_slab,4),new Object[]{"   ","PPP","   ",'P',granite_smooth});
        GameRegistry.addRecipe(new ItemStack(granite_wall,6),new Object[]{"   ","PPP","PPP",'P',granite});
        GameRegistry.addRecipe(new ItemStack(polished_granite_wall,6),new Object[]{"   ","PPP","PPP",'P',granite_smooth});

        //Diorite
        GameRegistry.addRecipe(new ItemStack(diorite_smooth, 4), new Object[]{"OO ","OO ", "   ",'O',diorite});
        GameRegistry.addRecipe(new ItemStack(diorite_stairs, 4), new Object[]{"O  ","OO ", "OOO",'O',diorite});
        GameRegistry.addRecipe(new ItemStack(diorite_smooth_stairs, 4), new Object[]{"O  ","OO ", "OOO",'O',diorite_smooth});
        GameRegistry.addRecipe(new ItemStack(diorite_slab,4),new Object[]{"   ","PPP","   ",'P',diorite});
        GameRegistry.addRecipe(new ItemStack(diorite_smooth_slab,4),new Object[]{"   ","PPP","   ",'P',diorite_smooth});
        GameRegistry.addRecipe(new ItemStack(diorite_wall,6),new Object[]{"   ","PPP","PPP",'P',diorite});
        GameRegistry.addRecipe(new ItemStack(polished_diorite_wall,6),new Object[]{"   ","PPP","PPP",'P',diorite_smooth});

        //Andesite
        GameRegistry.addRecipe(new ItemStack(andesite_smooth, 4), new Object[]{"OO ","OO ", "   ",'O',andesite});
        GameRegistry.addRecipe(new ItemStack(andesite_stairs, 4), new Object[]{"O  ","OO ", "OOO",'O',andesite});
        GameRegistry.addRecipe(new ItemStack(andesite_smooth_stairs, 4), new Object[]{"O  ","OO ", "OOO",'O',andesite_smooth});
        GameRegistry.addRecipe(new ItemStack(andesite_slab,4),new Object[]{"   ","PPP","   ",'P',andesite});
        GameRegistry.addRecipe(new ItemStack(andesite_smooth_slab,4),new Object[]{"   ","PPP","   ",'P',andesite_smooth});
        GameRegistry.addRecipe(new ItemStack(andesite_wall,6),new Object[]{"   ","PPP","PPP",'P',andesite});
        GameRegistry.addRecipe(new ItemStack(polished_andesite_wall,6),new Object[]{"   ","PPP","PPP",'P',andesite_smooth});

        //Fences
        GameRegistry.addRecipe(new ItemStack(Blocks.fence, 3),new Object[]{"   ","PSP","PSP",'S',Items.stick,'P', Blocks.planks});
        GameRegistry.addRecipe(new ItemStack(acacia_fence, 3),new Object[]{"   ","PSP","PSP",'S',Items.stick,'P', new ItemStack(Blocks.planks, 1, 4)});
        GameRegistry.addRecipe(new ItemStack(birch_fence, 3),new Object[]{"   ","PSP","PSP",'S',Items.stick,'P' ,new ItemStack(Blocks.planks, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(dark_oak_fence, 3),new Object[]{"   ","PSP","PSP",'S',Items.stick,'P', new ItemStack(Blocks.planks, 1, 5)});
        GameRegistry.addRecipe(new ItemStack(jungle_fence, 3),new Object[]{"   ","PSP","PSP",'S',Items.stick,'P', new ItemStack(Blocks.planks, 1, 3)});
        GameRegistry.addRecipe(new ItemStack(spurce_fence, 3),new Object[]{"   ","PSP","PSP",'S',Items.stick,'P', new ItemStack(Blocks.planks, 1, 1)});

        //Fences Gates
        GameRegistry.addRecipe(new ItemStack(acacia_fencegate),new Object[]{" SS"," WW"," SS",'S',Items.stick,'W', new ItemStack(Blocks.planks, 1, 4)});
        GameRegistry.addRecipe(new ItemStack(birch_fencegate),new Object[]{" SS"," WW"," SS",'S',Items.stick,'W' ,new ItemStack(Blocks.planks, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(darkoak_fencegate),new Object[]{" SS"," WW"," SS",'S',Items.stick,'W', new ItemStack(Blocks.planks, 1, 5)});
        GameRegistry.addRecipe(new ItemStack(jungle_fencegate),new Object[]{" SS"," WW"," SS",'S',Items.stick,'W', new ItemStack(Blocks.planks, 1, 3)});
        GameRegistry.addRecipe(new ItemStack(spurce_fencegate),new Object[]{" SS"," WW"," SS",'S',Items.stick,'W', new ItemStack(Blocks.planks, 1, 1)});

        //Doors items
        GameRegistry.addRecipe(new ItemStack(acacia_door_item, 3), new Object[]{"XX ", "XX ", "XX ", 'X', new ItemStack(Blocks.planks, 1, 4)});
        GameRegistry.addRecipe(new ItemStack(birch_door_item, 3), new Object[]{"XX ", "XX ", "XX ", 'X', new ItemStack(Blocks.planks, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(dark_oak_door_item, 3), new Object[]{"XX ", "XX ", "XX ", 'X', new ItemStack(Blocks.planks, 1, 5)});
        GameRegistry.addRecipe(new ItemStack(jungle_door_item, 3), new Object[]{"XX ", "XX ", "XX ", 'X', new ItemStack(Blocks.planks, 1, 3)});
        GameRegistry.addRecipe(new ItemStack(spurce_door_item, 3), new Object[]{"XX ", "XX ", "XX ", 'X', new ItemStack(Blocks.planks, 1, 1)});

        //Halloween :
        GameRegistry.addRecipe(new ItemStack(halloween_helmet), new Object[]{"XXX", "X X", "   ", 'X', halloween_ingot});
        GameRegistry.addRecipe(new ItemStack(halloween_chestplate), new Object[]{"X X", "XXX", "XXX", 'X', halloween_ingot});
        GameRegistry.addRecipe(new ItemStack(halloween_leggings), new Object[]{"XXX", "X X", "X X", 'X', halloween_ingot});
        GameRegistry.addRecipe(new ItemStack(halloween_boots), new Object[]{"   ", "X X", "X X", 'X', halloween_ingot});
        GameRegistry.addRecipe(new ItemStack(halloween_pickaxe), new Object[]{"XXX", " B ", " B ", 'X', halloween_ingot,'B', Items.stick});
        GameRegistry.addRecipe(new ItemStack(halloween_pickaxe), new Object[]{"XX ", "XB ", " B ", 'X', halloween_ingot,'B', Items.stick});
        GameRegistry.addRecipe(new ItemStack(halloween_shovel), new Object[]{" X ", " B ", " B ", 'X', halloween_ingot,'B', Items.stick});
        GameRegistry.addRecipe(new ItemStack(halloween_sword), new Object[]{" X ", " X ", " B ", 'X', halloween_ingot,'B', Items.stick});
        GameRegistry.addRecipe(new ItemStack(halloween_hoe), new Object[]{"XX ", " B ", " B ", 'X', halloween_ingot,'B', Items.stick});
        GameRegistry.addRecipe(new ItemStack(halloween_block), new Object[]{"XXX", "XXX", "XXX", 'X', halloween_ingot});
        GameRegistry.addShapelessRecipe(new ItemStack(Ura_ModMain.halloween_nugget, 9), new Object[]{ new ItemStack(Ura_ModMain.halloween_ingot)});
        GameRegistry.addShapelessRecipe(new ItemStack(Ura_ModMain.halloween_ingot, 9), new Object[]{ new ItemStack(Ura_ModMain.halloween_block )});
        GameRegistry.addRecipe(new ItemStack(halloween_ingot), new Object[]{"XXX", "XXX", "XXX", 'X', new ItemStack(Ura_ModMain.halloween_nugget, 9)});

        //Skeleton
        GameRegistry.addRecipe(new ItemStack(skeleton_helmet), new Object[]{"XXX", "X X", "   ", 'X', Items.bone});
        GameRegistry.addRecipe(new ItemStack(skeleton_chestplate), new Object[]{"X X", "XXX", "XXX", 'X', Items.bone});
        GameRegistry.addRecipe(new ItemStack(skeleton_leggings), new Object[]{"XXX", "X X", "X X", 'X', Items.bone});
        GameRegistry.addRecipe(new ItemStack(skeleton_boots), new Object[]{"   ", "X X", "X X", 'X', Items.bone});

        //Creeper
        GameRegistry.addRecipe(new ItemStack(creeper_helmet), new Object[]{"XXX", "X X", "   ", 'X', Items.gunpowder});
        GameRegistry.addRecipe(new ItemStack(creeper_chestplate), new Object[]{"X X", "XXX", "XXX", 'X', Items.gunpowder});
        GameRegistry.addRecipe(new ItemStack(creeper_leggings), new Object[]{"XXX", "X X", "X X", 'X', Items.gunpowder});
        GameRegistry.addRecipe(new ItemStack(creeper_boots), new Object[]{"   ", "X X", "X X", 'X', Items.gunpowder});

        //Zombie
        GameRegistry.addRecipe(new ItemStack(zombie_helmet), new Object[]{"XXX", "X X", "   ", 'X', Items.rotten_flesh});
        GameRegistry.addRecipe(new ItemStack(zombie_chestplate), new Object[]{"X X", "XXX", "XXX", 'X', Items.rotten_flesh});
        GameRegistry.addRecipe(new ItemStack(zombie_leggings), new Object[]{"XXX", "X X", "X X", 'X', Items.rotten_flesh});
        GameRegistry.addRecipe(new ItemStack(zombie_boots), new Object[]{"   ", "X X", "X X", 'X', Items.rotten_flesh});

        //Witch
        GameRegistry.addRecipe(new ItemStack(witch_helmet), new Object[]{"XXX", "X X", "   ", 'X', Items.glass_bottle});
        GameRegistry.addRecipe(new ItemStack(witch_chestplate), new Object[]{"X X", "XXX", "XXX", 'X', Items.glass_bottle});
        GameRegistry.addRecipe(new ItemStack(witch_leggings), new Object[]{"XXX", "X X", "X X", 'X', Items.glass_bottle});
        GameRegistry.addRecipe(new ItemStack(witch_boots), new Object[]{"   ", "X X", "X X", 'X', Items.glass_bottle});

        //Netherite :
        GameRegistry.addRecipe(new ItemStack(netherite_ingot), new Object[]{"NNN", "NGG", "GG ", 'N', netherite_scrap, 'G', Items.gold_ingot});
        GameRegistry.addRecipe(new ItemStack(netherite_block), new Object[]{"NNN", "NNN", "NNN", 'N', netherite_scrap});
        GameRegistry.addShapelessRecipe(new ItemStack(netherite_ingot, 9), new Object[]{ new ItemStack(netherite_block)});
        GameRegistry.addRecipe(new ItemStack(blacksmith_table), new Object[]{"II ", "PP ", "PP ", 'I', Items.iron_ingot, 'P', Blocks.planks});

        //ura :
        GameRegistry.addRecipe(new ItemStack(ura_helmet), new Object[]{"XXX", "X X", "   ", 'X', UraIngot,});
        GameRegistry.addRecipe(new ItemStack(ura_chestPlate), new Object[]{"X X", "XXX", "XXX", 'X', UraIngot,});
        GameRegistry.addRecipe(new ItemStack(ura_leggings), new Object[]{"XXX", "X X", "X X", 'X', UraIngot,});
        GameRegistry.addRecipe(new ItemStack(ura_boots), new Object[]{"   ", "X X", "X X", 'X', UraIngot,});
        GameRegistry.addRecipe(new ItemStack(ura_pickaxe), new Object[]{"XXX", " B ", " B ", 'X', UraIngot,'B', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ura_axe), new Object[]{"XX ", "XB ", " B ", 'X', UraIngot,'B', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ura_shovel), new Object[]{" X ", " B ", " B ", 'X', UraIngot,'B', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ura_sword), new Object[]{" X ", " X ", " B ", 'X', UraIngot,'B', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ura_hoe), new Object[]{"XX ", " B ", " B ", 'X', UraIngot,'B', Items.stick});
        //argent :
        GameRegistry.addRecipe(new ItemStack(argent_helmet), new Object[]{"XXX", "X X", "   ", 'X', ArgentIngot,});
        GameRegistry.addRecipe(new ItemStack(argent_chestPlate), new Object[]{"X X", "XXX", "XXX", 'X', ArgentIngot,});
        GameRegistry.addRecipe(new ItemStack(argent_leggings), new Object[]{"XXX", "X X", "X X", 'X', ArgentIngot,});
        GameRegistry.addRecipe(new ItemStack(argent_boots), new Object[]{"   ", "X X", "X X", 'X', ArgentIngot,});
        GameRegistry.addRecipe(new ItemStack(argent_pickaxe), new Object[]{"XXX", " B ", " B ", 'X', ArgentIngot,'B', Items.stick});
        GameRegistry.addRecipe(new ItemStack(argent_axe), new Object[]{"XX ", "XB ", " B ", 'X', ArgentIngot,'B', Items.stick});
        GameRegistry.addRecipe(new ItemStack(argent_shovel), new Object[]{" X ", " B ", " B ", 'X', ArgentIngot,'B', Items.stick});
        GameRegistry.addRecipe(new ItemStack(argent_sword), new Object[]{" X ", " X ", " B ", 'X', ArgentIngot,'B', Items.stick});
        GameRegistry.addRecipe(new ItemStack(argent_hoe), new Object[]{"XX ", " B ", " B ", 'X', ArgentIngot,'B', Items.stick});

        //autres :
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //furnace//
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        GameRegistry.addSmelting(Ura_ModMain.UraOre,new ItemStack(UraIngot,1),1.0f);
        GameRegistry.addSmelting(Ura_ModMain.ArgentOre,new ItemStack(ArgentIngot,1),1.0f);
        GameRegistry.addSmelting(Ura_ModMain.NeodymeOre,new ItemStack(NeodymeIngot,1),1.0f);
        GameRegistry.addSmelting(Ura_ModMain.chorus,new ItemStack(chorus_eclate,1),1.0f);
        GameRegistry.addSmelting(Ura_ModMain.halloween_ore,new ItemStack(halloween_ingot,1),1.0f);
        GameRegistry.addSmelting(Ura_ModMain.ancient_debris,new ItemStack(netherite_scrap,1),1.0f);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        proxy.registerRender();
        proxy.registerOverlay();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}

