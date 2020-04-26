package com.rong.sys.module.entity;

import com.rong.common.module.BaseEntity;
import com.vitily.mybatis.core.annotation.Column;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description : 图片表
 * @author      : lether
 * @createDate  : 2020-02-03
 */
@Table("`tb_comm_image_sams`")
@Data()
@Accessors(chain = true)
public class TbImageSams extends BaseEntity<TbImageSams> {
    /**
     * 标签id组合
     */
    @Column("`label_ids`")
    private String labelIds;

    /**
     * 图片地址(小)
     */
    @Column("`lmt_src`")
    @ApiModelProperty("小图地址")
    private String lmtSrc;

    /**
     * 图片地址(中)暂时不用
     */
    @Column("`cnt_src`")
    @ApiModelProperty("普通图地址，一般使用该地址较为清晰")
    private String cntSrc;

    /**
     * 图片地址(大)暂时不用
     */
    @Column("`big_src`")
    @ApiModelProperty("大图地址")
    private String bigSrc;

    /**
     * 图片名称
     */
    @Column("`name`")
    private String name;

    /**
     * 系统类型
     */
    @Column("`type`")
    private Integer type;

    /**
     * 描述
     */
    @Column("`description`")
    private String description;
}