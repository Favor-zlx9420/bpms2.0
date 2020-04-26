package com.rong.sys.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 广告
 * @author      : lether
 * @createDate  : 2020-02-03
 */
@Table("`tb_comm_advertise`")
@Data()
@Accessors(chain = true)
public class TbAdvertise extends BaseEntity<TbAdvertise> {
    /**
     * 状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 排序，越小越在前面
     */
    @Column("`sort`")
    @ApiModelProperty("排序，越小越在前面")
    private BigDecimal sort;

    /**
     * 广告类型
     */
    @Column("`type`")
    @ApiModelProperty("广告类型,1:信息，2：产品")
    private Integer type;

    /**
     * 文件url
     */
    @Column("`file_url`")
    @ApiModelProperty("文件url")
    private String fileUrl;

    /**
     * 缩略图url
     */
    @Column("`img_sam_url`")
    @ApiModelProperty("图片url")
    private String imgSamUrl;

    /**
     * 标题
     */
    @Column("`title`")
    @ApiModelProperty("广告标题")
    private String title;

    /**
     * 广告内容
     */
    @Column("`content`")
    @ApiModelProperty("广告内容")
    private String content;

    /**
     * 关联链接
     */
    @Column("`link`")
    @ApiModelProperty("关联链接")
    private String link;
    /**
     * 广告位置（0：广告位值0，1：广告位置1......）
     */
    @Column("`position`")
    @ApiModelProperty("广告位置（0：广告位值0，1：广告位置1......）")
    private Integer position;
}