package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 会员所属角色
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_mem_auth_role`")
@Data()
@Accessors(chain = true)
public class TbMemAuthRole extends BaseEntity<TbMemAuthRole> {
    /**
     * 用户id
     */
    @Column("`user_id`")
    private Long userId;

    /**
     * 角色id
     */
    @Column("`role_id`")
    private Long roleId;
}