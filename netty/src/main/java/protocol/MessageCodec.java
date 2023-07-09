package protocol;


import com.example.common.domain.device.queue.DeviceCustomData;
import com.example.common.domain.device.queue.DeviceData;
import com.example.common.domain.device.openFeign.DeviceValidate;
import com.example.common.domain.device.openFeign.DevicesValidate;
import com.example.common.util.JsonUtil;
import domain.DataCodecCode;
import domain.DataTypeCode;
import io.netty.util.CharsetUtil;
import message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class MessageCodec extends ByteToMessageCodec<Message>{

    private Class<?> messageClass;
    private Object o;

    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf out) throws Exception {
        out.writeBytes(new byte[]{-2,0x32}); //魔数
        out.writeByte(1);//版本号
        out.writeByte(message.getType());//数据类型
        out.writeByte(message.getCodec());//编码格式
        byte[] bytes;
        switch (message.getCodec()){
            case 0:bytes = ((String)message.getText()).getBytes(StandardCharsets.UTF_8);break;
            case 1:bytes = JsonUtil.convertToBytes(message.getText());break;
            default:return;
        }
        out.writeByte((bytes.length>>8)&0xFF);//正文长度
        out.writeByte(bytes.length&0xFF);//正文长度
        out.writeBytes(new byte[]{0});//填充对齐
        out.writeBytes(bytes);//正文
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 8) {
            return; // 不足一个完整消息的长度，等待更多数据
        }
//        byte num1 = in.readByte();
//        byte num2 = in.readByte();
//        int num = in.readUnsignedShort();
        // 解析消息头部
        byte version = in.readByte();  //版本号
        byte type = in.readByte();     //数据类型
        byte codec = in.readByte();    //编解码格式
        int length = in.readUnsignedShort();  //正文长度
        byte alignFill = in.readByte();   //对齐填充

        // 检查可读字节是否足够读取完整消息体
        if (in.readableBytes() < length) {
            in.readerIndex(in.readerIndex() - 8); // 回退读取的消息头部
            return; // 等待更多数据
        }

        byte[] bytes = new byte[length];
        //请求正文
        in.readBytes(bytes,0,length);
        //数据类型
        switch (type){
            case DataTypeCode.CONNECT_MSG:messageClass = DeviceValidate.class;break;
            case DataTypeCode.CONNECT_DEVICES_MSG:messageClass = DevicesValidate.class;break;
            case DataTypeCode.DEVICE_DATA_MSG:messageClass = DeviceData.class;break;
            case DataTypeCode.DEVICE_CUSTOM_DATA_MSG: messageClass = DeviceCustomData.class;break;
//            case DataTypeCode.DEVICE_MASTER_DATA_MSG: messageClass = DeviceMasterData.class;break;
            default:
                sendErrorMessage(ctx,"不支持的数据类型");
                return;
        }
//        if( (messageClass = DataTypeManage.getObjectClazzByDataTypeCode(type)) == null){
//            sendErrorMessage(ctx,"不支持的数据类型");
//            return;
//        }

        //正文解码
        try {
            switch (codec){
                //JSON_JackSon
                case DataCodecCode.JSON_JACKSON_CODEC:
                    o = JsonUtil.bytesToObject(bytes, messageClass);
                    break;
                default:
                    sendErrorMessage(ctx,"不支持的解码格式");
                    return;
            }
        }catch (Exception e){
            sendErrorMessage(ctx,"Data decoding error");
            return;
        }

        if(o!=null){
            out.add(new Message(type,o));
        }
    }

    /**
     * 将错误信息返回给客户端
     * */
    private void sendErrorMessage(ChannelHandlerContext ctx, String errorMessage) {
        ByteBuf responseBuf = ctx.alloc().buffer();
        responseBuf.writeBytes(errorMessage.getBytes(CharsetUtil.UTF_8));
        ctx.writeAndFlush(responseBuf);
    }
}
