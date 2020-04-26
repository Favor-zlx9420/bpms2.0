package com.rong.tong.pfunds.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 
 * @author      : ludexin
 * @createDate  : 2020-03-02
 */
@Table("`news_industry_new`")
@Data()
@Accessors(chain = true)
public class TbNewsIndustryNew extends BaseEntity<TbNewsIndustryNew> {
    /**
     * 
     */
    @Column("`NEWS_ID`")
    @ApiModelProperty("")
    private Long newsId;

    /**
     * 
     */
    @Column("`INDUSTRY_NAME_1ST`")
    @ApiModelProperty("")
    private String industryName1st;

    /**
     * 
     */
    @Column("`INDUSTRY_NAME_2ND`")
    @ApiModelProperty("")
    private String industryName2nd;

    /**
     * 
     */
    @Column("`INSERT_TIME`")
    @ApiModelProperty("")
    private Date insertTime;

    /**
     * 
     */
    @Column("`UPDATE_TIME`")
    @ApiModelProperty("")
    private Date updateTime;

    /**
     * 
     */
    @Column("`news_genre`")
    @ApiModelProperty("")
    private String newsGenre;

    /**
     * 
     */
    @Column("`TMSTAMP`")
    @ApiModelProperty("")
    private Long tmstamp;
}