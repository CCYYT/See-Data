package handler;

import com.example.common.domain.device.DeviceStatusTable;
import com.example.common.domain.device.queue.DeviceCustomData;
import com.example.common.domain.device.queue.DeviceData;
import com.example.common.domain.device.queue.DeviceStatus;
import config.NettyConfig;
import domain.DataTypeCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import message.Message;
import queue.DeviceStatusProducer;

import java.util.List;

/**
 * 验证通道中的数据 的deviceUuid是否与绑定值一致
* */
public class DevicesUuidCheckHandler extends ChannelInboundHandlerAdapter {
    private List<String> devicesUuid;
    public DevicesUuidCheckHandler(List<String> devicesUuid) {
        this.devicesUuid = devicesUuid;
    }

    /**
     * 连接断开时  将与 通道绑定的设备设置为下线状态
    * */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        for (String s : devicesUuid) {
            DeviceStatusProducer.publishMsgByObject(new DeviceStatus(s, DeviceStatusTable.OFF_LINE));
        }
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        switch (message.getType()){
            case DataTypeCode.DEVICE_DATA_MSG: if(devicesUuid.contains(((DeviceData) message.getText()).getDeviceUuid()))super.channelRead(ctx,msg);break;
            case DataTypeCode.DEVICE_CUSTOM_DATA_MSG: if(devicesUuid.contains(((DeviceCustomData) message.getText()).getDeviceUuid())) super.channelRead(ctx,msg);break;
            default:
                ByteBuf responseBuf = ctx.alloc().buffer();
                responseBuf.writeBytes(("未认证的设备:"+((DeviceData) message.getText()).getDeviceUuid()).getBytes(CharsetUtil.UTF_8));
                ctx.writeAndFlush(responseBuf);
                return;
        }
    }
}
