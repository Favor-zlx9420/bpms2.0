package com.rong.roadshow.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 路演热门嘉宾
 * @author      : lether
 * @createDate  : 2020-02-17
 */
@Table("`tb_roadshow_hot_user`")
@Data()
@Accessors(chain = true)
public class TbRoadShowHotUser extends BaseEntity<TbRoadShowHotUser> {
    /**
     * 排序
     */
    @Column("`sort`")
    @ApiModelProperty("排序")
    private BigDecimal sort;

    /**
     * （是否可见：0不可见，1可见）
     */
    @Column("`visible`")
    @ApiModelProperty("（是否可见：0不可见，1可见）")
    private Boolean visible;

    /**
     * 嘉宾名字
     */
    @Column("`presenter`")
    @ApiModelProperty("嘉宾名字")
    private String presenter;

    /**
     * 用户id
     */
    @Column("`user_id`")
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 描述
     */
    @Column("`description`")
    @ApiModelProperty("描述")
    private String description;
}