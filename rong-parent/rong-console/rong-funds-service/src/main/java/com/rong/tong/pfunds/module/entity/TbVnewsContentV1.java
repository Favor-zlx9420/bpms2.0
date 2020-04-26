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
@Table("`tong-rong`.`vnews_content_v1`")
@Data()
@Accessors(chain = true)
public class TbVnewsContentV1 extends BaseEntity<TbVnewsContentV1> {
    /**
     * 
     */
    @Column("`news_id`")
    @ApiModelProperty("")
    private Long newsId;

    /**
     * 
     */
    @Column("`NEWS_ORIGIN_SOURCE`")
    @ApiModelProperty("")
    private String newsOriginSource;

    /**
     * 
     */
    @Column("`news_author`")
    @ApiModelProperty("")
    private String newsAuthor;

    /**
     * 
     */
    @Column("`news_url`")
    @ApiModelProperty("")
    private String newsUrl;

    /**
     * 
     */
    @Column("`news_title`")
    @ApiModelProperty("")
    private String newsTitle;

    /**
     * 
     */
    @Column("`NEWS_PUBLISH_SITE`")
    @ApiModelProperty("")
    private String newsPublishSite;

    /**
     * 
     */
    @Column("`news_publish_time`")
    @ApiModelProperty("")
    private Date newsPublishTime;

    /**
     * 
     */
    @Column("`effective_time`")
    @ApiModelProperty("")
    private Date effectiveTime;

    /**
     * 
     */
    @Column("`update_time`")
    @ApiModelProperty("")
    private Date updateTime;

    /**
     * 
     */
    @Column("`TMSTAMP`")
    @ApiModelProperty("")
    private Long tmstamp;
}