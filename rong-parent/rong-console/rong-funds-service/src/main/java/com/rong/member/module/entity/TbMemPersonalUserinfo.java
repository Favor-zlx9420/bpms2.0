package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 个人用户信息表
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_mem_personal_userinfo`")
@Data()
@Accessors(chain = true)
public class TbMemPersonalUserinfo extends BaseEntity<TbMemPersonalUserinfo> {
    /**
     * 生日
     */
    @Column("`birth_date`")
    private Date birthDate;

    /**
     * 性别
     */
    @Column("`gender`")
    private Integer gender;

    /**
     * 真实姓名
     */
    @Column("`real_name`")
    private String realName;

    /**
     * 证件类型
     */
    @Column("`id_type`")
    private Integer idType;

    /**
     * 证件号码
     */
    @Column("`id_no`")
    private String idNo;

    /**
     * 手机号码
     */
    @Column("`phone`")
    private String phone;

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