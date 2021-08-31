package fr.uracraft.uramod.Blocks.wood_converter;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class PacketsWoodConverter implements IMessage {
    private int ID;
    private int CoordsX;
    private int CoordsY;
    private int CoordsZ;


    public PacketsWoodConverter(){}

    public PacketsWoodConverter(int id , int x , int y , int z) {
        this.ID = id;
        this.CoordsX = x;
        this.CoordsY = y;
        this.CoordsZ = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        ID = buf.readInt();
        CoordsX = buf.readInt();
        CoordsY = buf.readInt();
        CoordsZ = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(ID);
        buf.writeInt(CoordsX);
        buf.writeInt(CoordsY);
        buf.writeInt(CoordsZ);
    }

    public static class Handler implements IMessageHandler<PacketsWoodConverter, IMessage> {

        @Override
        public IMessage onMessage(PacketsWoodConverter message, MessageContext ctx) {

            if(ctx.getServerHandler().playerEntity.worldObj.getBlock(message.CoordsX, message.CoordsY, message.CoordsZ).hasTileEntity()){
                TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.CoordsX, message.CoordsY, message.CoordsZ);
                if(tile instanceof TileEntityWoodConverter){
                    TileEntityWoodConverter tileEntityWoodConverter = (TileEntityWoodConverter) tile;
                    tileEntityWoodConverter.onButtonAction(message.ID);
                }
            }
            return null;
        }
    }
}
