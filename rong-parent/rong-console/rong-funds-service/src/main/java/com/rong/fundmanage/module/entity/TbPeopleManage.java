package com.rong.fundmanage.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 机构人员管理
 * @author      : lether
 * @createDate  : 2020-02-05
 */
@Table("`tb_people_manage`")
@Data()
@Accessors(chain = true)
public class TbPeopleManage extends BaseEntity<TbPeopleManage> {
    /**
     * 人员id
     */
    @Column("`person_id`")
    private Long personId;

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
     * 本地用户id
     */
    @Column("`user_id`")
    private Long userId;
}