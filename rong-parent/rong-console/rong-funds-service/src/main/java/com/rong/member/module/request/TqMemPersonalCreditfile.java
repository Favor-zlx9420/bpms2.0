package com.rong.member.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.member.module.entity.TbMemCreditHistory;
import com.rong.member.module.entity.TbMemPersonalCreditfile;
import lombok.Data;

@Data
public class TqMemPersonalCreditfile extends BaseRequest<TbMemPersonalCreditfile,TqMemPersonalCreditfile> {
    private TbMemCreditHistory creditHistory;
    private String auditIp;
    private Integer auditResult;
}