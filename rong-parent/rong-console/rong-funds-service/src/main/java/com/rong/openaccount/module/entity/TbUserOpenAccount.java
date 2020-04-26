package com.rong.openaccount.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.PrimaryKey;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 用户开户信息表
 * @author      : admin
 * @createDate  : 2020-04-18
 */
@Table("`tb_user_open_account`")
@Data()
@Accessors(chain = true)
public class TbUserOpenAccount {
    /**
     * 主键ID
     */
    @Column("`id`")
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 用户ID
     */
    @PrimaryKey()
    @Column("`user_id`")
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 用户姓名
     */
    @Column("`user_name`")
    @ApiModelProperty("用户姓名")
    private String userName;

    /**
     * 用户手机号码
     */
    @Column("`phone_num`")
    @ApiModelProperty("用户手机号码")
    private String phoneNum;

    /**
     * 用户性别(1：男，2：女)
     */
    @Column("`sex`")
    @ApiModelProperty("用户性别(1：男，2：女)")
    private Short sex;

    /**
     * 身份证号
     */
    @Column("`id_num`")
    @ApiModelProperty("身份证号")
    private String idNum;

    /**
     * 银行卡号
     */
    @Column("`card_num`")
    @ApiModelProperty("银行卡号")
    private String cardNum;

    /**
     * 所属银行
     */
    @Column("`card_org`")
    @ApiModelProperty("所属银行")
    private String cardOrg;

    /**
     * 通联会员号：开户成功时，由通联机构返回
     */
    @Column("`sign_num`")
    @ApiModelProperty("通联会员号：开户成功时，由通联机构返回")
    private String signNum;

    /**
     * 开户请求交易流水号
     */
    @Column("`req_trace_num`")
    @ApiModelProperty("开户请求交易流水号")
    private String reqTraceNum;

    /**
     * 开户时间
     */
    @Column("`open_account_date`")
    @ApiModelProperty("开户时间")
    private Date openAccountDate;

    /**
     * 开户状态(0：成功，1：失败)
     */
    @Column("`open_account_status`")
    @ApiModelProperty("开户状态(0：成功，1：失败)")
    private int openAccountStatus;

    /**
     * 创建时间
     */
    @Column("`create_date`")
    @ApiModelProperty("创建时间")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column("`update_date`")
    @ApiModelProperty("更新时间")
    private Date updateDate;
}