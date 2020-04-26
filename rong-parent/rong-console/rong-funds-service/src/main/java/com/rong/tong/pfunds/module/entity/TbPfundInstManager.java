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
@Table("`tong-rong`.`pfund_inst_manager`")
@Data()
@Accessors(chain = true)
public class TbPfundInstManager {
    /**
     * 
     */
    @Column("`ID`")
    private Long id;

    /**
     * 
     */
    @Column("`PARTY_ID`")
    private Long partyId;

    /**
     * 
     */
    @Column("`PERSON_ID`")
    private Long personId;

    /**
     * 
     */
    @Column("`NAME`")
    private String name;

    /**
     * 
     */
    @Column("`POSITION`")
    private String position;

    /**
     * 
     */
    @Column("`BEGIN_DATE`")
    private Date beginDate;

    /**
     * 
     */
    @Column("`END_DATE`")
    private Date endDate;

    /**
     * 
     */
    @Column("`UPDATE_TIME`")
    private Date updateTime;

    /**
     * 
     */
    @Column("`TMSTAMP`")
    private Long tmstamp;

    /**
     * 
     */
    @Column("`BACKGROUND`")
    private String background;

    /**
     * 是否基金经理
     */
    @Column("`IS_MANA`")
    private Boolean isMana;

    /**
     * 
     */
    @Column("`BACKGROUND_DESC`")
    private String backgroundDesc;
}