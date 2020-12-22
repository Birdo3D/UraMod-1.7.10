package fr.mod.Ura_Mod.iriduim_smithing_table;

import fr.mod.Ura_Mod.Ura_ModCommon.ContainerUraFurnace;
import fr.mod.Ura_Mod.Ura_ModCommon.TileEntityUraFurnace;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class IriduimSmithingTableGui extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(Ura_ModMain.MODID,"textures/gui/container/smithing.png");
    @SuppressWarnings("unused")
    private IriduimSmithingTableTileEntity iriduimSmithingTableTileEntity;
    private IInventory playerInv;

    public IriduimSmithingTableGui(IriduimSmithingTableTileEntity tile, InventoryPlayer inventory, World world)
    {
        super(new IriduimSmithingTableContnaier( inventory,tile,world));
        this.iriduimSmithingTableTileEntity = tile;
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

        if(!this.iriduimSmithingTableTileEntity.canSmeltItem()&& this.iriduimSmithingTableTileEntity.getStackInSlot(0)!=null && this.iriduimSmithingTableTileEntity.getStackInSlot(1)!=null)
        {

            this.drawTexturedModalRect(k + 102, l + 48, 179, 3, 22, 18);

        }
    }

    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String s = this.iriduimSmithingTableTileEntity.hasCustomInventoryName() ? this.iriduimSmithingTableTileEntity.getInventoryName() : I18n.format(this.iriduimSmithingTableTileEntity.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 105 + 2, 4210752);    }

}
