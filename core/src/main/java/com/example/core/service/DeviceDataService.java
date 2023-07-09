package com.example.core.service;

import com.example.common.domain.device.queue.DeviceCustomData;
import com.example.common.page.PageInfo;
import com.example.core.domain.DeviceData;

/**
 * 设备数据信息(DeviceData)表服务接口
 *
 * @author makejava
 * @since 2023-06-16 18:20:55
 */
public interface DeviceDataService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DeviceData queryById(Integer id);

    /**
     * 通过设备(deviceUuid)查询最新的一条数据
     *
     * @param deviceUuid
     * @return DeviceData
     */

    DeviceData getLatestOneByDeviceUuid(String deviceUuid);

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
     * @param deviceData 实例对象
     * @return 是否成功
     */
    boolean insert(DeviceData deviceData);

    /**
     * 新增数据
     *
     * @param deviceCustomData 实例对象
     * @return 是否成功
     */
    boolean insert(DeviceCustomData deviceCustomData);

    /**
     * 修改数据
     *
     * @param deviceData 实例对象
     * @return 是否成功
     */
    boolean update(DeviceData deviceData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
