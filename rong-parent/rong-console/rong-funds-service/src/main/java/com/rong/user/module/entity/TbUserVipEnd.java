package com.rong.user.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : vip有效期
 * @author      : lether
 * @createDate  : 2020-02-14
 */
@Table("`tb_user_vip_end`")
@Data()
@Accessors(chain = true)
public class TbUserVipEnd extends BaseEntity<TbUserVipEnd> {
    /**
     * 用户id
     */
    @Column("`user_id`")
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 等级
     */
    @Column("`level`")
    @ApiModelProperty("等级")
    private Integer level;

    /**
     * 生效时间
     */
    @Column("`begin_date`")
    @ApiModelProperty("生效时间")
    private Date beginDate;

    /**
     * 截止时间
     */
    @Column("`end_date`")
    @ApiModelProperty("截止时间")
    private Date endDate;
}