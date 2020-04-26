package com.rong.tong.pfunds.module.entity;

import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : ludexin
 * @createDate  : 2020-02-03
 */
@Table("`tong-rong`.`pfund_inst_scale_amac`")
@Data()
@Accessors(chain = true)
public class TbPfundInstScaleAmac {
    /**
     * 信息编码
     */
    @Column("`ID`")
    private Long id;

    /**
     * 机构内部编码
     */
    @Column("`PARTY_ID`")
    private Long partyId;

    /**
     * 机构名称
     */
    @Column("`PARTY_FULL_NAME`")
    private String partyFullName;

    /**
     * 管理基金主要类别
     */
    @Column("`MAIN_FUND_TYPE`")
    private String mainFundType;

    /**
     * 管理规模
     */
    @Column("`SCALE`")
    private String scale;

    /**
     * 源数据id
     */
    @Column("`INST_ID`")
    private String instId;

    /**
     * 变更次数
     */
    @Column("`NUM`")
    private Integer num;

    /**
     * 是否最新
     */
    @Column("`IS_NEW`")
    private Integer isNew;

    /**
     * 变动时间
     */
    @Column("`FETCH_DATE`")
    private Date fetchDate;

    /**
     * 更新时间
     */
    @Column("`UPDATE_TIME`")
    private Date updateTime;

    /**
     * 时间戳
     */
    @Column("`TMSTAMP`")
    private Long tmstamp;
}