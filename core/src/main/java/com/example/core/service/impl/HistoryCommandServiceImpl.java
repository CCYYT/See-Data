package com.example.core.service.impl;

import com.example.common.page.PageInfo;
import com.example.core.domain.HistoryCommand;
import com.example.core.dao.HistoryCommandDao;
import com.example.core.service.HistoryCommandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 历史命令信息(HistoryCommand)表服务实现类
 *
 * @author makejava
 * @since 2023-07-03 16:59:27
 */
@Service("historyCommandService")
public class HistoryCommandServiceImpl implements HistoryCommandService {
    @Resource
    private HistoryCommandDao historyCommandDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HistoryCommand queryById(Integer id) {
        return this.historyCommandDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo queryByPage(PageInfo pageInfo) {
        pageInfo.setTotalElementNum(this.historyCommandDao.count(pageInfo));
        pageInfo.setContent(this.historyCommandDao.queryAllByLimit(pageInfo));
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param historyCommand 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(HistoryCommand historyCommand) {
        return this.historyCommandDao.insert(historyCommand) > 0;
    }


    /**
     * 修改数据
     *
     * @param historyCommand 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(HistoryCommand historyCommand) {
        return this.historyCommandDao.update(historyCommand) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.historyCommandDao.deleteById(id) > 0;
    }
}
