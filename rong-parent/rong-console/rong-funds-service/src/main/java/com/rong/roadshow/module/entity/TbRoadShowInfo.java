package com.rong.roadshow.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 路演信息
 * @author      : lether
 * @createDate  : 2020-02-05
 */
@Table("`tb_road_show_info`")
@Data()
@Accessors(chain = true)
public class TbRoadShowInfo extends BaseEntity<TbRoadShowInfo> {
    /**
     * 使用状态（0：待审核，-1：审核失败，1：审核通过）
     */
    @Column("`state`")
    private Integer state;
    /**
     * 是否置顶
     */
    @Column("`top`")
    private Boolean top;
    /**
     * 是否热门
     */
    @Column("`hot`")
    private Boolean hot;

    /**
     * 排序
     */
    @Column("`sort`")
    private BigDecimal sort;

    /**
     * 路演所属机构id
     */
    @Column("`org_id`")
    @ApiModelProperty(value = "路演所属机构id")
    private Long orgId;

    /**
     * 路演类型（0私募，1信托，2公募）
     */
    @Column("`type`")
    @ApiModelProperty(value = "0私募，1信托，2公募")
    private Integer type;

    /**
     * 路演时间
     */
    @Column("`show_date`")
    @ApiModelProperty(value = "路演时间")
    private Date showDate;

    /**
     * 路演结束时间
     */
    @Column("`end_date`")
    @ApiModelProperty(value = "路演结束时间")
    private Date endDate;

    /**
     * 查看人数
     */
    @Column("`view_users`")
    @ApiModelProperty(value = "查看/预约人数")
    private Integer viewUsers;

    /**
     * 分类id
     */
    @Column("`cate_id`")
    @ApiModelProperty(value = "分类id")
    private Long cateId;

    /**
     * 标签id
     */
    @Column("`label_id`")
    @ApiModelProperty(value = "标签id")
    private Long labelId;
    /**
     * 标签id0
     */
    @Column("`label_id0`")
    @ApiModelProperty(value = "标签id0")
    private Long labelId0;

    /**
     * 主讲人
     */
    @Column("`presenter`")
    @ApiModelProperty(value = "主讲人")
    private String presenter;

    /**
     * 标题
     */
    @Column("`title`")
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 路演详情
     */
    @Column("`detail`")
    @ApiModelProperty(value = "路演详情")
    private String detail;

    /**
     * 封面图片地址
     */
    @Column("`cover_image_url`")
    @ApiModelProperty(value = "封面图片地址")
    private String coverImageUrl;

    /**
     * 路演文档地址
     */
    @Column("`doc_url`")
    @ApiModelProperty(value = "路演文档地址")
    private String docUrl;

    /**
     * 路演文档地址2
     */
    @Column("`doc2_url`")
    @ApiModelProperty(value = "路演文档地址2")
    private String doc2Url;

    /**
     * 路演视频地址
     */
    @Column("`video_url`")
    @ApiModelProperty(value = "路演视频地址")
    private String videoUrl;

    /**
     * 上传路演用户id
     */
    @Column("`upload_user_id`")
    @ApiModelProperty(value = "上传路演用户id")
    private Long uploadUserId;

    /**
     * 审核路演用户id
     */
    @Column("`audit_user_id`")
    @ApiModelProperty(value = "审核路演用户id")
    private Long auditUserId;
    /**
     * 主讲人介绍
     */
    @Column("`presenter_introduce`")
    @ApiModelProperty(value = "主讲人介绍")
    private String presenterIntroduce;
    /**
     * 路演时长（单位：秒）
     */
    @Column("`show_duration`")
    @ApiModelProperty(value = "路演时长（单位：秒）")
    private Long showDuration;

    @Column("`adm_user_id`")
    @ApiModelProperty(value = "创建用户（后台）")
    private Long admUserId;

    @Column("`from`")
    @ApiModelProperty(value = "来源：0前台；1：后台")
    private Integer from;

}