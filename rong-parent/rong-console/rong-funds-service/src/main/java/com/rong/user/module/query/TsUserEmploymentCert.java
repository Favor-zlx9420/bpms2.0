package com.rong.user.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.user.module.entity.TbUserEmploymentCert;
import lombok.Data;

@Data
public class TsUserEmploymentCert extends QueryInfo<TbUserEmploymentCert> {

    public enum Fields {
        id,
        userId,
        userName,
        sex,
        orgType,
        orgName,
        positionName,
        employmentYearNum,
        personalCardImg,
        qualificationCertImg,
        authStatus,
        authMsg,
        createDate,
        updateDate;
    }
}