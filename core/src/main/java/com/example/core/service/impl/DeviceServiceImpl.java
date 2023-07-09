package com.example.core.service.impl;

import com.example.common.domain.device.openFeign.DeviceValidate;
import com.example.common.domain.device.openFeign.DevicesValidate;
import com.example.common.page.PageInfo;
import com.example.core.domain.Device;
import com.example.core.dao.DeviceDao;
import com.example.core.service.DeviceService;
import org.springframework.stereotype.Service;
import com.example.common.util.UuidUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备信息(Device)表服务实现类
 *
 * @author makejava
 * @since 2023-06-16 17:17:26
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
    @Resource
    private DeviceDao deviceDao;

    /**
     * 通过groupId  查询用户组中管理的所有设备
     *
     * @param groupId
     * @return 设备列表
     */
    @Override
    public List<Device> getDeviceListByGroupId(Integer groupId){
        return this.deviceDao.getDeviceListByGroupId(groupId);
    }

    /**
     * 通过groupId  查询用户组中管理的所有设备和设备的数据类型
     *
     * @param groupId
     * @return 设备列表
     */
    @Override
    public List<Device> getDeviceAndDataTypeListByGroupId(Integer groupId){
        return this.deviceDao.getDeviceAndDataTypeListByGroupId(groupId);
    }

    /**
     * 通过uuid查询单条数据
     *
     * @param uuid
     * @return 实例对象
     */
    @Override
    public Device queryByUuid(String uuid) {
        return this.deviceDao.queryByUuid(uuid);
    }

    /**
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo queryByPage(PageInfo pageInfo) {
        pageInfo.setTotalElementNum(this.deviceDao.count(pageInfo));
        pageInfo.setContent(this.deviceDao.queryAllByLimit(pageInfo));
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param device 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(Device device) {
        String uuid;
        while (true){
            uuid = UuidUtil.getUuid();
            if(!this.deviceDao.uuidIsExist(uuid)){
                (device).setUuid(uuid);
                return this.deviceDao.insert(device) > 0;
            }
        }
    }


    /**
     * 修改数据
     *
     * @param device 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Device device) {
        return this.deviceDao.update(device) > 0;
    }

    /**
     * 通过uuid删除数据
     *
     * @param uuid
     * @return 是否成功
     */
    @Override
    public boolean deleteByUuid(String uuid) {
        return this.deviceDao.deleteByUuid(uuid) > 0;
    }

    /**
     * 验证设备 是否 属于 区域
     *
     * @param deviceValidate
     * @return 验证结果
     */
    @Override
    public boolean validate(DeviceValidate deviceValidate){return this.deviceDao.validate(deviceValidate);}

    /**
     * 验证多个设备 是否 属于 区域
     *
     * @param devicesValidate
     * @return 验证结果
     */
    @Override
    public boolean validates(DevicesValidate devicesValidate){
        DeviceValidate d = new DeviceValidate();
        d.setAreaUuid(devicesValidate.getAreaUuid());
        for (String s : devicesValidate.getDevicesUuid()) {
            d.setDeviceUuid(s);
            if(!this.deviceDao.validate(d)) return false;
        }
        return true;
    }

    /**
     * 查询可以收到设备UUID为 目标值 的用户UUID
     * */
    @Override
    public List<String> getUserUuidByDeviceUuid(String deviceUuid){
        return this.deviceDao.getUserUuidByDeviceUuid(deviceUuid);
    }
}
