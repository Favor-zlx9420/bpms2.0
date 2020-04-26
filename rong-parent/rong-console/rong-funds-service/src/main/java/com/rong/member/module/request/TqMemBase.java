package com.rong.member.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.member.module.entity.TbMemBase;
import com.rong.member.module.entity.TbMemCompanyUserinfo;
import com.rong.member.module.entity.TbMemPersonalUserinfo;
import lombok.Data;

@Data
public class TqMemBase extends BaseRequest<TbMemBase,TqMemBase> {
    private TbMemPersonalUserinfo personalUserinfo;
    private TbMemCompanyUserinfo companyUserinfo;
}