package com.example.core.dao;

import com.example.common.page.PageInfo;
import com.example.core.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Admin)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-03 16:52:22
 */
@Mapper
public interface AdminDao {

    /**
     * 验证管理员的用户名与密码是否正确
     *
     * @param admin
     * @return 是否存在
     */
    boolean verifyNameAndPassword(@Param("admin") Admin admin);


    /**
     * 查找uuid是否存在
     *
     * @param uuid
     * @return 是否存在
     */
    boolean uuidIsExist(String uuid);

    /**
     * 查找Name是否存在
     *
     * @param name
     * @return 是否存在
     */
    boolean nameIsExist(String name);

    /**
     * 通过uuID查询单条数据
     *
     * @param uuid
     * @return 实例对象
     */
    Admin queryByUuid(String uuid);

    /**
     * 通过name查询单条数据
     *
     * @param name
     * @return 实例对象
     */
    Admin queryByName(String name);

    /**
     * 查询指定行数据
     *
     * @param pageInfo         分页对象
     * @return 对象列表
     */
    List<Admin> queryAllByLimit(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 统计总行数
     *
     * @param pageInfo 查询条件
     * @return 总行数
     */
    long count(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    int insert(Admin admin);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Admin> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Admin> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Admin> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Admin> entities);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    int update(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

