package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbMemCompanyCreditfile;
import lombok.Data;

@Data
public class TsMemCompanyCreditfile extends QueryInfo<TbMemCompanyCreditfile> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        businessLicense,
        businessLicenseUrls,
        businessLicenseCertifyState,
        registeredCapital,
        registeredCapitalUrls,
        registeredCapitalCertifyState,
        carInfo,
        carCertifyUrls,
        carCertifyState,
        estateInfo,
        estateCertifyUrls,
        estateCertifyState,
        stockFund,
        stockFundCertifyUrls,
        stockFundCertifyState,
        companyAssets,
        companyAssetsUrls,
        companyAssetsCertifyState,
        otherInfo,
        otherInfoCertifyUrls,
        otherInfoCertifyState,
        creditPoints;
    }
}