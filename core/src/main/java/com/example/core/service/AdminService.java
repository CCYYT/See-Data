package com.example.core.service;

import com.example.common.page.PageInfo;
import com.example.core.domain.Admin;

/**
 * (Admin)表服务接口
 *
 * @author makejava
 * @since 2023-06-03 16:52:34
 */
public interface AdminService {

    /**
     * 验证管理员的用户名与密码是否正确
     *
     * @param admin
     * @return 是否存在
     */
    boolean verifyNameAndPassword(Admin admin);

    /**
     * 通过uuID查询单条数据
     *
     * @param uuid
     * @return 实例对象
     */
    Admin queryByUuid(String uuid);

    /**
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    PageInfo queryByPage(PageInfo pageInfo);

    /**
     * 通过name查询单条数据
     *
     * @param name
     * @return 实例对象
     */
    Admin queryByName(String name);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 是否成功
     */
    boolean insert(Admin admin);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 是否成功
     */
    boolean update(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
