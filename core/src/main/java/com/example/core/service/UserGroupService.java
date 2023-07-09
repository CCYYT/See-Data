package com.example.core.service;

import com.example.common.page.PageInfo;
import com.example.core.domain.UserGroup;

/**
 * 用户组信息(UserGroup)表服务接口
 *
 * @author makejava
 * @since 2023-06-08 14:53:03
 */
public interface UserGroupService {

    /**
     * 通过uuid查询单条数据
     *
     * @param uuid
     * @return 实例对象
     */
    UserGroup queryByUuid(String uuid);

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
     * @param userGroup 实例对象
     * @return 是否成功
     */
    boolean insert(UserGroup userGroup);

    /**
     * 修改数据
     *
     * @param userGroup 实例对象
     * @return 是否成功
     */
    boolean update(UserGroup userGroup);

    /**
     * 通过uuid删除数据
     *
     * @param uuid
     * @return 是否成功
     */
    boolean deleteByUuid(String uuid);

}
