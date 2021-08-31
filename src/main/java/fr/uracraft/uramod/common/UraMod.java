package fr.uracraft.uramod.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.uracraft.uramod.Blocks.wood_converter.PacketsWoodConverter;
import fr.uracraft.uramod.Events.*;
import fr.uracraft.uramod.Items.Armors.PatchVanillaArmors;
import fr.uracraft.uramod.Blocks.FluidDisplay.FluidDisplayTileEntity;
import fr.uracraft.uramod.client.gui.GuiHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "uramod", name = "UraMod", version = "2.0.0")

public class UraMod {
    @Instance("uramod")
    public static UraMod instance;
    public static final String MODID = "uramod";

    @SidedProxy(clientSide = "fr.uracraft.uramod.client.ClientProxy", serverSide = "fr.uracraft.uramod.common.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs uramodcreativetab = new UraModCreativeTab("uramodcreativetab");

    public static String ip = "uracraft.ddns.net";
    public static int port = 25565;
    public boolean SinglePlayer = true;
    public boolean BlockTurnerTurnBlock = false;

    public static SimpleNetworkWrapper network;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PatchVanillaArmors.armors(event);
        UraFluids.fluids(event);
        UraBlocks.blocks(event);
        UraItems.items(event);
        UraEnchantments.enchantments(event);

        GameRegistry.registerWorldGenerator(new UraWorldGenerator(), 2);

        if (event.getSide().isClient())
            NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        network = NetworkRegistry.INSTANCE.newSimpleChannel("WoodConverter");
        network.registerMessage(PacketsWoodConverter.Handler.class, PacketsWoodConverter.class, 0, Side.SERVER);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerRender();

        MinecraftForge.EVENT_BUS.register(new DebugScreenEvent());

        if (event.getSide().isClient()) {
            FMLCommonHandler.instance().bus().register(new EventCustomMainMenu());
        }

        MinecraftForge.EVENT_BUS.register(new EnchantmentsEvent());
        MinecraftForge.EVENT_BUS.register(new FluidsEvent());
        MinecraftForge.EVENT_BUS.register(new RandomOreEvent());
        MinecraftForge.EVENT_BUS.register(new NeodymiumEvent());
        MinecraftForge.EVENT_BUS.register(new HangGliderEvent());

        GameRegistry.registerTileEntity(FluidDisplayTileEntity.class, "FluidDisplayTileEntity");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}