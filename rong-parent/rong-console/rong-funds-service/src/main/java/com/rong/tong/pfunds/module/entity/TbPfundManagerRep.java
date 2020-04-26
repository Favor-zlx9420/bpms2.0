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
@Table("`tong-rong`.`pfund_manager_rep`")
@Data()
@Accessors(chain = true)
public class TbPfundManagerRep {
    /**
     * 信息编码
     */
    @Column("`ID`")
    private Long id;

    /**
     * 人员ID
     */
    @Column("`PERSON_ID`")
    private Long personId;

    /**
     * 截止日期
     */
    @Column("`END_DATE`")
    private Date endDate;

    /**
     * 代表产品
     */
    @Column("`SECURITY_ID`")
    private Long securityId;

    /**
     * 管理产品总数
     */
    @Column("`NUM_ALL`")
    private Integer numAll;

    /**
     * 存续产品总数
     */
    @Column("`NUM_DURA`")
    private Integer numDura;

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