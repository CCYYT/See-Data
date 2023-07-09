package com.example.core.dao;

import com.example.common.page.PageInfo;
import com.example.core.domain.Warning;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 警报信息(Warning)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-17 11:55:35
 */
@Mapper
public interface WarningDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Warning queryById(Integer id);

    /**
     * 查询用户组中的所有设备的警报信息
     * */
    List<Warning> getDeviceWarningListByGroupId(Integer groupId);

    /**
     * 查询指定行数据
     *
     * @param pageInfo         分页对象
     * @return 对象列表
     */
    List<Warning> queryAllByLimit(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 新增数据
     *
     * @param warning 实例对象
     * @return 影响行数
     */
    int insert(Warning warning);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Warning> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Warning> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Warning> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Warning> entities);

    /**
     * 修改数据
     *
     * @param warning 实例对象
     * @return 影响行数
     */
    int update(Warning warning);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

