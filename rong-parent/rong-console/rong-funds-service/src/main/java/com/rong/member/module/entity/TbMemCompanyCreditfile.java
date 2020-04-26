package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 企业用户信用档案
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_mem_company_creditfile`")
@Data()
@Accessors(chain = true)
public class TbMemCompanyCreditfile extends BaseEntity<TbMemCompanyCreditfile> {

    /**
     * 营业执照
     */
    @Column("`business_license`")
    private String businessLicense;

    /**
     * 营业执照urls
     */
    @Column("`business_license_urls`")
    private String businessLicenseUrls;

    /**
     * 营业执照验证状态
     */
    @Column("`business_license_certify_state`")
    private Integer businessLicenseCertifyState;

    /**
     * 注册资本信息
     */
    @Column("`registered_capital`")
    private String registeredCapital;

    /**
     * 注册资本urls
     */
    @Column("`registered_capital_urls`")
    private String registeredCapitalUrls;

    /**
     * 注册资本验证状态
     */
    @Column("`registered_capital_certify_state`")
    private Integer registeredCapitalCertifyState;

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
     * 公司资产信息
     */
    @Column("`company_assets`")
    private String companyAssets;

    /**
     * 公司资产信息urls
     */
    @Column("`company_assets_urls`")
    private String companyAssetsUrls;

    /**
     * 公司资产验证状态
     */
    @Column("`company_assets_certify_state`")
    private Integer companyAssetsCertifyState;

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