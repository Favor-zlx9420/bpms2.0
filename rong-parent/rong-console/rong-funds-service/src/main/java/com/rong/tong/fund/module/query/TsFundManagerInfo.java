package com.rong.tong.fund.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.tong.fund.module.entity.TbFundManagerInfo;
import lombok.Data;

@Data
public class TsFundManagerInfo extends QueryInfo<TbFundManagerInfo> {

    public enum Fields {
        id,
        personId,
        name,
        gender,
        birthday,
        nationalityCd,
        certificate,
        photo,
        education,
        practiceDate,
        mfToPf,
        awards,
        updateTime,
        tmstamp,
        gradUniv,
        major,
        gradUniv2,
        major2,
        postGradUniv1,
        postGradMajor1,
        postGradUniv2,
        postGradMajor2,
        drGradUniv1,
        drGradMajor1,
        drGradUniv2,
        drGradMajor2,
        backgroundDesc;
    }
}