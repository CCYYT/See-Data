package handler;

import clients.DeviceClient;
import com.example.common.domain.device.DeviceStatusTable;
import com.example.common.domain.device.openFeign.DeviceValidate;
import com.example.common.domain.device.openFeign.DevicesValidate;
import com.example.common.domain.device.queue.DeviceStatus;
import com.example.common.result.Code;
import com.example.common.result.Result;
import config.NettyConfig;
import domain.DataTypeCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import message.Message;
import queue.DeviceStatusProducer;
import util.FeignUtil;

/**
 * 连接验证处理器
 * 验证成功将会在通道中删除
* */
public class ValidateConnectionHandler extends ChannelInboundHandlerAdapter {
    private DeviceValidate deviceValidate;
    private DevicesValidate devicesValidate;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message receivedData = (Message) msg;
        Result result;
        //发送验证请求
        switch (receivedData.getType()){
            case DataTypeCode.CONNECT_MSG:
                deviceValidate = (DeviceValidate) receivedData.getText();
                result = ((DeviceClient) FeignUtil.getClient(DeviceClient.class))
                                                        .validate(deviceValidate);break;
            case DataTypeCode.CONNECT_DEVICES_MSG:
                devicesValidate = (DevicesValidate) receivedData.getText();
                result = ((DeviceClient) FeignUtil.getClient(DeviceClient.class))
                                                        .validates(devicesValidate);break;
            default:
                return;
        }
        //验证通过
        if(Code.VERIFICATION_OK.equals(result.getCode())){
            // 将验证的deviceUuid/devicesUuid与通道绑定
            switch (receivedData.getType()) {
                case DataTypeCode.CONNECT_MSG:
                    NettyConfig.channelMap.put(deviceValidate.getDeviceUuid(),ctx);
                    ctx.pipeline().addAfter(ctx.name(), "newHandler", new DeviceUuidCheckHandler(deviceValidate.getDeviceUuid()));
                    DeviceStatusProducer.publishMsgByObject(new DeviceStatus(deviceValidate.getDeviceUuid(), DeviceStatusTable.ON_LINE));
                    break;
                case DataTypeCode.CONNECT_DEVICES_MSG:
                    for (String s : devicesValidate.getDevicesUuid()) {
                        NettyConfig.channelMap.put(s,ctx);
                        DeviceStatusProducer.publishMsgByObject(new DeviceStatus(s, DeviceStatusTable.ON_LINE));
                    }
                    ctx.pipeline().addAfter(ctx.name(), "newHandler", new DevicesUuidCheckHandler(devicesValidate.getDevicesUuid()));
                    break;
            }

            // 验证通过，移除验证处理器并继续处理后续消息
            ctx.pipeline().remove(this);
//            ctx.fireChannelRead(msg);//将数据传递给下一个处理器
            return;
        }
        ByteBuf responseBuf = ctx.alloc().buffer();
        responseBuf.writeBytes("未验证/认证的连接".getBytes(CharsetUtil.UTF_8));
        ctx.writeAndFlush(responseBuf);
        // 验证失败，关闭连接
        ctx.close();
//        responseBuf.clear();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
