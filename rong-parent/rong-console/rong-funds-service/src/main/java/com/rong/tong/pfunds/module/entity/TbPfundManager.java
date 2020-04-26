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
@Table("`tong-rong`.`pfund_manager`")
@Data()
@Accessors(chain = true)
public class TbPfundManager {
    /**
     * 
     */
    @Column("`ID`")
    private Long id;

    /**
     * 
     */
    @Column("`PERSON_ID`")
    private Long personId;

    /**
     * 
     */
    @Column("`SECURITY_ID`")
    private Long securityId;

    /**
     * 
     */
    @Column("`PUBLISH_DATE`")
    private Date publishDate;

    /**
     * 
     */
    @Column("`POSITION`")
    private String position;

    /**
     * 
     */
    @Column("`IS_INCUMBENT`")
    private Boolean isIncumbent;

    /**
     * 
     */
    @Column("`ACCESSION_DATE`")
    private Date accessionDate;

    /**
     * 
     */
    @Column("`DIMISSION_DATE`")
    private Date dimissionDate;

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
     * 公司ID
     */
    @Column("`PARTY_ID`")
    private Long partyId;
}