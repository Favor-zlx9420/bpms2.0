package com.rong.fundmanage.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 机构代理用户
 * @author      : lether
 * @createDate  : 2020-02-05
 */
@Table("`tb_org_proxy`")
@Data()
@Accessors(chain = true)
public class TbOrgProxy extends BaseEntity<TbOrgProxy> {
    /**
     * 用户id
     */
    @Column("`user_id`")
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 机构id
     */
    @Column("`party_id`")
    @ApiModelProperty("代理机构id")
    private Long partyId;
}