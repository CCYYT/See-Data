package com.example.core.service;

import com.example.common.page.PageInfo;
import com.example.core.domain.Warning;

import java.util.List;

/**
 * 警报信息(Warning)表服务接口
 *
 * @author makejava
 * @since 2023-06-17 11:55:35
 */
public interface WarningService {

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
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    PageInfo queryByPage(PageInfo pageInfo);

    /**
     * 新增数据
     *
     * @param warning 实例对象
     * @return 是否成功
     */
    boolean insert(Warning warning);

    /**
     * 修改数据
     *
     * @param warning 实例对象
     * @return 是否成功
     */
    boolean update(Warning warning);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 批量添加数据
     *
     * @param warningList 警告列表
     * @return 是否成功
     */
    boolean batchInsert(List<Warning> warningList);

}
