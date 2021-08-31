package fr.uracraft.uramod.Events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.uracraft.uramod.Items.HangGlider;
import fr.uracraft.uramod.common.UraItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

public class HangGliderEvent {

    @SubscribeEvent
    public void LivingEvent(LivingEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            if (event.entityLiving.motionY < 1D) {
                if (event.entityLiving.getHeldItem() != null) {
                    if (event.entityLiving.getHeldItem().getItem() == UraItems.hang_glider) {
                        if(HangGlider.usingHangGliderClient.contains(Minecraft.getMinecraft().thePlayer) || HangGlider.usingHangGliderServer.contains(Minecraft.getMinecraft().thePlayer)) {
                            event.entityLiving.fallDistance = 0;
                        }
                    }
                }
            }
        }
    }
}
