package com.zlx.bpms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlx.bpms.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.zlx.bpms.dao
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:用户数据交互接口
 */
public interface UserDao extends BaseMapper<User> {
    List<String> findRolePermission(@Param("username") String username);
}
