package com.rong.member.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.member.module.entity.TbMemCompanyCreditfile;
import com.rong.member.module.entity.TbMemCreditHistory;
import lombok.Data;

@Data
public class TqMemCompanyCreditfile extends BaseRequest<TbMemCompanyCreditfile,TqMemCompanyCreditfile> {
    private TbMemCreditHistory creditHistory;
    private String auditIp;
    private Integer auditResult;
}