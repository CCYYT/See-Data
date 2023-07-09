package com.example.core.service.impl;

import com.example.common.page.PageInfo;
import com.example.core.domain.User;
import com.example.core.dao.UserDao;
import com.example.core.service.UserService;
import org.springframework.stereotype.Service;
import com.example.common.util.UuidUtil;

import javax.annotation.Resource;

/**
 * 用户信息(User)表服务实现类
 *
 * @author makejava
 * @since 2023-06-08 22:02:43
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;


    /**
     * 验证用户的用户名与密码是否正确
     *
     * @param user
     * @return 是否存在
     */
    @Override
    public boolean verifyUserAndPassword(User user) {
        return userDao.verifyUserAndPassword(user);
    }

    /**
     * 通过uuid查询单条数据
     *
     * @param uuid
     * @return 实例对象
     */
    @Override
    public User queryByUuid(String uuid) {
        return this.userDao.queryByUuid(uuid);
    }

    /**
     * 通过userName查询单条数据
     *
     * @param userName
     * @return 实例对象
     */
    @Override
    public User queryByUserName(String userName){
        return this.userDao.queryByUserName(userName);
    };

    /**
     * 分页查询
     *
     * @param pageInfo      分页对象
     * @return 查询结果
     */
    @Override
    public PageInfo queryByPage(PageInfo pageInfo) {
        pageInfo.setTotalElementNum(this.userDao.count(pageInfo));
        pageInfo.setContent(this.userDao.queryAllByLimit(pageInfo));
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(User user) {
        String uuid;
        if(this.userDao.userNameIsExist(user.getUserName())) return false;
        while (true){
            uuid = UuidUtil.getUuid();
            if(!this.userDao.uuidIsExist(uuid)){
                (user).setUuid(uuid);
                return this.userDao.insert(user) > 0;
            }
        }
    }


    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(User user) {
        return this.userDao.update(user) > 0;
    }

    /**
     * 通过uuid删除数据
     *
     * @param uuid
     * @return 是否成功
     */
    @Override
    public boolean deleteByUuid(String uuid) {
        return this.userDao.deleteByUuid(uuid) > 0;
    }
}
