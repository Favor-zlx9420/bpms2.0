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
@Table("`tong-rong`.`md_people`")
@Data()
@Accessors(chain = true)
public class TbMdPeople {
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
    @Column("`GENDER`")
    private String gender;

    /**
     * 
     */
    @Column("`BIRTHDAY`")
    private String birthday;

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
    @Column("`EDUCATION`")
    private Integer education;

    /**
     * 
     */
    @Column("`BACKGROUND_DESC`")
    private String backgroundDesc;
}