package fr.mod.Ura_Mod.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import fr.mod.Ura_Mod.utils.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;


public class EventHandler {

    @SubscribeEvent
    public void tickEvent(PlayerTickEvent event)
    {
        if (event.player.isSneaking())
            event.player.stepHeight = 0.6F;
        else if (Ura_ModMain.autoJump)
            event.player.stepHeight = 1.25F;
        else
            event.player.stepHeight = 0.6F;
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event)
    {
        if(event.itemStack.getItem() == Item.getItemFromBlock(Ura_ModMain.shulker_box)) {
            if(event.itemStack.hasTagCompound() && event.itemStack.getTagCompound().hasKey("Tile")) {
                NBTTagCompound compound = event.itemStack.getTagCompound().getCompoundTag("Tile");
                NBTTagList nbttaglist = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
                for(int i = 0; i < nbttaglist.tagCount(); ++i)
                {
                    NBTTagCompound nbtitem = nbttaglist.getCompoundTagAt(i);
                    int j = nbtitem.getByte("Slot") & 255;

                    if(j >= 0 && j < 27)
                    {
                        ItemStack item = ItemStack.loadItemStackFromNBT(nbtitem);
                        if(item != null) {
                            if(event.toolTip.size() < 5) {
                                event.toolTip.add(item.getDisplayName()+" x"+item.stackSize);
                            }
                            else {
                                event.toolTip.add("...");
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onPreRender(RenderGameOverlayEvent.Pre event){
        if (event.type == RenderGameOverlayEvent.ElementType.DEBUG) {
            Minecraft minecraft = Minecraft.getMinecraft();
            event.setCanceled(true);
            this.drawString(Minecraft.getMinecraft().fontRenderer, minecraft.debug.split(",",2)[0],10,10,16777215);

            int angle = MathHelper.floor_double((double)(Minecraft.getMinecraft().thePlayer.rotationYaw * 4.0F/360.0F)+0.5D) & 3;
            String direction = Direction.directions[angle];

            this.drawString(minecraft.fontRenderer,"Biome: "+minecraft.theWorld.getBiomeGenForCoords(MathHelper.floor_double(minecraft.thePlayer.posX),MathHelper.floor_double(minecraft.thePlayer.posZ)).biomeName,10,50,16777215);
            this.drawString(minecraft.fontRenderer,"Direction: "+direction,10,40,16777215);

            int x = (int) minecraft.thePlayer.posX;
            int y = (int) minecraft.thePlayer.posY;
            int z = (int) minecraft.thePlayer.posZ;
            String coords = "XYZ : "+x+" / "+y+" / "+z;
            this.drawString(minecraft.fontRenderer,coords,10,30,16777215);
        }
    }
    @SideOnly(Side.CLIENT)
    private void drawString(FontRenderer fontRenderer, String s, int x, int y, int color) {
        fontRenderer.drawStringWithShadow(s,x,y,color);
    }


    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event)
    {
        if (event.source.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) event.source.getEntity();


            if (entity.getHeldItem() != null) {
                if (hasEnchantment(entity.getHeldItem(), 120)) {


                    System.out.println("////////////////////////Drop///////////////////////");
                    System.out.println(event.drops);
                }
            }
        }
    }

    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent harvestDropsEvent){
        if(harvestDropsEvent.harvester instanceof EntityPlayer) {
            if (harvestDropsEvent.harvester.getHeldItem() != null && harvestDropsEvent.drops != null) {
                if (hasEnchantment(harvestDropsEvent.harvester.getHeldItem(), 121)&& hasEnchantment(harvestDropsEvent.harvester.getHeldItem(), 120)) {
                    if(harvestDropsEvent.block== Blocks.stone){
                        harvestDropsEvent.drops.clear();
                        if(Random.Random(175)==1){
                            harvestDropsEvent.harvester.inventory.addItemStackToInventory(new ItemStack(Ura_ModMain.iridium_particle));
                        }else if (Random.Random(150)==1){
                            harvestDropsEvent.harvester.inventory.addItemStackToInventory(new ItemStack(Ura_ModMain.ura_particle));
                        }else if (Random.Random(75)==1){
                            harvestDropsEvent.harvester.inventory.addItemStackToInventory(new ItemStack(Ura_ModMain.argent_particle));
                        }
                    }
                }else if (hasEnchantment(harvestDropsEvent.harvester.getHeldItem(), 120)) {

                    for (int i = 0; i < harvestDropsEvent.drops.size(); i++) {
                        if(harvestDropsEvent.harvester.inventory.addItemStackToInventory(harvestDropsEvent.drops.get(i))){
                            harvestDropsEvent.drops.clear();                          }

                    }

                }else if (hasEnchantment(harvestDropsEvent.harvester.getHeldItem(), 121)) {
                    if(harvestDropsEvent.block== Blocks.stone){
                        harvestDropsEvent.drops.clear();
                        if(Random.Random(400)==1){
                            harvestDropsEvent.drops.add(new ItemStack(Ura_ModMain.iridium_particle));
                        }else if (Random.Random(300)==1){
                            harvestDropsEvent.drops.add(new ItemStack(Ura_ModMain.ura_particle));
                        }else if (Random.Random(175)==1){
                            harvestDropsEvent.drops.add(new ItemStack(Ura_ModMain.argent_particle));
                        }
                    }
                }
            }
        }
    }

    public boolean hasEnchantment(ItemStack stack, int id) {
        boolean flag = false;
        if (stack.getEnchantmentTagList() != null) {
            for (int i = 0; i < stack.getEnchantmentTagList().tagCount(); i++) {
                if (stack.getEnchantmentTagList().getCompoundTagAt(i).getShort("id") == id) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        }
        return flag;
    }
        @SubscribeEvent
        public void LivingEvent(LivingEvent event){

            if(event.entityLiving instanceof EntityPlayer)
            {


                if(event.entityLiving.motionY < 1D) {
                    if (event.entityLiving.getHeldItem() != null) {

                        if (event.entityLiving.getHeldItem().getItem() == Ura_ModMain.hang_glider)

                            //       event.entityLiving.motionX *= 5.0F;
                            event.entityLiving.motionY *= 0.5F;
                        event.entityLiving.fallDistance = 0;

                    }
                }

            }
        }

        @SubscribeEvent
        public void onPlayerInteract(PlayerInteractEvent event) {
            if (event.entityPlayer != null) {
                World world = event.entityPlayer.worldObj;
                if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
                    if (world.getBlock(event.x, event.y, event.z) == Blocks.grass) {
                        ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
                        if (stack != null && (stack.getItem() instanceof ItemSpade)) {
                            world.setBlock(event.x, event.y, event.z, Ura_ModMain.terre_labouree);
                            event.entityPlayer.swingItem();
                            stack.damageItem(1, event.entityPlayer);
                            world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F, Block.soundTypeGravel.getStepResourcePath(), 1.0F, 0.8F);
                        }
                    }
                }
            }
        }
    //Mending
    @SubscribeEvent
    public void onPlayerPickupXP(PlayerPickupXpEvent event) {
        EntityPlayer player = event.entityPlayer;
        EntityXPOrb orb = event.orb;
        if (player.worldObj.isRemote)
            return;

        ItemStack[] stacks = new ItemStack[5];
        stacks[0] = player.getCurrentEquippedItem(); // held
        stacks[1] = player.getEquipmentInSlot(1); // boots
        stacks[2] = player.getEquipmentInSlot(2); // leggings
        stacks[3] = player.getEquipmentInSlot(3); // chestplate
        stacks[4] = player.getEquipmentInSlot(4); // helmet

        for (ItemStack stack : stacks)
            if (stack != null && stack.getItemDamage() > 0 && EnchantmentHelper.getEnchantmentLevel(Ura_ModMain.mending.effectId, stack) > 0) {
                int xp = orb.xpValue;
                while (xp > 0 && stack.getItemDamage() > 0) {
                    stack.setItemDamage(stack.getItemDamage() - 2);
                    xp--;
                }
                if (xp <= 0) {
                    orb.setDead();
                    event.setCanceled(true);
                    return;
                }
            }
    }

    //Frost Walker
    @SubscribeEvent
    public void onLivingUpdate(PlayerEvent event) {
        EntityPlayer entity = event.entityPlayer;
        if (entity.worldObj.isRemote)
            return;

        ItemStack boots = entity.getEquipmentInSlot(1);
        int level = 0;
        if ((level = EnchantmentHelper.getEnchantmentLevel(Ura_ModMain.frostWalker.effectId, boots)) > 0)
            if (entity.onGround) {
                int x = (int) entity.posX;
                int y = (int) entity.posY;
                int z = (int) entity.posZ;

                int radius = 1 + level;

                for (int i = -radius; i <= radius; i++)
                    for (int j = -radius; j <= radius; j++) {
                        Block block = entity.worldObj.getBlock(x + i, y - 1, z + j);
                        if (block == Blocks.water || block == Blocks.flowing_water)
                            entity.worldObj.setBlock(x + i, y - 1, z + j, Ura_ModMain.frosted_ice);
                    }
            }
    }

    @SubscribeEvent
    public void onPlayerInteractWood(PlayerInteractEvent event) {
        if (event.entityPlayer != null) {
            World world = event.entityPlayer.worldObj;
            if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
                if (world.getBlock(event.x, event.y, event.z) == Blocks.log || world.getBlock(event.x, event.y, event.z) == Blocks.log2) {
                    ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
                    if (stack != null && (stack.getItem() instanceof ItemAxe)) {
                        world.setBlock(event.x, event.y, event.z, Ura_ModMain.stripped_oak_log);
                        event.entityPlayer.swingItem();
                        stack.damageItem(1, event.entityPlayer);
                        world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F, Block.soundTypeGravel.getStepResourcePath(), 1.0F, 0.8F);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteractRandomOreItem(PlayerInteractEvent event) {
        if (event.entityPlayer != null) {
            if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
                ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
                if (stack != null && (stack.getItem() == Ura_ModMain.randomoreitem)) {
                    double p = Math.floor(Math.random() * Math.floor(5));
                    int v = (int)p;
                    double p1 = Math.floor(Math.random() * Math.floor(3));
                    int v1 = (int)p1;
                    if(v == 0){
                        if(v1 == 0) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Blocks.diamond_ore,1));
                        }else if(v1 == 1) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Blocks.diamond_ore,2));
                        }else if(v1 == 2) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Blocks.diamond_ore,3));
                        }
                    }else if(v == 1){
                        if(v1 == 0) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Ura_ModMain.ArgentOre,1));
                        }else if(v1 == 1) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Ura_ModMain.ArgentOre,2));
                        }else if(v1 == 2) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Ura_ModMain.ArgentOre,3));
                        }
                    }else if(v == 2){
                        if(v1 == 0) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Ura_ModMain.UraOre,1));
                        }else if(v1 == 1) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Ura_ModMain.UraOre,2));
                        }else if(v1 == 2) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Ura_ModMain.UraOre,3));
                        }
                    }else if(v == 3){
                        if(v1 == 0) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Ura_ModMain.NeodymeOre,1));
                        }else if(v1 == 1) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Ura_ModMain.NeodymeOre,2));
                        }else if(v1 == 2) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Ura_ModMain.NeodymeOre,3));
                        }
                    }else if(v == 4){
                        event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Ura_ModMain.randomore,1));
                    }
                }
            }
        }
    }
       /* @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event){
        if(event.entity instanceof EntityPlayer){
            if(!event.world.isRemote){
       //     ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA+"Joyeux Anniversaire"+EnumChatFormatting.BOLD));
                ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(EnumChatFormatting.YELLOW+"Joy"+EnumChatFormatting.GOLD+"eux"+EnumChatFormatting.RED+" Ann"+EnumChatFormatting.LIGHT_PURPLE+"ive"+EnumChatFormatting.AQUA+"rsa"+EnumChatFormatting.BLUE+"ire"+EnumChatFormatting.GREEN+" !!!!"));
        }}
        }*/
}

