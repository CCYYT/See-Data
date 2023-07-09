package com.example.core.dao;

import com.example.common.domain.device.openFeign.DeviceValidate;
import com.example.common.page.PageInfo;
import com.example.core.domain.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 设备信息(Device)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-16 17:17:26
 */
@Mapper
public interface DeviceDao {

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
     * 查找uuid是否存在
     *
     * @param uuid
     * @return 是否存在
     */
    boolean uuidIsExist(String uuid);

    /**
     * 通过uuid查询单条数据
     *
     * @param uuid
     * @return 实例对象
     */
    Device queryByUuid(String uuid);

    /**
     * 查询指定行数据
     *
     * @param pageInfo         分页对象
     * @return 对象列表
     */
    List<Device> queryAllByLimit(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 新增数据
     *
     * @param device 实例对象
     * @return 影响行数
     */
    int insert(Device device);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Device> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Device> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Device> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Device> entities);

    /**
     * 修改数据
     *
     * @param device 实例对象
     * @return 影响行数
     */
    int update(Device device);

    /**
     * 通过uuid删除数据
     *
     * @param uuid
     * @return 影响行数
     */
    int deleteByUuid(String uuid);

    /**
     * 验证设备 是否 属于 区域
     *
     * @param deviceValidate
     * @return 验证结果
     */
    boolean validate(DeviceValidate deviceValidate);

    /**
     * 查询可以收到设备UUID为 目标值 的用户UUID
     * */
    List<String> getUserUuidByDeviceUuid(String deviceUuid);

//    /** 6-28 标记 将废除
//     * 验证多个设备 是否 属于 区域
//     *
//     * @param devicesValidate
//     * @return 验证结果
//     */
//    boolean validates(DevicesValidate devicesValidate);
}

