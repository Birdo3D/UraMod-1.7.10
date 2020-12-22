package fr.mod.Ura_Mod.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import fr.mod.Ura_Mod.OverlayChestRadar;
import fr.mod.Ura_Mod.Ura_ModCommon.EntityMobHalloween;
import fr.mod.Ura_Mod.Ura_ModCommon.UraRender;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import fr.mod.Ura_Mod.client.RenderMobHalloween;
import fr.mod.Ura_Mod.items.backpack.Backpack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy
{
    public static int renderTableId;
    private static KeyBinding backpackKey;
    private static KeyBinding autoJumpKey;

    public ClientProxy()
    {
        FMLCommonHandler.instance().bus().register(this);
        backpackKey = new KeyBinding("uramod.backpack", Keyboard.KEY_F, "key.categories.inventory");
        ClientRegistry.registerKeyBinding(backpackKey);
        autoJumpKey = new KeyBinding("uramod.autoJump", Keyboard.KEY_J, "key.categories.movement");
        ClientRegistry.registerKeyBinding(autoJumpKey);
    }

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event)
    {
        if(backpackKey.isPressed())
        {
            if(Minecraft.getMinecraft().thePlayer.getEquipmentInSlot(3) != null && (Minecraft.getMinecraft().thePlayer.getEquipmentInSlot(3).getItem() instanceof Backpack)) {
                Minecraft.getMinecraft().thePlayer.openGui(Ura_ModMain.instance, 0, Minecraft.getMinecraft().thePlayer.worldObj, (int) Minecraft.getMinecraft().thePlayer.posX, (int) Minecraft.getMinecraft().thePlayer.posY, (int) Minecraft.getMinecraft().thePlayer.posZ);
            }
        }
        if(autoJumpKey.isPressed())
        {
            Ura_ModMain.cfg.getCategory(Ura_ModMain.cfg.CATEGORY_GENERAL).get("autoJump").set(!Ura_ModMain.autoJump);
            Ura_ModMain.cfg.save();
            Ura_ModMain.autoJump = !Ura_ModMain.autoJump;
        }
    }

    @Override
    public void registerRender()
    {
        renderTableId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderTableId, new UraRender());
        RenderingRegistry.registerBlockHandler(new UraRender());
        System.out.println("méthode côté client");
        RenderingRegistry.registerEntityRenderingHandler(EntityMobHalloween.class, new RenderMobHalloween(new ModelBiped(), 0.5F));
    }
    @Override
    public void registerOverlay() {
        MinecraftForge.EVENT_BUS.register(new OverlayChestRadar());
    }

}