package com.example.core.dao;

import com.example.common.page.PageInfo;
import com.example.core.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息(User)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-08 22:02:43
 */
@Mapper
public interface UserDao {



    /**
     * 验证用户的用户名与密码是否正确
     *
     * @param user
     * @return 是否存在
     */
    boolean verifyUserAndPassword(@Param("user") User user);

    /**
     * 查找userName是否存在
     *
     * @param userName
     * @return 是否存在
     */
    boolean userNameIsExist(String userName);

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
    User queryByUuid(String uuid);

    /**
     * 通过userName查询单条数据
     *
     * @param userName
     * @return 实例对象
     */
    User queryByUserName(String userName);

    /**
     * 查询指定行数据
     *
     * @param pageInfo         分页对象
     * @return 对象列表
     */
    List<User> queryAllByLimit(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<User> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<User> entities);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过uuid删除数据
     *
     * @param uuid
     * @return 影响行数
     */
    int deleteByUuid(String uuid);

}

