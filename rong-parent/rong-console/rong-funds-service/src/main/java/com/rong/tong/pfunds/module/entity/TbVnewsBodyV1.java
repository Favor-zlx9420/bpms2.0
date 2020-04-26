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
@Table("`vnews_body_v1`")
@Data()
@Accessors(chain = true)
public class TbVnewsBodyV1 extends BaseEntity<TbVnewsBodyV1> {
    /**
     * 
     */
    @Column("`NEWS_ID`")
    @ApiModelProperty("")
    private Long newsId;

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
    @Column("`TMSTAMP`")
    @ApiModelProperty("")
    private Long tmstamp;

    /**
     * 
     */
    @Column("`NEWS_BODY`")
    @ApiModelProperty("")
    private String newsBody;
}