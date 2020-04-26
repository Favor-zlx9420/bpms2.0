package com.rong.store.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 直营店入驻用户
 * @author      : lether
 * @createDate  : 2020-02-13
 */
@Table("`tb_direct_store_user`")
@Data()
@Accessors(chain = true)
public class TbDirectStoreUser extends BaseEntity<TbDirectStoreUser> {
    /**
     * （0，直营店客服；1，基金经理；2，机构代理，{@link com.rong.member.consts.MemEnumContainer.MemType containsKey}）
     */
    @Column("`type`")
    @ApiModelProperty("（0，直营店客服；1，基金经理；2，机构代理）")
    private Integer type;

    /**
     * 用户id
     */
    @Column("`user_id`")
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 是否可见
     */
    @Column("`visible`")
    @ApiModelProperty("是否可见")
    private Boolean visible;

    /**
     * 是否推荐
     */
    @Column("`recommend`")
    @ApiModelProperty("是否推荐")
    private Boolean recommend;

    /**
     * 排序，越小在越前面
     */
    @Column("`sort`")
    @ApiModelProperty("排序，越小在越前面")
    private BigDecimal sort;

    /**
     * 所属机构
     */
    @Column("`party_id`")
    @ApiModelProperty("所属机构")
    private Long partyId;

    /**
     * 昵称
     */
    @Column("`nickName`")
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 职位
     */
    @Column("`position`")
    @ApiModelProperty("职位")
    private String position;

    /**
     * 手机号
     */
    @Column("`phone`")
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 介绍信息
     */
    @Column("`remark`")
    @ApiModelProperty("介绍信息")
    private String remark;

    /**
     * 自动回复信息
     */
    @Column("`auto_replay`")
    @ApiModelProperty("自动回复信息")
    private String autoReplay;

    /**
     * 头像图片地址
     */
    @Column("`head_portrait`")
    @ApiModelProperty("头像图片地址")
    private String headPortrait;

    /**
     * 从业资格证书1
     */
    @Column("`certificate1_url`")
    @ApiModelProperty("从业资格证书1")
    private String certificate1Url;

    /**
     * 从业资格证书2
     */
    @Column("`certificate2_url`")
    @ApiModelProperty("从业资格证书2")
    private String certificate2Url;

    /**
     * 从业资格证书3
     */
    @Column("`certificate3_url`")
    @ApiModelProperty("从业资格证书3")
    private String certificate3Url;

    /**
     * 从业资格证书4
     */
    @Column("`certificate4_url`")
    @ApiModelProperty("从业资格证书4")
    private String certificate4Url;

    /**
     * 从业资格证书5
     */
    @Column("`certificate5_url`")
    @ApiModelProperty("从业资格证书5")
    private String certificate5Url;

    /**
     * 小程序二维码图片地址
     */
    @Column("`application_code_url`")
    @ApiModelProperty("小程序二维码图片地址")
    private String applicationCodeUrl;

    /**
     * 状态（0：未审核，1：审核通过，2：审核不通过）
     */
    @Column("`state`")
    @ApiModelProperty("状态（0：未审核，1：审核通过，-1：审核不通过，2：再次提交）")
    private Integer state;
}