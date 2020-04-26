package com.rong.member.module.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfBd2s;
import com.rong.common.util.serializer.NeedQualifiedSerializerOfNull;
import com.rong.common.util.serializer.Scale2BigDecimalSerializer;
import com.rong.member.module.entity.*;
import com.rong.user.module.entity.TbInvestorQualified;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TvMemBase extends TbMemBase {
    private String groupName;
    private TbUserAccount account;
    private TbMemPersonalUserinfo personalUserinfo;
    private TbMemCompanyUserinfo companyUserinfo;
    private TbMemPersonalCreditfile personalCreditfile;
    private TbMemCompanyCreditfile companyCreditfile;
    private TbInvestorQualified investorQualified;
    private Long labelId;
    private String labelName;
    private String recommendUser;
    private String recommendUserCode;

    private Date qualifiedDate;
    private String FinancialPreferences;
    private String qualifiedResult;


}