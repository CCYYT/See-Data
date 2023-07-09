package com.example.core.service.impl;

import com.example.common.page.PageInfo;
import com.example.core.domain.UserGroup;
import com.example.core.dao.UserGroupDao;
import com.example.core.service.UserGroupService;
import com.example.common.util.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户组信息(UserGroup)表服务实现类
 *
 * @author makejava
 * @since 2023-06-08 14:53:03
 */
@Service("userGroupService")
public class UserGroupServiceImpl implements UserGroupService {
    @Resource
    private UserGroupDao userGroupDao;

    /**
     * 通过uuid查询单条数据
     *
     * @param uuid
     * @return 实例对象
     */
    @Override
    public UserGroup queryByUuid(String uuid) {
        return this.userGroupDao.queryByUuid(uuid);
    }

    /**
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo queryByPage(PageInfo pageInfo) {
        pageInfo.setTotalElementNum(this.userGroupDao.count(pageInfo));
        pageInfo.setContent(this.userGroupDao.queryAllByLimit(pageInfo));
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param userGroup 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(UserGroup userGroup) {
        String uuid;
        while (true){
            uuid = UuidUtil.getUuid();
            if(!this.userGroupDao.uuidIsExist(uuid))
                userGroup.setUuid(uuid);
                return this.userGroupDao.insert(userGroup) > 0;
        }
    }


    /**
     * 修改数据
     *
     * @param userGroup 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(UserGroup userGroup) {
        return this.userGroupDao.update(userGroup) > 0;
    }

    /**
     * 通过uuid删除数据
     *
     * @param uuid
     * @return 是否成功
     */
    @Override
    public boolean deleteByUuid(String uuid) {
        return this.userGroupDao.deleteByUuid(uuid) > 0;
    }
}
