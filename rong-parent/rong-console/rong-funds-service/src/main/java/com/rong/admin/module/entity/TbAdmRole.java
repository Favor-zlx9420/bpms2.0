package com.rong.admin.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 角色
 * @author      : Administrator
 * @createDate  : 2020-01-03
 */
@Table("`tb_adm_role`")
@Data()
@Accessors(chain = true)
public class TbAdmRole extends BaseEntity<TbAdmRole> {
    /**
     * 角色状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 角色名称
     */
    @Column("`name`")
    private String name;

    /**
     * 描述
     */
    @Column("`description`")
    private String description;

    /**
     * 权限字符串
     */
    @Column("`permission_str`")
    private String permissionStr;
}