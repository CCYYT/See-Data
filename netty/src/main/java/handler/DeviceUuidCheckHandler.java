package handler;

import com.example.common.domain.device.DeviceStatusTable;
import com.example.common.domain.device.queue.DeviceCustomData;
import com.example.common.domain.device.queue.DeviceData;
import com.example.common.domain.device.queue.DeviceStatus;
import domain.DataTypeCode;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import message.Message;
import queue.DeviceStatusProducer;

/**
 * 验证通道中的数据 的deviceUuid是否与绑定值一致
* */
public class DeviceUuidCheckHandler extends ChannelInboundHandlerAdapter {
    private String deviceUuid="";
    public DeviceUuidCheckHandler(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    /**
     * 连接断开时  将与 通道绑定的设备设置为下线状态
     * */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        DeviceStatusProducer.publishMsgByObject(new DeviceStatus(deviceUuid, DeviceStatusTable.OFF_LINE));
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        switch (message.getType()){
            case DataTypeCode.DEVICE_DATA_MSG: if(deviceUuid.equals(((DeviceData) message.getText()).getDeviceUuid())) super.channelRead(ctx,msg);break;
            case DataTypeCode.DEVICE_CUSTOM_DATA_MSG: if(deviceUuid.equals(((DeviceCustomData) message.getText()).getDeviceUuid())) super.channelRead(ctx,msg);break;
            default:
                return;
        }
    }
}
