package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserVip1Apply;
import lombok.Data;

@Data
public class TsUserVip1Apply extends QueryInfo<TbUserVip1Apply> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        auditResult,
        appUserId,
        auditUserId,
        remark,
        certificate1Url,
        certificate2Url,
        certificate3Url,
        certificate4Url,
        certificate5Url,
        certificate6Url,
        certificate7Url,
        certificate8Url,
        certificate9Url,
        certificate10Url;
    }
}