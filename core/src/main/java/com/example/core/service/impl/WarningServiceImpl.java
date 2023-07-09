package com.example.core.service.impl;

import com.example.common.page.PageInfo;
import com.example.core.domain.Warning;
import com.example.core.dao.WarningDao;
import com.example.core.domain.WarningRules;
import com.example.core.service.WarningService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 警报信息(Warning)表服务实现类
 *
 * @author makejava
 * @since 2023-06-17 11:55:35
 */
@Service("warningService")
public class WarningServiceImpl implements WarningService {
    @Resource
    private WarningDao warningDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Warning queryById(Integer id) {
        return this.warningDao.queryById(id);
    }

    /**
     * 查询用户组中的所有设备的警报信息
     * */
    @Override
    public List<Warning> getDeviceWarningListByGroupId(Integer groupId){
        return this.warningDao.getDeviceWarningListByGroupId(groupId);
    }

    /**
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo queryByPage(PageInfo pageInfo) {
        pageInfo.setTotalElementNum(this.warningDao.count(pageInfo));
        pageInfo.setContent(this.warningDao.queryAllByLimit(pageInfo));
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param warning 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(Warning warning) {
        return this.warningDao.insert(warning) > 0;
    }


    /**
     * 修改数据
     *
     * @param warning 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Warning warning) {
        return this.warningDao.update(warning) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.warningDao.deleteById(id) > 0;
    }

    /**
     * 批量添加数据
     *
     * @param warningList 警告列表
     * @return 是否成功
     */@Override
    public boolean batchInsert(List<Warning> warningList){
         return this.warningDao.insertBatch(warningList) > 0;
    }
}
