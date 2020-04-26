package com.rong.usercard.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 用户名片信息
 * @author      : Administrator
 * @createDate  : 2020-03-10
 */
@Table("`tb_user_card_info`")
@Data()
@Accessors(chain = true)
public class TbUserCardInfo extends BaseEntity<TbUserCardInfo> {
    /**
     * 用户ID
     */
    @Column("`user_id`")
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 是否可见,(true:可见，false：不可见)
     */
    @Column("`visible`")
    @ApiModelProperty("是否可见,(true:可见，false：不可见)")
    private Boolean visible;

    /**
     * 是否热门
     */
    @Column("`hot_search`")
    @ApiModelProperty("是否热门")
    private Boolean hotSearch;

    /**
     * 排序，越小越在前面
     */
    @Column("`sort`")
    @ApiModelProperty("排序，越小越在前面")
    private BigDecimal sort;

    /**
     * 是否推荐
     */
    @Column("`recommend`")
    @ApiModelProperty("是否推荐")
    private Boolean recommend;

    /**
     * 头像地址
     */
    @Column("`head_portrait`")
    @ApiModelProperty("头像地址")
    private String headPortrait;

    /**
     * 姓
     */
    @Column("`first_name`")
    @ApiModelProperty("姓")
    private String firstName;

    /**
     * 名
     */
    @Column("`last_name`")
    @ApiModelProperty("名")
    private String lastName;

    /**
     * 全名
     */
    @Column("`full_name`")
    @ApiModelProperty("全名")
    private String fullName;

    /**
     * 手机号码
     */
    @Column("`phone`")
    @ApiModelProperty("手机号码")
    private String phone;

    /**
     * 邮箱地址
     */
    @Column("`email`")
    @ApiModelProperty("邮箱地址")
    private String email;

    /**
     * 微信号
     */
    @Column("`wx_no`")
    @ApiModelProperty("微信号")
    private String wxNo;

    /**
     * 公司名称
     */
    @Column("`company`")
    @ApiModelProperty("公司名称")
    private String company;

    /**
     * 职位
     */
    @Column("`position`")
    @ApiModelProperty("职位")
    private String position;

    /**
     * 座机
     */
    @Column("`call`")
    @ApiModelProperty("座机")
    private String call;

    /**
     * 传真
     */
    @Column("`fax`")
    @ApiModelProperty("传真")
    private String fax;

    /**
     * 地址
     */
    @Column("`address`")
    @ApiModelProperty("地址")
    private String address;

    /**
     * 家乡
     */
    @Column("`hometown`")
    @ApiModelProperty("家乡")
    private String hometown;

    /**
     * 个人简介
     */
    @Column("`personal_introduction`")
    @ApiModelProperty("个人简介")
    private String personalIntroduction;

    /**
     * 公司简介
     */
    @Column("`company_introduction`")
    @ApiModelProperty("公司简介")
    private String companyIntroduction;

    /**
     * 团队简介
     */
    @Column("`team_introduction`")
    @ApiModelProperty("团队简介")
    private String teamIntroduction;

    /**
     * 荣耀
     */
    @Column("`glory`")
    @ApiModelProperty("荣耀")
    private String glory;

    /**
     * 名片板式编号
     */
    @Column("`style`")
    @ApiModelProperty("名片板式编号")
    private Long style;

    /**
     * 名片背景编号
     */
    @Column("`background`")
    @ApiModelProperty("名片背景编号")
    private Long background;

    /**
     * 对方申请交换名片需认证,(true:需认证，false：无需同意)
     */
    @Column("`swap_verify`")
    @ApiModelProperty("对方申请交换名片需认证,(true:需认证，false：无需同意)")
    private Boolean swapVerify;

    /**
     * 名片联系方式公开可见,(true:公开可见，false：非公开可见)
     */
    @Column("`contact_public_visible`")
    @ApiModelProperty("名片联系方式公开可见,(true:公开可见，false：非公开可见)")
    private Boolean contactPublicVisible;
}