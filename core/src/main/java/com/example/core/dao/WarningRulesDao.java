package com.example.core.dao;

import com.example.common.page.PageInfo;
import com.example.core.domain.WarningRules;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 预警规则(WarningRules)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-17 12:05:48
 */
@Mapper
public interface WarningRulesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WarningRules queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param pageInfo         分页对象
     * @return 对象列表
     */
    List<WarningRules> queryAllByLimit(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 新增数据
     *
     * @param warningRules 实例对象
     * @return 影响行数
     */
    int insert(WarningRules warningRules);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WarningRules> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WarningRules> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WarningRules> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WarningRules> entities);

    /**
     * 修改数据
     *
     * @param warningRules 实例对象
     * @return 影响行数
     */
    int update(WarningRules warningRules);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过设备uuid与数据类型查询数据
     *
     * @param deviceUuid 设备uuid
     * @param dataType   数据类型
     * @return 是否成功
     */
    List<WarningRules> queryByDeviceUuidAndDataType(@Param("deviceUuid") String deviceUuid,@Param("dataType") String dataType);

}

