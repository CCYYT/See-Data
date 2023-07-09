package com.example.core.service;

import com.example.common.page.PageInfo;
import com.example.core.domain.HistoryCommand;

/**
 * 历史命令信息(HistoryCommand)表服务接口
 *
 * @author makejava
 * @since 2023-07-03 16:59:27
 */
public interface HistoryCommandService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HistoryCommand queryById(Integer id);

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
     * @param historyCommand 实例对象
     * @return 是否成功
     */
    boolean insert(HistoryCommand historyCommand);

    /**
     * 修改数据
     *
     * @param historyCommand 实例对象
     * @return 是否成功
     */
    boolean update(HistoryCommand historyCommand);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
