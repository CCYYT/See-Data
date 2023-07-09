package com.example.core.dao;

import com.example.common.page.PageInfo;
import com.example.core.domain.Remind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 提示接收中间表(Remind)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-02 15:04:56
 */
@Mapper
public interface RemindDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Remind queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param pageInfo         分页对象
     * @return 对象列表
     */
    List<Remind> queryAllByLimit(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count(@Param("pageInfo") PageInfo pageInfo);

    /**
     * 新增数据
     *
     * @param remind 实例对象
     * @return 影响行数
     */
    int insert(Remind remind);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Remind> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Remind> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Remind> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Remind> entities);

    /**
     * 修改数据
     *
     * @param remind 实例对象
     * @return 影响行数
     */
    int update(Remind remind);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过 预警规则id 查找 用户邮箱
     *
     * @param warningRulesId 预警规则id
     * @return 用户邮箱列表
     */
     List<String> queryUserMailByWarningRulesId(Integer warningRulesId);

}

