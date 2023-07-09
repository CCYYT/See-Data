package com.example.core.dao;

import com.example.common.page.PageInfo;
import com.example.core.domain.UserGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户组信息(UserGroup)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-08 14:53:03
 */
@Mapper
public interface UserGroupDao {

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
    UserGroup queryByUuid(String uuid);

    /**
     * 查询指定行数据
     *
     * @param pageInfo         分页对象
     * @return 对象列表
     */
    List<UserGroup> queryAllByLimit(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 新增数据
     *
     * @param userGroup 实例对象
     * @return 影响行数
     */
    int insert(UserGroup userGroup);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserGroup> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserGroup> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserGroup> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserGroup> entities);

    /**
     * 修改数据
     *
     * @param userGroup 实例对象
     * @return 影响行数
     */
    int update(UserGroup userGroup);

    /**
     * 通过uuid删除数据
     *
     * @param uuid
     * @return 影响行数
     */
    int deleteByUuid(String uuid);

}

