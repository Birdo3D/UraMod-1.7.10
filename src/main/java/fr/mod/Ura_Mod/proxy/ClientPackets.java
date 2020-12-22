package fr.mod.Ura_Mod.proxy;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import fr.mod.Ura_Mod.wood_converter.TileEntityWoodConverter;
import fr.mod.Ura_Mod.wood_converter.WoodConverterPackets;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import sun.security.x509.X500Name;

public class ClientPackets implements IMessage {
    private int X;
    private int Y;
    private int Z;


    public ClientPackets(int x, int y, int z) {
        this.X = x;
        this.Y = y;
        this.Z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        X = buf.readInt();
        Y = buf.readInt();
        Z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(X);
        buf.writeInt(Y);
        buf.writeInt(Z);
    }

    public static class Handler implements IMessageHandler<ClientPackets, IMessage> {
        //      TileEntityWoodConverter tileEntityWoodConverter;

        @Override
        public IMessage onMessage(ClientPackets message, MessageContext ctx) {
            ctx.getServerHandler().playerEntity.openGui(Ura_ModMain.instance, 0, ctx.getServerHandler().playerEntity.worldObj, message.X, message.Y, message.Z);
            return null; // no response in this case
        }
    }
}
