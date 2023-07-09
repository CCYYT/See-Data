package com.example.core.service;

import com.example.common.page.PageInfo;
import com.example.core.domain.Warning;
import com.example.core.domain.WarningRules;

import java.util.List;

/**
 * 预警规则(WarningRules)表服务接口
 *
 * @author makejava
 * @since 2023-06-17 12:05:48
 */
public interface WarningRulesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WarningRules queryById(Integer id);

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
     * @param warningRules 实例对象
     * @return 是否成功
     */
    boolean insert(WarningRules warningRules);

    /**
     * 修改数据
     *
     * @param warningRules 实例对象
     * @return 是否成功
     */
    boolean update(WarningRules warningRules);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过设备uuid与数据类型查询数据
     *
     * @param deviceUuid 设备uuid
     * @param dataType   数据类型
     * @return 预警规则列表
     */
    List<WarningRules> queryByDeviceUuidAndDataType(String deviceUuid, String dataType);

    /**
     * 通过校验规则集 校验数据
     *
     * @param warningRules 校验规则集
     * @param data         数据
     * @param deviceUuid   设备UUID
     * @return 是否成功
     */
    List<Warning> check(List<WarningRules> warningRules, String data, String deviceUuid);

}
