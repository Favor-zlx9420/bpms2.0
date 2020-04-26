package com.rong.fundmanage.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 机构管理
 * @author      : lether
 * @createDate  : 2020-02-05
 */
@Table("`tb_org_manage`")
@Data()
@Accessors(chain = true)
public class TbOrgManage extends BaseEntity<TbOrgManage> {
    /**
     * 机构id
     */
    @Column("`party_id`")
    private Long partyId;

    /**
     * 是否可见
     */
    @Column("`visible`")
    private Boolean visible;

    /**
     * 是否推荐
     */
    @Column("`recommend`")
    private Boolean recommend;

    /**
     * 是否热门搜索
     */
    @Column("`hot_search`")
    private Boolean hotSearch;
    /**
     * 机构类型
     */
    @Column("`type`")
    @ApiModelProperty("0私募，1信托，2公募")
    private Integer type;
}