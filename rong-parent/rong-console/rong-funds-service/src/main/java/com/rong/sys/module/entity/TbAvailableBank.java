package com.rong.sys.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 可用银行
 * @author      : lether
 * @createDate  : 2020-02-03
 */
@Table("`tb_comm_available_bank`")
@Data()
@Accessors(chain = true)
public class TbAvailableBank extends BaseEntity<TbAvailableBank> {
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
     * 卡号类型
     */
    @Column("`type`")
    private Integer type;

    /**
     * 银行名称
     */
    @Column("`name`")
    private String name;

    /**
     * 银行简称
     */
    @Column("`short_name`")
    private String shortName;

    /**
     * 银行编码
     */
    @Column("`code`")
    private String code;

    /**
     * 备注
     */
    @Column("`memo`")
    private String memo;

    /**
     * 支持平台（以,分割）
     */
    @Column("`platform`")
    private String platform;

    /**
     * 图片地址
     */
    @Column("`pic_url`")
    private String picUrl;
}