package com.rong.sys.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 友情链接表
 * @author      : lether
 * @createDate  : 2020-02-03
 */
@Table("`tb_comm_org_info`")
@Data()
@Accessors(chain = true)
public class TbOrgInfo extends BaseEntity<TbOrgInfo> {
    /**
     * 状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 排序
     */
    @Column("`sort`")
    private BigDecimal sort;

    /**
     * 机构名称
     */
    @Column("`name`")
    private String name;

    /**
     * 简称
     */
    @Column("`short_name`")
    private String shortName;

    /**
     * 服务电话
     */
    @Column("`service_phone`")
    private String servicePhone;

    /**
     * 地址
     */
    @Column("`area`")
    private String area;

    /**
     * 网址
     */
    @Column("`net_url`")
    private String netUrl;

    /**
     * 机构类型
     */
    @Column("`type`")
    private Integer type;
}