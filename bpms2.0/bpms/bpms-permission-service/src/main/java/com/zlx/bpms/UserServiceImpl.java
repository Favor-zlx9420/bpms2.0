package com.zlx.bpms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlx.bpms.bean.User;
import com.zlx.bpms.dao.UserDao;
import com.zlx.bpms.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Package: com.zlx.bpms
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:认证权限用户接口实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;


    @Override
    public User getUserByUserName(String username) {
        QueryWrapper<User> ew = new QueryWrapper<>();
        ew.eq("user_name", username);
        ew.eq("is_status", 1);
        return userDao.selectOne(ew);
    }

    @Override
    public List<String> findRolePermission(String username) {
        return userDao.findRolePermission(username);
    }
}
