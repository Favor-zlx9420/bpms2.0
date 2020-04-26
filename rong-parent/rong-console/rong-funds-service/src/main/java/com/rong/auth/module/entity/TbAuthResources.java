package com.rong.auth.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @description : 会员资源
 * @author      : Administrator
 * @createDate  : 2020-01-03
 */
@Table("`tb_auth_resources`")
@Data()
@Accessors(chain = true)
public class TbAuthResources extends BaseEntity<TbAuthResources> {
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
     * 资源名称
     */
    @Column("`name`")
    private String name;

    /**
     * 资源链接
     */
    @Column("`url_pattern`")
    private String urlPattern;

    /**
     * 资源类型
     */
    @Column("`type`")
    private Integer type;
}