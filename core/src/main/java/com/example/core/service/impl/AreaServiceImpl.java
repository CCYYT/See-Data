package com.example.core.service.impl;

import com.example.common.page.PageInfo;
import com.example.core.domain.Area;
import com.example.core.dao.AreaDao;
import com.example.core.service.AreaService;
import org.springframework.stereotype.Service;
import com.example.common.util.UuidUtil;

import javax.annotation.Resource;

/**
 * 区域信息(Area)表服务实现类
 *
 * @author makejava
 * @since 2023-06-13 21:20:44
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService {
    @Resource
    private AreaDao areaDao;

    /**
     * 通过uuid查询单条数据
     *
     * @param uuid
     * @return 实例对象
     */
    @Override
    public Area queryByUuid(String uuid) {
        return this.areaDao.queryByUuid(uuid);
    }

    /**
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo queryByPage(PageInfo pageInfo) {
        pageInfo.setTotalElementNum(this.areaDao.count(pageInfo));
        pageInfo.setContent(this.areaDao.queryAllByLimit(pageInfo));
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param area 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(Area area) {
        String uuid;
        while (true){
            uuid = UuidUtil.getUuid();
            if(!this.areaDao.uuidIsExist(uuid)){
                (area).setUuid(uuid);
                return this.areaDao.insert(area) > 0;
            }
        }
    }


    /**
     * 修改数据
     *
     * @param area 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Area area) {
        return this.areaDao.update(area) > 0;
    }

    /**
     * 通过uuid删除数据
     *
     * @param uuid
     * @return 是否成功
     */
    @Override
    public boolean deleteByUuid(String uuid) {
        return this.areaDao.deleteByUuid(uuid) > 0;
    }
}
