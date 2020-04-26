package com.rong.sys.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : banner
 * @author      : lether
 * @createDate  : 2020-02-03
 */
@Table("`tb_comm_banner`")
@Data()
@Accessors(chain = true)
public class TbBanner extends BaseEntity<TbBanner> {
    /**
     * 状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 排序
     */
    @Column("`sort`")
    private BigDecimal sort;

    /**
     * 页面类型
     */
    @Column("`page_type`")
    private Integer pageType;

    /**
     * 标题
     */
    @Column("`title`")
    private String title;

    /**
     * 描述
     */
    @Column("`description`")
    private String description;

    /**
     * 图片地址
     */
    @Column("`pic_url`")
    private String picUrl;

    /**
     * 图片跳转连接
     */
    @Column("`link`")
    private String link;

    /**
     * 创建人
     */
    @Column("`create_by`")
    private Long createBy;
}