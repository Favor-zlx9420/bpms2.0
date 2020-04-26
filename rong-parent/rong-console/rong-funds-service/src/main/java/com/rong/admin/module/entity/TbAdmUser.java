package com.rong.admin.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description : 管理员
 * @author      : Administrator
 * @createDate  : 2020-01-03
 */
@Table("`tb_adm_user`")
@Data()
@Accessors(chain = true)
public class TbAdmUser extends BaseEntity<TbAdmUser> {
    /**
     * 使用状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 管理员登录名
     */
    @Column("`user_name`")
    private String userName;

    /**
     * 登录密码
     */
    @Column("`password`")
    private String password;

    /**
     * 密码随机数
     */
    @Column("`salt`")
    private String salt;

    /**
     * 手机号码
     */
    @Column("`phone`")
    private String phone;

    /**
     * 邮箱地址
     */
    @Column("`email`")
    private String email;

    /**
     * 真实姓名
     */
    @Column("`real_name`")
    private String realName;

    /**
     * 证件号码
     */
    @Column("`id_number`")
    private String idNumber;

    /**
     * 昵称
     */
    @Column("`nick_name`")
    private String nickName;

    /**
     * 角色Id
     */
    @Column("`role_id`")
    private Long roleId;

    /**
     * 临时tokenkey，每次登录需要清除一次
     */
    @Column("`auth_token`")
    private String authToken;

    /**
     * 最后一次登录时间
     */
    @Column("`last_login_date`")
    private Date lastLoginDate;

    /**
     * 密码过期时间
     */
    @Column("`password_expiration`")
    private Date passwordExpiration;

    /**
     * 权限字符串
     */
    @Column("`permission_str`")
    private String permissionStr;
}