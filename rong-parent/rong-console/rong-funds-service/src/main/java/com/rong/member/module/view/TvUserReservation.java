package com.rong.member.module.view;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.util.CommonUtil;
import com.rong.member.module.entity.TbUserReservation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TvUserReservation extends TbUserReservation {
    @ApiModelProperty("预约信息：（预约类型type=（0：机构名；1：基金经理名字；2：产品名字））")
    private String info;
    @ApiModelProperty(hidden = true)
    private String reservationManageInfo;
    @ApiModelProperty(hidden = true)
    private String reservationOrgInfo;
    @ApiModelProperty(hidden = true)
    private String reservationProInfo;
    @ApiModelProperty(hidden = true)
    private String roadshowInfo;
    @ApiModelProperty("用户类型type=（0：普通用户；1：合格投资者；2：金融从业者）")
    private Integer userType;
    @ApiModelProperty("用户类型type=（0：普通用户；1：合格投资者；2：金融从业者）")
    private String userTypeStr;
    @ApiModelProperty("处理人")
    private String dualUserName;


    public String getInfo() {
        if(CommonUtil.isEqual(this.getType(), CommonEnumContainer.ReservationType.ORGANIZATION.getValue())){
            return reservationOrgInfo;
        }else if(CommonUtil.isEqual(this.getType(), CommonEnumContainer.ReservationType.FUND_MANAGER.getValue())){
            return reservationManageInfo;
        }else if(CommonUtil.isEqual(this.getType(), CommonEnumContainer.ReservationType.PRODUCT.getValue())){
            return reservationProInfo;
        }else if(CommonUtil.isEqual(this.getType(), CommonEnumContainer.ReservationType.ROAD_SHOW.getValue())){
            return roadshowInfo;
        }
        return info;
    }

    public String getUserTypeStr() {
        return CommonEnumContainer.ReservationUserType.getDesc(this.getUserType());
    }
}