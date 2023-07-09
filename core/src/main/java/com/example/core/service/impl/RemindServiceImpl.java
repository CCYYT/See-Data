package com.example.core.service.impl;

import com.example.common.page.PageInfo;
import com.example.core.domain.Remind;
import com.example.core.dao.RemindDao;
import com.example.core.service.RemindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 提示接收中间表(Remind)表服务实现类
 *
 * @author makejava
 * @since 2023-07-02 15:04:56
 */
@Service("remindService")
public class RemindServiceImpl implements RemindService {
    @Resource
    private RemindDao remindDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Remind queryById(Integer id) {
        return this.remindDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo queryByPage(PageInfo pageInfo) {
        pageInfo.setTotalElementNum(this.remindDao.count(pageInfo));
        pageInfo.setContent(this.remindDao.queryAllByLimit(pageInfo));
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param remind 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(Remind remind) {
        return this.remindDao.insert(remind) > 0;
    }


    /**
     * 修改数据
     *
     * @param remind 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Remind remind) {
        return this.remindDao.update(remind) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.remindDao.deleteById(id) > 0;
    }

    /**
     * 通过 预警规则id 查找 用户邮箱
     *
     * @param warningRulesId 预警规则id
     * @return 用户邮箱列表
     */
    @Override
    public List<String> queryUserMailByWarningRulesId(Integer warningRulesId){
        return this.remindDao.queryUserMailByWarningRulesId(warningRulesId);
    }
}
