package com.example.core.service.impl;

import com.example.common.domain.device.queue.DeviceCustomData;
import com.example.common.page.PageInfo;
import com.example.core.domain.DeviceData;
import com.example.core.dao.DeviceDataDao;
import com.example.core.service.DeviceDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 设备数据信息(DeviceData)表服务实现类
 *
 * @author makejava
 * @since 2023-06-16 18:20:55
 */
@Service("deviceDataService")
public class DeviceDataServiceImpl implements DeviceDataService {
    @Resource
    private DeviceDataDao deviceDataDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DeviceData queryById(Integer id) {
        return this.deviceDataDao.queryById(id);
    }

    /**
     * 通过设备(deviceUuid)查询最新的一条数据
     *
     * @param deviceUuid
     * @return DeviceData
     */
    @Override
    public DeviceData getLatestOneByDeviceUuid(String deviceUuid){
        return this.deviceDataDao.getLatestOneByDeviceUuid(deviceUuid);
    }

    /**
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo queryByPage(PageInfo pageInfo) {
        pageInfo.setTotalElementNum(this.deviceDataDao.count(pageInfo));
        pageInfo.setContent(this.deviceDataDao.queryAllByLimit(pageInfo));
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param deviceData 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(DeviceData deviceData) {
        return this.deviceDataDao.insert(deviceData) > 0;
    }

    /**
     * 新增数据
     *
     * @param deviceCustomData 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(DeviceCustomData deviceCustomData) {
        return this.deviceDataDao.insertByDeviceCustomData(deviceCustomData) > 0;
    }


    /**
     * 修改数据
     *
     * @param deviceData 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(DeviceData deviceData) {
        return this.deviceDataDao.update(deviceData) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.deviceDataDao.deleteById(id) > 0;
    }
}
