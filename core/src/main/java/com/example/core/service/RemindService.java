package com.example.core.service;

import com.example.common.page.PageInfo;
import com.example.core.domain.Remind;

import java.util.List;

/**
 * 提示接收中间表(Remind)表服务接口
 *
 * @author makejava
 * @since 2023-07-02 15:04:56
 */
public interface RemindService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Remind queryById(Integer id);

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
     * @param remind 实例对象
     * @return 是否成功
     */
    boolean insert(Remind remind);

    /**
     * 修改数据
     *
     * @param remind 实例对象
     * @return 是否成功
     */
    boolean update(Remind remind);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过 预警规则id 查找 用户邮箱
     *
     * @param warningRulesId 预警规则id
     * @return 用户邮箱列表
     */
    List<String> queryUserMailByWarningRulesId(Integer warningRulesId);

}
