package com.rong.openaccount.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.common.module.TqBase;
import com.rong.openaccount.module.entity.TbUserOpenAccount;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.PrimaryKey;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TqUserOpenAccount extends TqBase {

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String userName;

    /**
     * 用户手机号码
     */
    @ApiModelProperty("用户手机号码")
    private String phoneNum;

    /**
     * 用户性别(1：男，2：女)
     */
    @ApiModelProperty("用户性别(1：男，2：女)")
    private Short sex;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String idNum;

    /**
     * 银行卡号
     */
    @ApiModelProperty("银行卡号")
    private String cardNum;

    /**
     * 所属银行
     */
    @ApiModelProperty("所属银行")
    private String cardOrg;

    /**
     * 通联会员号：开户成功时，由通联机构返回
     */
    @ApiModelProperty("通联会员号：开户成功时，由通联机构返回")
    private String signNum;

    /**
     * 开户时间
     */
    @ApiModelProperty("开户时间")
    private Date openAccountDate;

    /**
     * 用户开户时，上送验证码属性
     */
    @ApiModelProperty("验证码")
    private String verCode;

    /**
     * [0] 基金超市
     * [1] 资金宝首页
     * [2] 首页
     * [3] 基金/组合详情（需要传FundCode）
     * [4] 稳健理财
     * [5] 高端理财
     * [6] 基金搜索（仅H5）
     * [7] 我的自选（仅H5）
     * [8] 我的资产
     * [9] 交易查询
     * [10] 分红查询
     * [11] 个人中心
     * [12] 基金资产
     * [13] 宝类资产
     * [14] 高端资产
     * [15] 定投专区
     * [16] 我的定投计划
     * [17] 基金精选
     * [18] 风险评测
     * [19] 基金/组合买入订单页（需要传FundCode）
     * [20] 组合资产
     * [21] 指定页面（需要传URL）
     */
    @ApiModelProperty("标记")
    private String flag;

    /**
     * 基金/组合代码，当Flag=3或19时必填
     */
    @ApiModelProperty("基金/组合代码")
    private String fundCode;

    /**
     * 证件类型（默认为00：身份证03-统一社会信用代码，04-营业执照）
     */
    @ApiModelProperty("证件类型")
    private String idType = "00";
}