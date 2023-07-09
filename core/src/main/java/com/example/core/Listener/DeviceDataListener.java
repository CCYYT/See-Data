package com.example.core.Listener;


import com.example.common.domain.device.queue.DeviceCustomData;
import com.example.common.domain.device.queue.DeviceData;
import com.example.core.domain.Warning;
import com.example.core.domain.WarningRules;
import com.example.core.event.DeviceCustomDataEvent;
import com.example.core.event.DeviceDataEvent;
import com.example.core.service.DataTypeService;
import com.example.core.service.DeviceService;
import com.example.core.service.WarningRulesService;
import com.example.core.service.WarningService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 新增数据 事件 监听器
* */
@Component
public class DeviceDataListener {
    @Resource
    WarningRulesService warningRulesService;
    @Resource
    WarningService warningService;
    @Resource
    DeviceService deviceService;
    @Resource
    DataTypeService dataTypeService;

    /**
     * 校验数据是否通过预警规则
     * 添加警告信息
     * */
    @Async
    @EventListener
    public void checkData(DeviceDataEvent event){
        DeviceData deviceData = event.getDeviceData();
        List<WarningRules> warningRules = warningRulesService.queryByDeviceUuidAndDataType(deviceData.getDeviceUuid(), deviceData.getDataType());
        List<Warning> warningList = warningRulesService.check(warningRules,deviceData.getData(),deviceData.getDeviceUuid());
        if(warningList != null){
            warningService.batchInsert(warningList);
        }
    }

//    /**
//     * 将数据推通过WebSocket送到前端
//    * */
//    @Async
//    @EventListener
//    public void pushDataByWebSocket(DeviceDataEvent event) throws IOException {
//        DeviceData deviceData = event.getDeviceData();
//        /*解析dataType获取dataType对象*/
//        DataType dataType = dataTypeService.queryByDataType(deviceData.getDataType());
//        if(dataType == null) return;
//        //组装对象
//        com.example.core.domain.DeviceData d = new com.example.core.domain.DeviceData();
//        d.setDeviceUuid(deviceData.getDeviceUuid());
//        d.setDeviceData(deviceData.getData());
//        d.setDataTypeId(dataType.getId());
//        d.setDataType(dataType.getDataType());
//        d.setDataUnit(dataType.getDataUnit());
//        d.setDataUnitSymbol(dataType.getDataUnitSymbol());
//        d.setCreateTime(new Date(System.currentTimeMillis()));
//        d.setDataInfo(deviceData.getDataInfo());
//        d.setDataTypeInfo(dataType.getInfo());
//
//        /*
//        * 查询可以收到设备UUID为 目标值 的用户UUID
//        * */
//        List<String> userUuidList = deviceService.getUserUuidByDeviceUuid(deviceData.getDeviceUuid());
//        for (String s : userUuidList) {
//            UserWebSocket.sendMsg(s, JsonUtil.ObjectToString(
//                    new ResponseEntry(d.getDeviceUuid()+d.getDataTypeId(),d)
//            ));
//        }
//    }

    /**
     * 校验数据是否通过预警规则
     * 添加警告信息
     * */
    @Async
    @EventListener
    public void checkData(DeviceCustomDataEvent event){
        DeviceCustomData deviceCustomData = event.getDeviceCustomData();
        Map<String, String> dataMap = deviceCustomData.getDataMap();
        for (String s : dataMap.keySet()) {
            List<WarningRules> warningRules = warningRulesService.queryByDeviceUuidAndDataType(deviceCustomData.getDeviceUuid(),s);
            List<Warning> warningList = warningRulesService.check(warningRules,dataMap.get(s),deviceCustomData.getDeviceUuid());
            if(warningList != null){
                warningService.batchInsert(warningList);
            }
        }
    }
}
