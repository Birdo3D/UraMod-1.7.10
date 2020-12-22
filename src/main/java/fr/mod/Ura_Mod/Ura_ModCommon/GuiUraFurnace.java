package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
@SideOnly(Side.CLIENT)
public class GuiUraFurnace extends GuiContainer{
    private static final ResourceLocation texture = new ResourceLocation(Ura_ModMain.MODID,"textures/gui/container/guiMachineTuto.png");
    @SuppressWarnings("unused")
    private TileEntityUraFurnace tileMachineTuto;
    private IInventory playerInv;

    public GuiUraFurnace(TileEntityUraFurnace tile, InventoryPlayer inventory)
    {
        super(new ContainerUraFurnace( inventory,tile));
        this.tileMachineTuto = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 176;
        this.xSize = 176;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        if(this.tileMachineTuto.BurningTimeLeft > 0)
        {
            int i = this.tileMachineTuto.getCookProgress();
            this.drawTexturedModalRect(k + 51 +29, l + 34, 177, 14, i, 17);

            int i1 = this.tileMachineTuto.getCookProgressScaled(13);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
            //int i1 = this.tileMachineTuto.getCookProgressScaled(24);
            //this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        }
    }

    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String s = this.tileMachineTuto.hasCustomInventoryName() ? this.tileMachineTuto.getInventoryName() : I18n.format(this.tileMachineTuto.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 105 + 2, 4210752);    }

}

