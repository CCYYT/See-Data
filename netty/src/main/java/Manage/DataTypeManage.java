//package Manage;
//
//import com.example.common.domain.device.DeviceData;
//import com.example.common.domain.device.DeviceValidate;
//import domain.DataTypeCode;
//
//import java.util.Map;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * 数据类型管理   模板模式
// * 数据类型 与 java对象映射管理
//* */
//public class DataTypeManage {
//    /**
//     * 数据类型映射表
//     * @describe 数据类型与 对应的类型对象的class
//    * */
//    private static Map<Byte,Class> dataTypeMappingTable = new ConcurrentHashMap<>();
////
////    /**
////     * 数据类型与事件映射表
////     * @describe 数据类型与 对应的事件 event
////     * */
////    private static Map<Byte, Callable<?>> eventMappingTable = new ConcurrentHashMap<>();
//
//    static {
//        dataTypeMappingTable.put(DataTypeCode.CONNECT_MSG,DeviceValidate.class); //连接数据 类型
//        dataTypeMappingTable.put(DataTypeCode.DEVICE_DATA_MSG,DeviceData.class); //设备数据 类型
//
////        eventMappingTable.put(DataTypeCode.DEVICE_DATA_MSG, new Callable<Object>() {
////            @Override
////            public Object call() throws Exception {
////
////                return null;
////            }
////        });
//    }
//
//    /**
//     * 通过数据类型code 获取到对应的class类型
//    * */
//    public static Class getObjectClazzByDataTypeCode(byte dataTypeCode){
//        return dataTypeMappingTable.get(dataTypeCode);
//    }
//
//
////    public static Class getEventByDataTypeCode(byte dataTypeCode){
////        return eventMappingTable.get(dataTypeCode);
////    }
////    public static Object executeEvent(byte dataTypeCode) throws Exception {
////        return eventMappingTable.get(dataTypeCode).call();
////    }
//
//
//}
