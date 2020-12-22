package fr.mod.Ura_Mod.blocks.shulkerbox;

import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiShulkerBox extends GuiContainer
{
    private static final ResourceLocation textures = new ResourceLocation(Ura_ModMain.MODID, "textures/gui/container/shulkerbox.png");
    private TileEntityShulkerBox tile;
    private IInventory playerInv;
    
    public GuiShulkerBox(TileEntityShulkerBox tile, InventoryPlayer inventory) {
        super(new ContainerShulkerBox(tile, inventory));
        this.tile = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 170;
    }

    
    
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String tileName = this.tile.hasCustomInventoryName() ? this.tile.getInventoryName() : I18n.format(this.tile.getInventoryName());
        this.fontRendererObj.drawString(tileName, 8, 6, 4210752);
        String invName = this.playerInv.hasCustomInventoryName() ? this.playerInv.getInventoryName() : I18n.format(this.playerInv.getInventoryName());
        this.fontRendererObj.drawString(invName, 8, this.ySize-97, 4210752);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(textures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

}
