package fr.uracraft.uramod.Blocks.wood_converter;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiWoodConverter extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(UraMod.MODID, "textures/gui/container/gui_wood_converter.png");
    @SuppressWarnings("unused")
    private TileEntityWoodConverter tileEntityWoodConverter;
    private IInventory playerInv;

    public GuiWoodConverter(TileEntityWoodConverter tile, InventoryPlayer inventory) {
        super(new ContainerWoodConverter(inventory, tile));
        this.tileEntityWoodConverter = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 166;
        this.xSize = 176;
    }

    public void initGui() {
        this.buttonList.clear();
        buttonList.add(new GuiButton(200, (this.width - this.xSize) / 2 + 55, (this.height - this.ySize) / 2 + 22, 20, 20, " "));
        buttonList.add(new GuiButton(201, (this.width - this.xSize) / 2 + 78, (this.height - this.ySize) / 2 + 22, 20, 20, " "));
        buttonList.add(new GuiButton(202, (this.width - this.xSize) / 2 + 101, (this.height - this.ySize) / 2 + 22, 20, 20, " "));
        buttonList.add(new GuiButton(203, (this.width - this.xSize) / 2 + 55, (this.height - this.ySize) / 2 + 45, 20, 20, " "));
        buttonList.add(new GuiButton(204, (this.width - this.xSize) / 2 + 78, (this.height - this.ySize) / 2 + 45, 20, 20, " "));
        buttonList.add(new GuiButton(205, (this.width - this.xSize) / 2 + 101, (this.height - this.ySize) / 2 + 45, 20, 20, " "));


        super.initGui();
    }

    protected void actionPerformed(GuiButton button) {


        if (this.tileEntityWoodConverter.getStackInSlot(0) != null && this.tileEntityWoodConverter.getStackInSlot(1) == null) {
            if (tileEntityWoodConverter.getStackInSlot(0).getItem() instanceof ItemBlock) {
                if (Block.getBlockFromItem(tileEntityWoodConverter.getStackInSlot(0).getItem()) == Blocks.log || Block.getBlockFromItem(tileEntityWoodConverter.getStackInSlot(0).getItem()) == Blocks.log2) {
                    if (this.tileEntityWoodConverter.getStackInSlot(0) != null && this.tileEntityWoodConverter.getStackInSlot(1) == null) {
                        UraMod.network.sendToServer(new PacketsWoodConverter(button.id, tileEntityWoodConverter.xCoord, tileEntityWoodConverter.yCoord, tileEntityWoodConverter.zCoord));
                    }
                }
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y) {
        RenderHelper.enableGUIStandardItemLighting();
        ItemStack slot1 = new ItemStack(Item.getItemFromBlock(Blocks.stone));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

    protected void drawGuiContainerForegroundLayer(int x, int y) {
        RenderHelper.enableGUIStandardItemLighting();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);


        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glColor3f(1F, 1F, 1F); //Forge: Reset color in case Items change it.
        GL11.glEnable(GL11.GL_BLEND); //Forge: Make sure blend is enabled else tabs show a white border.
        this.zLevel = 100.0F;
        itemRender.zLevel = 100.0F;
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);

        ItemStack itemstack = new ItemStack(Blocks.log);
        ItemStack itemstack2 = new ItemStack(Blocks.log2);
        ItemStack itemstack3 = new ItemStack(Blocks.log, 1, 1);
        ItemStack itemstack4 = new ItemStack(Blocks.log, 1, 2);
        ItemStack itemstack5 = new ItemStack(Blocks.log, 1, 3);
        ItemStack itemstack6 = new ItemStack(Blocks.log2, 1, 1);
        itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, 57, 24);
        itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, 57, 24);

        itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack2, 80, 24);
        itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack2, 80, 24);

        itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack3, 103, 24);
        itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack3, 103, 24);


        itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack4, 57, 47);
        itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack4, 57, 47);

        itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack5, 80, 47);
        itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack5, 80, 47);

        itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack6, 103, 47);
        itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack6, 103, 47);
        GL11.glDisable(GL11.GL_LIGHTING);
        itemRender.zLevel = 0.0F;
        this.zLevel = 0.0F;

        String s = this.tileEntityWoodConverter.hasCustomInventoryName() ? this.tileEntityWoodConverter.getInventoryName() : I18n.format(this.tileEntityWoodConverter.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 105 + 11, 4210752);
    }

}
