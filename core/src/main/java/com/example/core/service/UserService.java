package com.example.core.service;

import com.example.common.page.PageInfo;
import com.example.core.domain.User;

/**
 * 用户信息(User)表服务接口
 *
 * @author makejava
 * @since 2023-06-08 22:02:43
 */
public interface UserService {

    /**
     * 验证用户的用户名与密码是否正确
     *
     * @param user
     * @return 是否存在
     */
    boolean verifyUserAndPassword(User user);


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
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    PageInfo queryByPage(PageInfo pageInfo);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    boolean insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    boolean update(User user);

    /**
     * 通过uuid删除数据
     *
     * @param uuid
     * @return 是否成功
     */
    boolean deleteByUuid(String uuid);

}
