package com.rong.cms.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @description : 第三方新闻管理
 * @author      : Administrator
 * @createDate  : 2020-03-11
 */
@Table("`tb_third_news_manage`")
@Data()
@Accessors(chain = true)
public class TbThirdNewsManage extends BaseEntity<TbThirdNewsManage> {
    /**
     * 新闻id
     */
    @Column("`news_id`")
    @ApiModelProperty("新闻id")
    private Long newsId;

    /**
     * 是否可见
     */
    @Column("`visible`")
    @ApiModelProperty("是否可见")
    private Boolean visible;

    /**
     * 是否推荐
     */
    @Column("`recommend`")
    @ApiModelProperty("是否推荐")
    private Boolean recommend;

    /**
     * 是否热门搜索
     */
    @Column("`hot_search`")
    @ApiModelProperty("是否热门搜索")
    private Boolean hotSearch;

    /**
     * 排序，越小越在前面
     */
    @Column("`sort`")
    @ApiModelProperty("排序，越小越在前面")
    private BigDecimal sort;
}