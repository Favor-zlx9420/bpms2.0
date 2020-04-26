package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserVip2Apply;
import lombok.Data;

@Data
public class TsUserVip2Apply extends QueryInfo<TbUserVip2Apply> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        auditResult,
        appUserId,
        auditUserId,
        partyFullName,
        departName,
        position,
        remark,
        bizCardUrl1,
        bizCardUrl2,
        holdingPhotoUrl1,
        holdingPhotoUrl2,
        transferInfoUrl1,
        transferInfoUrl2,
        transferInfoUrl3,
        transferInfoUrl4;
    }
}