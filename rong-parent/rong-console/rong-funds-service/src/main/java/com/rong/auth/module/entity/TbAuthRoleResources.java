package com.rong.auth.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 角色拥有的资源
 * @author      : Administrator
 * @createDate  : 2020-01-03
 */
@Table("`tb_auth_role_resources`")
@Data()
@Accessors(chain = true)
public class TbAuthRoleResources extends BaseEntity<TbAuthRoleResources> {
    /**
     * 角色id
     */
    @Column("`role_id`")
    private Long roleId;

    /**
     * 资源id
     */
    @Column("`resources_id`")
    private Long resourcesId;
}