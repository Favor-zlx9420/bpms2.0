package com.rong.usercard.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 名片自定义模块表
 * @author      : Administrator
 * @createDate  : 2020-03-09
 */
@Table("`tb_user_card_custom_model`")
@Data()
@Accessors(chain = true)
public class TbUserCardCustomModel extends BaseEntity<TbUserCardCustomModel> {
    /**
     * 用户ID
     */
    @Column("`user_id`")
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 模块标题
     */
    @Column("`model_title`")
    @ApiModelProperty("模块标题")
    private String modelTitle;

    /**
     * 模块内容
     */
    @Column("`model_content`")
    @ApiModelProperty("模块内容")
    private String modelContent;

    /**
     * 说明
     */
    @Column("`remark`")
    @ApiModelProperty("说明")
    private String remark;
}