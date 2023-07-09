package com.example.core.dao;

import com.example.common.domain.device.queue.DeviceCustomData;
import com.example.common.page.PageInfo;
import com.example.core.domain.DeviceData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 设备数据信息(DeviceData)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-16 18:20:55
 */
@Mapper
public interface DeviceDataDao {

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
     * 查询指定行数据
     *
     * @param pageInfo         分页对象
     * @return 对象列表
     */
    List<DeviceData> queryAllByLimit(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 新增数据
     *
     * @param deviceData 实例对象
     * @return 影响行数
     */
    int insert(DeviceData deviceData);

    /**
     * 新增数据
     *
     * @param deviceCustomData 实例对象
     * @return 影响行数
     */
    int insertByDeviceCustomData(DeviceCustomData deviceCustomData);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DeviceData> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DeviceData> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DeviceData> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DeviceData> entities);

    /**
     * 修改数据
     *
     * @param deviceData 实例对象
     * @return 影响行数
     */
    int update(DeviceData deviceData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

