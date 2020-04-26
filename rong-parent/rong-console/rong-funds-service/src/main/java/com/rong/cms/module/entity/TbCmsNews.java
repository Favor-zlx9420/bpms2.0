package com.rong.cms.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 新闻主表
 * @author      : Administrator
 * @createDate  : 2020-01-03
 */
@Table("`tb_cms_news`")
@Data()
@Accessors(chain = true)
public class TbCmsNews extends BaseEntity<TbCmsNews> {
    /**
     * 状态
     */
    @Column("`state`")
    @ApiModelProperty("状态")
    private Integer state;

    /**
     * 排序
     */
    @Column("`sort`")
    @ApiModelProperty("排序，越小越靠前")
    private BigDecimal sort;

    /**
     * 分类id
     */
    @Column("`cate_id`")
    @ApiModelProperty("分类id")
    private Long cateId;

    /**
     * 类型id
     */
    @Column("`type_id`")
    @ApiModelProperty("类型id")
    private Long typeId;

    /**
     * 标题
     */
    @Column("`title`")
    @ApiModelProperty("信息标题")
    private String title;

    /**
     * 副标题
     */
    @Column("`sub_title`")
    @ApiModelProperty("信息子标题")
    private String subTitle;

    /**
     * 简述
     */
    @Column("`description`")
    @ApiModelProperty("简述")
    private String description;

    /**
     * 作者
     */
    @Column("`author`")
    @ApiModelProperty("作者")
    private String author;

    /**
     * 来源
     */
    @Column("`come_from`")
    @ApiModelProperty("来源")
    private String comeFrom;

    /**
     * 是否可评论
     */
    @Column("`commentable`")
    @ApiModelProperty("是否可评论")
    private Boolean commentable;

    /**
     * 点击量
     */
    @Column("`hits`")
    @ApiModelProperty("点击量")
    private Integer hits;

    /**
     * 是否置顶
     */
    @Column("`top`")
    @ApiModelProperty("是否置顶")
    private Boolean top;

    /**
     * 页面标题
     */
    @Column("`page_title`")
    @ApiModelProperty("页面标题")
    private String pageTitle;

    /**
     * 关键字
     */
    @Column("`keyword`")
    @ApiModelProperty("页面关键字")
    private String keyword;

    /**
     * 页面描述
     */
    @Column("`page_description`")
    @ApiModelProperty("页面描述")
    private String pageDescription;

    /**
     * 是否已发布
     */
    @Column("`published`")
    @ApiModelProperty("发布状态")
    private Integer published;

    /**
     * 标签id组合
     */
    @Column("`label_ids`")
    @ApiModelProperty("标签id组合")
    private String labelIds;

    /**
     * 图片列表
     */
    @Column("`pic_list`")
    @ApiModelProperty("新闻额外图片组合")
    private String picList;

    /**
     * 文件列表
     */
    @Column("`file_list`")
    @ApiModelProperty("新闻关联文件组合")
    private String fileList;

    /**
     * 关联链接
     */
    @Column("`related_link`")
    @ApiModelProperty("关联连接")
    private String relatedLink;

    /**
     * 发布时间
     */
    @Column("`displaydate`")
    @ApiModelProperty("发布时间")
    private Date displaydate;

    /**
     * 内容
     */
    @Column("`content`")
    @ApiModelProperty("新闻内容")
    private String content;

    /**
     * 创始人
     */
    @Column("`create_by`")
    @ApiModelProperty("创始人")
    private Long createBy;
}