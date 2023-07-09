package com.example.core.service.impl;

import com.example.common.page.PageInfo;
import com.example.core.domain.Warning;
import com.example.core.domain.WarningRules;
import com.example.core.dao.WarningRulesDao;
import com.example.core.event.WarningEvent;
import com.example.core.service.WarningRulesService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 预警规则(WarningRules)表服务实现类
 *
 * @author makejava
 * @since 2023-06-17 12:05:48
 */
@Service("warningRulesService")
public class WarningRulesServiceImpl implements WarningRulesService {
    @Resource
    private WarningRulesDao warningRulesDao;
    @Resource
    private ApplicationContext applicationContext;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WarningRules queryById(Integer id) {
        return this.warningRulesDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo queryByPage(PageInfo pageInfo) {
        pageInfo.setTotalElementNum(this.warningRulesDao.count(pageInfo));
        pageInfo.setContent(this.warningRulesDao.queryAllByLimit(pageInfo));
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param warningRules 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(WarningRules warningRules) {
        return this.warningRulesDao.insert(warningRules) > 0;
    }


    /**
     * 修改数据
     *
     * @param warningRules 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(WarningRules warningRules) {
        return this.warningRulesDao.update(warningRules) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.warningRulesDao.deleteById(id) > 0;
    }

    /**
     * 通过设备uuid与数据类型查询数据
     *
     * @param deviceUuid 设备uuid
     * @param dataType   数据类型
     * @return 是否成功
     */
    @Override
    public List<WarningRules> queryByDeviceUuidAndDataType(String deviceUuid, String dataType){
        if(deviceUuid == null || dataType == null)return null;
        return this.warningRulesDao.queryByDeviceUuidAndDataType(deviceUuid,dataType);
    }

    /**
     * 通过校验规则集 校验数据
     *
     * @param warningRules 校验规则集
     * @param data         数据
     * @param deviceUuid   设备UUID
     * @return 预警
     */
    @Override
    public List<Warning> check(List<WarningRules> warningRules,String data,String deviceUuid){
        if(warningRules == null) return null;
        Integer dataI = Integer.valueOf(data);
        ArrayList<Warning> warningList = new ArrayList<>();
        for (WarningRules warningRule : warningRules) {
            int com = dataI.compareTo(Integer.valueOf(warningRule.getWarningValue()))+1;
            if(warningRule.getWarningRules().compareTo(com) == 0) {
                Warning warning = new Warning(){{
                    setDeviceUuid(deviceUuid);
                    setWarningInfo(warningRule.getWarningInfo());
                    setWarningLevel(warningRule.getWarningLevel());
                    setDataTypeId(warningRule.getDataTypeId());
                    setCreateTime(new Date(System.currentTimeMillis()));
                    setStatus(0);
                }};

                warningList.add(warning);
                /**
                 * 发布数据警告事件
                 * */
                applicationContext.publishEvent(new WarningEvent(this,warning,warningRule));
            }
        }
        if(warningList.size()==0)return null;
        return warningList;
    }
}
