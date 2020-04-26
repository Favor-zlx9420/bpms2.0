package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 个人用户信用档案
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_mem_personal_creditfile`")
@Data()
@Accessors(chain = true)
public class TbMemPersonalCreditfile extends BaseEntity<TbMemPersonalCreditfile> {
    /**
     * 最高学历（教育程度）
     */
    @Column("`education_level`")
    private Integer educationLevel;

    /**
     * 毕业院校
     */
    @Column("`graduated_school`")
    private String graduatedSchool;

    /**
     * 教育认证信息url
     */
    @Column("`education_certify_urls`")
    private String educationCertifyUrls;

    /**
     * 教育认证结果(未验证:0,申请验证中:2,验证通过:1,验证无效:-1,重新申请:3)
     */
    @Column("`education_certify_state`")
    private Integer educationCertifyState;

    /**
     * 专业信息
     */
    @Column("`discipline_info`")
    private String disciplineInfo;

    /**
     * 工作年限
     */
    @Column("`jobs_years`")
    private Integer jobsYears;

    /**
     * 技术职称
     */
    @Column("`technical_titles`")
    private String technicalTitles;

    /**
     * 工作信息urls
     */
    @Column("`jobs_certify_urls`")
    private String jobsCertifyUrls;

    /**
     * 工作验证状态
     */
    @Column("`jobs_certify_state`")
    private Integer jobsCertifyState;

    /**
     * 工作信息
     */
    @Column("`jobs_info`")
    private String jobsInfo;

    /**
     * 驾照信息
     */
    @Column("`driver_license`")
    private String driverLicense;

    /**
     * 驾照信息urls
     */
    @Column("`driver_license_certify_urls`")
    private String driverLicenseCertifyUrls;

    /**
     * 驾照验证状态
     */
    @Column("`driver_license_certify_state`")
    private Integer driverLicenseCertifyState;

    /**
     * 车辆信息,多辆以,分隔
     */
    @Column("`car_info`")
    private String carInfo;

    /**
     * 车辆验证urls
     */
    @Column("`car_certify_urls`")
    private String carCertifyUrls;

    /**
     * 车辆验证状态(判断是否有车依据)
     */
    @Column("`car_certify_state`")
    private Integer carCertifyState;

    /**
     * 房产证信息,多套以,分隔
     */
    @Column("`estate_info`")
    private String estateInfo;

    /**
     * 房产验证urls
     */
    @Column("`estate_certify_urls`")
    private String estateCertifyUrls;

    /**
     * 房产验证状态
     */
    @Column("`estate_certify_state`")
    private Integer estateCertifyState;

    /**
     * 股票基金信息
     */
    @Column("`stock_fund`")
    private String stockFund;

    /**
     * 股票基金url
     */
    @Column("`stock_fund_certify_urls`")
    private String stockFundCertifyUrls;

    /**
     * 股票基金验证状态
     */
    @Column("`stock_fund_certify_state`")
    private Integer stockFundCertifyState;

    /**
     * 其他信用信息
     */
    @Column("`other_info`")
    private String otherInfo;

    /**
     * 其他信息url
     */
    @Column("`other_info_certify_urls`")
    private String otherInfoCertifyUrls;

    /**
     * 其他信息验证状态
     */
    @Column("`other_info_certify_state`")
    private Integer otherInfoCertifyState;

    /**
     * 信用积分
     */
    @Column("`credit_points`")
    private Integer creditPoints;
}