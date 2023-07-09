package com.example.core.service;

import com.example.common.domain.device.openFeign.DeviceValidate;
import com.example.common.domain.device.openFeign.DevicesValidate;
import com.example.common.page.PageInfo;
import com.example.core.domain.Device;

import java.util.List;

/**
 * 设备信息(Device)表服务接口
 *
 * @author makejava
 * @since 2023-06-16 17:17:26
 */
public interface DeviceService {
    /**
     * 通过groupId  查询用户组中管理的所有设备
     *
     * @param groupId
     * @return 设备列表
     */
    List<Device> getDeviceListByGroupId(Integer groupId);

    /**
     * 通过groupId  查询用户组中管理的所有设备和设备的数据类型
     *
     * @param groupId
     * @return 设备列表
     */
    List<Device> getDeviceAndDataTypeListByGroupId(Integer groupId);

    /**
     * 通过uuid查询单条数据
     *
     * @param uuid
     * @return 实例对象
     */
    Device queryByUuid(String uuid);

    /**
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    PageInfo queryByPage(PageInfo pageInfo);

    /**
     * 新增数据
     *
     * @param device 实例对象
     * @return 是否成功
     */
    boolean insert(Device device);

    /**
     * 修改数据
     *
     * @param device 实例对象
     * @return 是否成功
     */
    boolean update(Device device);

    /**
     * 通过uuid删除数据
     *
     * @param uuid
     * @return 是否成功
     */
    boolean deleteByUuid(String uuid);
    /**
     * 验证设备 是否 属于 区域
     *
     * @param deviceValidate
     * @return 验证结果
     */
    boolean validate(DeviceValidate deviceValidate);

    /**
     * 验证多个设备 是否 属于 区域
     *
     * @param devicesValidate
     * @return 验证结果
     */
    boolean validates(DevicesValidate devicesValidate);

    /**
     * 查询可以收到设备UUID为 目标值 的用户UUID
     * */
    List<String> getUserUuidByDeviceUuid(String deviceUuid);
}
