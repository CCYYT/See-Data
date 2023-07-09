package com.example.core.service.impl;

import com.example.common.page.PageInfo;
import com.example.common.util.UuidUtil;
import com.example.core.domain.DataType;
import com.example.core.dao.DataTypeDao;
import com.example.core.service.DataTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备类型信息(DataType)表服务实现类
 *
 * @author makejava
 * @since 2023-06-13 21:38:12
 */
@Service("deviceTypeService")
public class DataTypeServiceImpl implements DataTypeService {
    @Resource
    private DataTypeDao dataTypeDao;

    /**
     * 查询所有数据
     *
     * @return 实例对象列表
     */
    @Override
    public List<DataType> getAll() {
        return this.dataTypeDao.getAll();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DataType queryById(Integer id) {
        return this.dataTypeDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo queryByPage(PageInfo pageInfo) {
        pageInfo.setTotalElementNum(this.dataTypeDao.count(pageInfo));
        pageInfo.setContent(this.dataTypeDao.queryAllByLimit(pageInfo));
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param dataType 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(DataType dataType) {
        if(this.dataTypeDao.dataTypeIsExist(dataType.getDataType()))return false;
        return this.dataTypeDao.insert(dataType) > 0;
    }


    /**
     * 修改数据
     *
     * @param dataType 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(DataType dataType) {
        return this.dataTypeDao.update(dataType) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.dataTypeDao.deleteById(id) > 0;
    }

    /**
     * 通过dataType获取dataType对象
     * */
    @Override
    public DataType queryByDataType(String dataType){
        return this.dataTypeDao.queryByDataType(dataType);
    }
}
