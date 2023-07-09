package handler;

import com.example.common.domain.device.queue.DeviceCustomData;
import com.example.common.domain.device.queue.DeviceData;
import config.NettyConfig;
import domain.DataTypeCode;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import message.Message;
import queue.DeviceDataProducer;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端与服务端创建连接的时候调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端连接开始...");
        NettyConfig.group.add(ctx.channel());
    }

    /**
     * 客户端与服务端断开连接时调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端连接关闭...");
        NettyConfig.group.remove(ctx.channel());
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
//        System.out.println("信息接收完毕...");
    }

    /**
     * 工程出现异常的时候调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.write(cause.getMessage());
        ctx.close();
    }

    /**
     * 服务端处理客户端websocket请求的核心方法，这里接收了客户端发来的信息
     */
    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object info) throws Exception {
        System.out.println("接收到了："+info);
        Message message = (Message) info;

        switch (message.getType()){
            case DataTypeCode.DEVICE_DATA_MSG:
                DeviceData deviceData = (DeviceData) message.getText();
                //发送数据到mq
                DeviceDataProducer.publishMsgByDeviceData(deviceData);
                break;
            case DataTypeCode.DEVICE_CUSTOM_DATA_MSG:
                DeviceCustomData deviceCustomData = (DeviceCustomData) message.getText();
                DeviceDataProducer.publishMsgByDeviceCustomData(deviceCustomData);
                break;
//            case DataTypeCode.DEVICE_MASTER_DATA_MSG:
//                DeviceMasterData deviceMasterData = (DeviceMasterData) message.getText();

        }

    }


}
