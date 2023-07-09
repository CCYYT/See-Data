package com.example.core.dao;

import com.example.common.page.PageInfo;
import com.example.core.domain.DataType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 设备类型信息(DataType)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-13 21:38:12
 */
@Mapper
public interface DataTypeDao {
    /**
     * 查找dataType是否存在
     *
     * @param dataType
     * @return 是否存在
     */
    boolean dataTypeIsExist(String dataType);


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
     * 查询指定行数据
     *
     * @param pageInfo         分页对象
     * @return 对象列表
     */
    List<DataType> queryAllByLimit(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 新增数据
     *
     * @param dataType 实例对象
     * @return 影响行数
     */
    int insert(DataType dataType);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DataType> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DataType> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DataType> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DataType> entities);

    /**
     * 修改数据
     *
     * @param dataType 实例对象
     * @return 影响行数
     */
    int update(DataType dataType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过dataType获取dataType对象
     * */
    DataType queryByDataType(String dataType);

}

