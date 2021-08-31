package fr.uracraft.uramod.Events;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.uracraft.uramod.client.MainMenu.GuiCustomIngameMenu;
import fr.uracraft.uramod.client.MainMenu.GuiCustomMainMenu;
import fr.uracraft.uramod.client.MainMenu.GuiCustomOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;

public class EventCustomMainMenu {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTick(TickEvent.ClientTickEvent event)
    {
        Minecraft mc = FMLClientHandler.instance().getClient();
        if(mc.currentScreen != null && mc.currentScreen.getClass().equals(GuiMainMenu.class))
        {
            mc.displayGuiScreen(new GuiCustomMainMenu());
        }

        if(mc.currentScreen != null && mc.currentScreen.getClass().equals(GuiOptions.class))
        {
            mc.displayGuiScreen(new GuiCustomOptions(Minecraft.getMinecraft().currentScreen, Minecraft.getMinecraft().gameSettings));
        }

        if(mc.currentScreen != null && mc.currentScreen.getClass().equals(GuiIngameMenu.class))
        {
            mc.displayGuiScreen(new GuiCustomIngameMenu());
        }
    }
}
