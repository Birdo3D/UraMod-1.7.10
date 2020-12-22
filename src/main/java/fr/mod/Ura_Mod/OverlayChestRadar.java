package fr.mod.Ura_Mod;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

import java.util.Iterator;
import java.util.Map;

public class OverlayChestRadar extends Gui {
    private Minecraft mc;
    private String show;
    final ResourceLocation bg = new ResourceLocation("uramod", "textures/overlay/finder.png");
    FontRenderer fontRender;
    RenderItem itemRenderer = new RenderItem();

    public OverlayChestRadar() {
        this.mc = Minecraft.getMinecraft();
        this.show = "0%";
        this.fontRender = this.mc.fontRenderer;
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return;
        }
        if ((this.mc.thePlayer.getHeldItem() != null)
                && ((this.mc.thePlayer.getHeldItem().getItem() instanceof ItemModFinder))
                && (this.mc.theWorld.isRemote)) {
            this.mc.renderEngine.bindTexture(this.bg);

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);

            DisplayHelper.drawTexturedQuadFit(5.0D, 5.0D, 32.0D, 32.0D, 0.0D);

            int amountTiles = this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + 0,
                    this.mc.thePlayer.chunkCoordZ + 0).chunkTileEntityMap.values().size();

            amountTiles = amountTiles + this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + 0,
                    this.mc.thePlayer.chunkCoordZ + 1).chunkTileEntityMap.values().size();

            amountTiles = amountTiles + this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + 0,
                    this.mc.thePlayer.chunkCoordZ - 1).chunkTileEntityMap.values().size();

            amountTiles = amountTiles + this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + 1,
                    this.mc.thePlayer.chunkCoordZ).chunkTileEntityMap.values().size();

            amountTiles = amountTiles + this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX - 1,
                    this.mc.thePlayer.chunkCoordZ).chunkTileEntityMap.values().size();
            if (this.mc.thePlayer.getHeldItem().getItemDamage() >= 1) {
                int[] y = { -2, -1, 0, 1, 2, -2, 2, -2, 2, -2, 2, -2, -1, 0, 1, 2 };
                int[] x = { -2, -2, -2, -2, -2, -1, -1, 0, 0, 1, 1, 2, 2, 2, 2, 2 };
                for (int i = 0; i < y.length; i++) {
                    amountTiles = amountTiles
                            + this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + x[i],
                            this.mc.thePlayer.chunkCoordZ + y[i]).chunkTileEntityMap.values().size();
                }
            }
            if (this.mc.thePlayer.getHeldItem().getItemDamage() >= 2) {
                int[] y = { -3, -2, -1, 0, 1, 2, 0, 3, -3, 3, -3, 3, -3, 3, -3, 3, -3, -2, -1, 0, 1, 2, 3 };
                int[] x = { -3, -3, -3, -3, -3, -3, -5, -2, -1, -1, 0, 0, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 3 };
                for (int i = 0; i < y.length; i++) {
                    amountTiles = amountTiles
                            + this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + x[i],
                            this.mc.thePlayer.chunkCoordZ + y[i]).chunkTileEntityMap.values().size();
                }
            }
            if (this.mc.thePlayer.getHeldItem().getItemDamage() >= 0) {
                int tileAmmount = 0;
                int items = 0;
                int chests = 0;
                for (int x = -3; x < 3; x++) {
                    for (int z = -3; z < 3; z++) {
                        Map tileMap = this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + x,
                                this.mc.thePlayer.chunkCoordZ + z).chunkTileEntityMap;

                        Iterator entries = tileMap.entrySet().iterator();
                        while (entries.hasNext()) {
                            Map.Entry e = (Map.Entry) entries.next();
                            TileEntity te = (TileEntity) e.getValue();
                            if (te != null) {
                                    chests++;{
                            }
                        }
                    }
                }


                int xChests = 53;
                if (chests > 100) {
                    chests = 100;
                }
                String showChests = chests + "%";
                if (chests > 9) {
                    xChests -= 3;
                }
            }
            if (amountTiles > 100) {
                amountTiles = 100;
            }
            this.show = (amountTiles + "%");
            int x = 0;
            if (amountTiles > 9) {
                x = 12;
            } else {
                x = 15;
            }
            this.fontRender.drawStringWithShadow(this.show, x, 37, -1);
        }
    }
}}