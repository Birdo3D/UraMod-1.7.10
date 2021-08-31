package fr.uracraft.uramod.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class UraFluids {

    public static Fluid fake_water;
    public static Fluid fake_lava;
    public static Fluid glowstone_liquide;

    public static void fluids(FMLPreInitializationEvent event) {

        fake_water = new Fluid("fake_water").setDensity(4000).setViscosity(500).setTemperature(286).setLuminosity(-1).setUnlocalizedName("fake_water");
        fake_lava = new Fluid("fake_lava").setDensity(4000).setViscosity(6000).setTemperature(286).setLuminosity(15).setUnlocalizedName("fake_lava");
        glowstone_liquide = new Fluid("glowstone_liquide").setDensity(-4000).setViscosity(2000).setTemperature(286).setLuminosity(15).setUnlocalizedName("glowstone_liquide");

        FluidRegistry.registerFluid(fake_water);
        fake_water = FluidRegistry.getFluid("fake_water");
        FluidRegistry.registerFluid(fake_lava);
        fake_lava = FluidRegistry.getFluid("fake_lava");
        FluidRegistry.registerFluid(glowstone_liquide);
        glowstone_liquide = FluidRegistry.getFluid("glowstone_liquide");
    }
}