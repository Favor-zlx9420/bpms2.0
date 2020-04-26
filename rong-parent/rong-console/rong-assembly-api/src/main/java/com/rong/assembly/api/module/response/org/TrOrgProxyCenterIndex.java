package com.rong.assembly.api.module.response.org;

import com.rong.common.util.DateUtil;
import com.vitily.mybatis.core.annotation.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Table("dual")
@Data
public class TrOrgProxyCenterIndex {
    @ApiModelProperty("机构id")
    private Long partyId;
    @ApiModelProperty("机构简称")
    private String partyShortName;
    @ApiModelProperty("机构全称")
    private String partyFullName;

    @ApiModelProperty("蓝vip到期日期")
    private Date vipEndDay;

    @ApiModelProperty("个人路演次数(次数)")
    private int userRoadshowCount;
    @ApiModelProperty("机构路演次数(次数)")
    private int orgRoadshowCount;
    @ApiModelProperty("个人总视频时长（秒）")
    private int userRoadshowDuration;
    @ApiModelProperty("个人视频播放量(次数)")
    private int userRoadshowPlayback;
    @ApiModelProperty("机构总视频时长（秒）")
    private int orgRoadshowDuration;
    @ApiModelProperty("机构视频播放量(次数)")
    private int orgRoadshowPlayback;


    @ApiModelProperty("蓝vip剩余时间（单位：天）")
    private Integer leftVipDays = 0;

    public Integer getLeftVipDays() {
        try {
            if (null != vipEndDay) {
                leftVipDays = DateUtil.difference(new Date(), vipEndDay);
            }
            return leftVipDays;
        }catch (Exception e){
            return null;
        }
    }
}
