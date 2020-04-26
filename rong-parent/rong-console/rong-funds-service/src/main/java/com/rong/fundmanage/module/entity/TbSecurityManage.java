package com.rong.fundmanage.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @description : 证券管理
 * @author      : lether
 * @createDate  : 2020-02-05
 */
@Table("`tb_security_manage`")
@Data()
@Accessors(chain = true)
public class TbSecurityManage extends BaseEntity<TbSecurityManage> {
    /**
     * 证券id
     */
    @Column("`security_id`")
    private Long securityId;

    /**
     * 排序
     */
    @Column("`sort`")
    private BigDecimal sort;

    /**
     * 是否可见
     */
    @Column("`visible`")
    private Boolean visible;

    /**
     * 是否推荐
     */
    @Column("`recommend`")
    private Boolean recommend;

    /**
     * 是否热门搜索
     */
    @Column("`hot_search`")
    private Boolean hotSearch;

    /**
     * 0私募，1信托，2公募
     */
    @Column("`type`")
    private Integer type;
}