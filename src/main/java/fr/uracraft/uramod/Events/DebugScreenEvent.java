package fr.uracraft.uramod.Events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class DebugScreenEvent {

    private static int ores[][] = new int[][]{
            {0, 14, 12211667},
            {0, 14, 16760358},
            {0, 25, 12566463},
            {0, 25, 12565546},
            {0, 54, 14200723},
            {0, 12, 965309},
            {0, 64, 1381653},
            {0, 29, 9096077},
            {0, 29, 16755200},
            {0, 12, 15081480},
            {14, 16, 1594813}};
    private static String ores_names[] = new String[]{
            "tile.ura_ore.name",
            "tile.neodymium_ore.name",
            "tile.silver_ore.name",
            "tile.random_ore.name",
            "tile.oreIron.name",
            "tile.oreDiamond.name",
            "tile.oreCoal.name",
            "tile.oreEmerald.name",
            "tile.oreGold.name",
            "tile.oreRedstone.name",
            "tile.oreLapis.name"
    };

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderPre(RenderGameOverlayEvent.Pre event)
    {
        if(event.type == RenderGameOverlayEvent.ElementType.DEBUG)
        {
            Minecraft minecraft = Minecraft.getMinecraft();
            event.setCanceled(true);

            this.drawString(minecraft.fontRenderer, "UraCraft V3", 10, 10, 12211667);
            String fps = minecraft.debug.split(",",2)[0];
            int f = Integer.parseInt(fps.substring(0, fps.length() - 4));
            int s = f * 10 / minecraft.gameSettings.limitFramerate;
            this.drawString(Minecraft.getMinecraft().fontRenderer, fps,10,30,fpscolor(s));
            int angle = MathHelper.floor_double((double)(Minecraft.getMinecraft().thePlayer.rotationYaw * 4.0F/360.0F)+0.5D) & 3;
            String direction = Direction.directions[angle];
            double rot = (minecraft.thePlayer.rotationYaw - 90) % 360;
            if (rot < 0) {
                rot += 360.0;
            }
            if (0 <= rot && rot < 67.5) {
                direction = "North";
            } else if (67.5 <= rot && rot < 157.5) {
                direction = "East";
            } else if (157.5 <= rot && rot < 247.5) {
                direction = "South";
            } else if (247.5 <= rot && rot < 360.0) {
                direction = "West";
            }
            int x = (int) minecraft.thePlayer.posX;
            int y = (int) minecraft.thePlayer.posY;
            int z = (int) minecraft.thePlayer.posZ;
            this.drawString(minecraft.fontRenderer,"Biome: "+minecraft.theWorld.getBiomeGenForCoords(MathHelper.floor_double(minecraft.thePlayer.posX),MathHelper.floor_double(minecraft.thePlayer.posZ)).biomeName,10,70,16777215);
            this.drawString(minecraft.fontRenderer, "Direction: " + direction, 10, 60, 16777215);


            String coords = "XYZ : " + x + " / " + y + " / " + z;
            this.drawString(minecraft.fontRenderer, coords, 10, 50, 16777215);
            if ((int) minecraft.thePlayer.posY < 64 && (int) minecraft.thePlayer.posY > 1) {
                this.drawString(minecraft.fontRenderer, I18n.format("debug.oreSpawnText"), 10, 100, 16777215);
                drawOresSpawn(minecraft);
            }
        }

    }

    public void drawString(FontRenderer par1FontRenderer, String par2Str, int par3, int par4, int par5)
    {
        par1FontRenderer.drawStringWithShadow(par2Str, par3, par4, par5);
    }

    private int fpscolor(int fps) {
        switch (fps) {
            case 1:
                return 7274496;
            case 2:
                return 7274496;
            case 3:
                return 10158080;
            case 4:
                return 10158080;
            case 5:
                return 16711680;
            case 6:
                return 16728320;
            case 7:
                return 16745472;
            case 8:
                return 16776960;
            case 9:
                return 6422272;
            case 10:
                return 6422272;
        }
        return 0;
    }

    private void drawOresSpawn(Minecraft minecraft) {
        int y = (int) minecraft.thePlayer.posY;
        int draw_y = 120;
        for (int i = 0; i < 10; i++) {
            if (ores[i][0] < y && ores[i][1] > y) {
                this.drawString(minecraft.fontRenderer, I18n.format(ores_names[i]), 10, draw_y, ores[i][2]);
                draw_y = draw_y + 10;
            }
        }
    }
}