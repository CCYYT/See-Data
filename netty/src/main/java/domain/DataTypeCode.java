package domain;

public class DataTypeCode {
    public static final byte CONNECT_MSG = 0;//连接消息
    public static final byte CONNECT_DEVICES_MSG = 1;//多设备连接消息
    public static final byte DEVICE_DATA_MSG = 10;//设备数据消息
//    public static final byte DEVICE_MASTER_DATA_MSG = -2;//主机数据消息
    public static final byte DEVICE_COMMAND_MSG = 100;//设备命令
    public static final byte DEVICE_CUSTOM_DATA_MSG = -1;//设备自定义数据消息

}
