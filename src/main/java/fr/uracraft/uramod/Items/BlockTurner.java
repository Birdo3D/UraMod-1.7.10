package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockTurner extends Item {

    public BlockTurner() {
        this.setUnlocalizedName("block_turner");
        this.setTextureName(UraMod.MODID + ":block_turner");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.addChatMessage(new ChatComponentText("serveur : side " + side));
            player.addChatMessage(new ChatComponentText("serveur : metadata " + world.getBlockMetadata(x, y, z)));
        }
        if (UraMod.instance.BlockTurnerTurnBlock) {
            if (world.getBlock(x, y, z).rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side))) {
                return true;
            }
        }
        return false;
    }
}
