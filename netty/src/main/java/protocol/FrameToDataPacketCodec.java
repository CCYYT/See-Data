package protocol;

import Manage.CredentialManage;
import domain.MediumMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

public class FrameToDataPacketCodec extends ByteToMessageCodec<MediumMessage> {

    protected void encode(ChannelHandlerContext ctx, MediumMessage message, ByteBuf out) throws Exception {

    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        //版本号
        byte version = in.readByte();
        //(用户)凭证
        int voucher = in.readInt();
        //验证凭证
        if(CredentialManage.voucherVerification(voucher)){
//            list.add(in);
            int length = in.readInt();
            int sequenceNumber = in.readUnsignedShort(); //请求序号
            byte[] bytes = new byte[length+4+2];
            bytes[0] = (byte) (voucher >> 24);bytes[1] = (byte) (voucher >> 16);bytes[2] = (byte) (voucher >> 8);bytes[3] = (byte) voucher; //凭证号
            bytes[4] = (byte) (sequenceNumber >> 8);bytes[5] = (byte) sequenceNumber; // 请求序号
            in.readBytes(bytes,6,length);  // 凭证+请求序号+正文
            list.add(bytes);
        }else {
            in.readBytes(in.writerIndex() - 5); //跳过当前数据包
        }
    }
}
