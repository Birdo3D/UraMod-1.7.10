package fr.mod.Ura_Mod.wood_converter;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class WoodConverterPackets implements IMessage {
    private int ID;
    private int CoordsX;
    private int CoordsY;
    private int CoordsZ;


    public WoodConverterPackets(int id , int x , int y , int z) {
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

    public static class Handler implements IMessageHandler<WoodConverterPackets, IMessage> {
  //      TileEntityWoodConverter tileEntityWoodConverter;

        @Override
        public IMessage onMessage(WoodConverterPackets message, MessageContext ctx) {
//            this.tileEntityWoodConverter.onButtonAction(Integer.parseInt(message.text));
            if(ctx.getServerHandler().playerEntity.worldObj.getBlock(message.CoordsX, message.CoordsY, message.CoordsZ).hasTileEntity()){
                TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.CoordsX, message.CoordsY, message.CoordsZ);
                if(tile instanceof TileEntityWoodConverter){
                    TileEntityWoodConverter tileEntityWoodConverter = (TileEntityWoodConverter) tile;
                    tileEntityWoodConverter.onButtonAction(message.ID);
                }

            }


            System.out.println(String.format("Received ID : %s from %s", message.ID +" X : "+ message.CoordsX +" Y : "+ message.CoordsY +" Z : "+ message.CoordsZ, ctx.getServerHandler().playerEntity.getDisplayName()));
            return null; // no response in this case
        }
    }
}
