package com.example.core.service;

import com.example.common.page.PageInfo;
import com.example.core.domain.Area;

/**
 * 区域信息(Area)表服务接口
 *
 * @author makejava
 * @since 2023-06-13 21:20:44
 */
public interface AreaService {

    /**
     * 通过uuid查询单条数据
     *
     * @param uuid
     * @return 实例对象
     */
    Area queryByUuid(String uuid);

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
     * @param area 实例对象
     * @return 是否成功
     */
    boolean insert(Area area);

    /**
     * 修改数据
     *
     * @param area 实例对象
     * @return 是否成功
     */
    boolean update(Area area);

    /**
     * 通过uuid删除数据
     *
     * @param uuid
     * @return 是否成功
     */
    boolean deleteByUuid(String uuid);

}
