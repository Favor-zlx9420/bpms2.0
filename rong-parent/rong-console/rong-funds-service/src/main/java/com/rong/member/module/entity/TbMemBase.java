package com.rong.member.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 会员主表
 * @author      : lether
 * @createDate  : 2020-02-04
 */
@Table("`tb_mem_base`")
@Data()
@Accessors(chain = true)
public class TbMemBase extends BaseEntity<TbMemBase> {
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
     * 会员登录名
     */
    @Column("`user_name`")
    private String userName;

    /**
     * 分组id
     */
    @Column("`group_id`")
    private Long groupId;

    /**
     * 0个人用户1企业用户
     */
    @Column("`cate`")
    private Integer cate;

    /**
     * 昵称
     */
    @Column("`nick_name`")
    private String nickName;

    /**
     * email
     */
    @Column("`email`")
    private String email;

    /**
     * 来源
     */
    @Column("`reg_from`")
    private Integer regFrom;

    /**
     * qq号
     */
    @Column("`qq`")
    private String qq;

    /**
     * 描述
     */
    @Column("`description`")
    private String description;

    /**
     * 用户头像
     */
    @Column("`head_portrait`")
    @ApiModelProperty("`用户头像`")
    private String headPortrait;

    /**
     * salt
     */
    @Column("`salt`")
    private String salt;

    /**
     * 密码
     */
    @Column("`password`")
    private String password;

    /**
     * tokenkey
     */
    @Column("`token_key`")
    private String tokenKey;

    /**
     * 问题
     */
    @Column("`question`")
    private String question;

    /**
     * 问题答案
     */
    @Column("`answer`")
    private String answer;

    /**
     * 是否已开通信用信息：0未开通1已开通
     */
    @Column("`open_creditfile`")
    private Boolean openCreditfile;

    /**
     * 最后一次登录时间
     */
    @Column("`last_login_date`")
    private Date lastLoginDate;

    /**
     * 推荐人id
     */
    @Column("`recommend_member_id`")
    private Long recommendMemberId;

    /**
     * 我的推荐码
     */
    @Column("`recommend_code`")
    private String recommendCode;

    /**
     * （0，直营店客服；1，基金经理；2，机构代理，3，普通用户，4，直营店用户）
     */
    @Column("`type`")
    @ApiModelProperty("用户类别（0，直营店客服；1，基金经理；2，机构代理，3，普通用户，4，直营店用户）")
    private Integer type;

    /**
     * （0，无；1，金色vip；2，蓝色vip）
     */
    @Column("`level`")
    @ApiModelProperty("用户等级（0，无；1，金色vip；2，蓝色vip）")
    private Integer level;

    /**
     * 用户真实姓名
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
     * 证件验证类型
     */
    @Column("`id_verify_status`")
    private Integer idVerifyStatus;

    /**
     * 职位
     */
    @Column("`position`")
    @ApiModelProperty("职位")
    private String position;

    /**
     * 手机号码
     */
    @Column("`phone`")
    private String phone;

    /**
     * 座机
     */
    @Column("`call`")
    private String call;
    /**
     * 极光信息
     */
    @Column("`jg_info`")
    private String jgInfo;

    @ApiModelProperty(value = "通讯地址")
    @Column("`address`")
    private String address;

    @ApiModelProperty(value = "打招呼信息")
    @Column("`greet`")
    private String greet;
}