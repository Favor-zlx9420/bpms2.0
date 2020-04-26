package com.rong.cms.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 内容类型
 * @author      : Administrator
 * @createDate  : 2020-01-03
 */
@Table("`tb_cms_type`")
@Data()
@Accessors(chain = true)
public class TbCmsType extends BaseEntity<TbCmsType> {
    /**
     * 状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 类别名称
     */
    @Column("`name`")
    private String name;

    /**
     * 描述
     */
    @Column("`description`")
    private String description;
}