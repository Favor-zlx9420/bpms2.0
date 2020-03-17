package com.zlx.bpms;

import com.zlx.bpms.bean.User;
import com.zlx.bpms.bean.UserDetail;
import com.zlx.bpms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Package: com.zlx.bpms
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:用户详细信息服务接口实现类
 */
@Component("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        //查询该用户具有的权限
        List<String> list = userService.findRolePermission(username);
        log.info("通过用户名为:{},获取到的用户信息为:{}", username, user);
        //region 实体类封装
        UserDetail detail = new UserDetail();
        if (null != user) {
            detail.setUsername(user.getUserName());
            detail.setPassword(user.getPassword());
            //region 权限组装
            Set<SimpleGrantedAuthority> authoritiesSet = new HashSet<SimpleGrantedAuthority>();
            for (String role : list) {
                SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority(role);
                authoritiesSet.add(roleAdmin);
            }
            //endregion
            detail.setAuthorities(authoritiesSet);
        }
        //endregion
        return detail;
    }
}
