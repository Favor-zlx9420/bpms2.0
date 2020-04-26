package com.rong.admin.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description : 备忘录
 * @author      : Administrator
 * @createDate  : 2020-01-03
 */
@Table("`tb_adm_memorandum`")
@Data()
@Accessors(chain = true)
public class TbAdmMemorandum extends BaseEntity<TbAdmMemorandum> {
    /**
     * 状态
     */
    @Column("`state`")
    private Integer state;

    /**
     * 用户id
     */
    @Column("`adm_user_id`")
    private Long admUserId;

    /**
     * 备忘录标题
     */
    @Column("`title`")
    private String title;

    /**
     * 内容
     */
    @Column("`content`")
    private String content;

    /**
     * 提醒时间
     */
    @Column("`remind_time`")
    private Date remindTime;

    /**
     * 选择的标签
     */
    @Column("`label_ids`")
    private String labelIds;
}