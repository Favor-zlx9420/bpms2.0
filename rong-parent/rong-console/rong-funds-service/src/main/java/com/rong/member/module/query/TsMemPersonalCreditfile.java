package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbMemPersonalCreditfile;
import lombok.Data;

@Data
public class TsMemPersonalCreditfile extends QueryInfo<TbMemPersonalCreditfile> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        educationLevel,
        graduatedSchool,
        educationCertifyUrls,
        educationCertifyState,
        disciplineInfo,
        jobsYears,
        technicalTitles,
        jobsCertifyUrls,
        jobsCertifyState,
        jobsInfo,
        driverLicense,
        driverLicenseCertifyUrls,
        driverLicenseCertifyState,
        carInfo,
        carCertifyUrls,
        carCertifyState,
        estateInfo,
        estateCertifyUrls,
        estateCertifyState,
        stockFund,
        stockFundCertifyUrls,
        stockFundCertifyState,
        otherInfo,
        otherInfoCertifyUrls,
        otherInfoCertifyState,
        creditPoints;
    }
}