package com.rong.auth.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @description : 授权角色
 * @author      : Administrator
 * @createDate  : 2020-01-03
 */
@Table("`tb_auth_role`")
@Data()
@Accessors(chain = true)
public class TbAuthRole extends BaseEntity<TbAuthRole> {
    /**
     * 使用状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 排序
     */
    @Column("`sort`")
    private BigDecimal sort;

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
     * 角色标志
     */
    @Column("`symbol`")
    private String symbol;
}