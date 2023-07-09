package com.example.core.service;

import com.example.common.page.PageInfo;
import com.example.core.domain.DataType;

import java.util.List;

/**
 * 设备类型信息(DataType)表服务接口
 *
 * @author makejava
 * @since 2023-06-13 21:38:12
 */
public interface DataTypeService {

    /**
     * 查询所有数据
     *
     * @return 实例对象列表
     */
    List<DataType> getAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DataType queryById(Integer id);

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
     * @param dataType 实例对象
     * @return 是否成功
     */
    boolean insert(DataType dataType);

    /**
     * 修改数据
     *
     * @param dataType 实例对象
     * @return 是否成功
     */
    boolean update(DataType dataType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过dataType获取dataType对象
     * */
    DataType queryByDataType(String dataType);

}
