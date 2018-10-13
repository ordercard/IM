package cs.util;

import cs.entry.JsonSerI;
import cs.entry.Packet;
import cs.entry.Serializer;
import cs.util.request.*;
import cs.util.response.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static cs.entry.Command.*;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 10:20 2018/10/11 2018
 * @Modify:
 */
public class EDcode {

    public static  final int MAC_NUMBER = 0x12345678;
    public static final EDcode INSTANCE = new EDcode();

    //命令对应相应的实体
    private static final Map<Byte,Class<? extends Packet>> map ;
    //对应相应的序列化对象
    private static final Map<Byte, Serializer> sermap;
    static {

        map= new HashMap<>();
        sermap =new HashMap<>();

        map.put(LOGIN_REQUEST, LoginRequestPacket.class);
        map.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        map.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        map.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        map.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
        map.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);
        map.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        map.put(CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);

        map.put(JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        map.put(JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        map.put(QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        map.put(QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        map.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        map.put(LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        Serializer serializer =new JsonSerI();
        sermap.put(serializer.getSerializerAlgorithm(),serializer);
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator,Packet packet) {

        ByteBuf byteBuf = byteBufAllocator.ioBuffer();

        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return  byteBuf;
    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        // 1. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 2. 实际编码过程
        byteBuf.writeInt(MAC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf) {
    //    System.out.println("可读字节数"+byteBuf.readableBytes());
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);

        byte  seralthm =byteBuf.readByte();
        byte  com =byteBuf.readByte();
        int length =byteBuf.readInt();

        byte[] bytes =  new byte[length];

        byteBuf.readBytes(bytes);

        Class<? extends Packet> rtype = getCommandType(com);
        Serializer serializer = getSerializer(seralthm);
       if (rtype != null && serializer !=null){

           return serializer.deserialize(rtype,bytes);
       }
       return  null;
    }

    private Serializer getSerializer(byte seralthm) {
        return  sermap.get(seralthm);
    }

    private Class<? extends Packet> getCommandType(byte com) {
        return map.get(com);
    }


}
