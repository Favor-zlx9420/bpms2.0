package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 企业用户信息
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_mem_company_userinfo`")
@Data()
@Accessors(chain = true)
public class TbMemCompanyUserinfo extends BaseEntity<TbMemCompanyUserinfo> {
    /**
     * 企业名称
     */
    @Column("`company_name`")
    private String companyName;

    /**
     * 国家
     */
    @Column("`country`")
    private String country;

    /**
     * 省
     */
    @Column("`province`")
    private String province;

    /**
     * 市
     */
    @Column("`city`")
    private String city;

    /**
     * 区
     */
    @Column("`area`")
    private String area;

    /**
     * 详细地址
     */
    @Column("`address`")
    private String address;

    /**
     * 固话
     */
    @Column("`call`")
    private String call;

    /**
     * 邮编
     */
    @Column("`post_code`")
    private String postCode;
}