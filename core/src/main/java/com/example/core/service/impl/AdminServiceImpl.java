package com.example.core.service.impl;

import com.example.common.page.PageInfo;
import com.example.core.domain.Admin;
import com.example.core.dao.AdminDao;
import com.example.core.service.AdminService;
import com.example.common.util.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 16:52:34
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    /**
     * 验证管理员的用户名与密码是否正确
     *
     * @param admin
     * @return 是否存在
     */
    @Override
    public boolean verifyNameAndPassword(Admin admin){
        return adminDao.verifyNameAndPassword(admin);
    };

    /**
     * 通过uuID查询单条数据
     *
     * @param uuid
     * @return 实例对象
     */
    @Override
    public Admin queryByUuid(String uuid) {
        return this.adminDao.queryByUuid(uuid);
    }

    /**
     * 通过name查询单条数据
     *
     * @param name
     * @return 实例对象
     */
    @Override
    public Admin queryByName(String name) {
        return this.adminDao.queryByName(name);
    }

    /**
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo queryByPage(PageInfo pageInfo) {
        pageInfo.setTotalElementNum(this.adminDao.count(pageInfo));
        pageInfo.setContent(this.adminDao.queryAllByLimit(pageInfo));
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(Admin admin) {
        String uuid;
        if(this.adminDao.nameIsExist(admin.getName())) return false;
        while (true){
            uuid = UuidUtil.getUuid();
            if(!this.adminDao.uuidIsExist(uuid))
                admin.setUuid(uuid);
                return this.adminDao.insert(admin) > 0;
        }
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Admin admin) {
        return this.adminDao.update(admin) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.adminDao.deleteById(id) > 0;
    }
}
