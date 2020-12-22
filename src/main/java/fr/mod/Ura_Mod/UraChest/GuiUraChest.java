package fr.mod.Ura_Mod.UraChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.mod.Ura_Mod.Ura_ModCommon.ContainerUraFurnace;
import fr.mod.Ura_Mod.Ura_ModCommon.TileEntityUraFurnace;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiUraChest extends GuiContainer{
    private static final ResourceLocation texture = new ResourceLocation(Ura_ModMain.MODID,"textures/gui/container/guiUraChest.png");
    @SuppressWarnings("unused")
    private TileEntityUraChest tileMachineTuto;
    private IInventory playerInv;

    public GuiUraChest(TileEntityUraChest tile, InventoryPlayer inventory)
    {
        super(new ContainerUraChest( inventory,tile));
        this.tileMachineTuto = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 240;
        this.xSize = 248;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

    }

    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String s = this.tileMachineTuto.hasCustomInventoryName() ? this.tileMachineTuto.getInventoryName() : I18n.format(this.tileMachineTuto.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 43, this.ySize - 105 + 12, 4210752);    }

}                                                                                                                                                           //4210752

