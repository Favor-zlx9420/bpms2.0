package com.rong.member.module.request;

import com.rong.common.module.BaseRequest;
import com.rong.member.module.entity.TbUserAccount;
import lombok.Data;

import java.math.BigDecimal;

@Data()
public class TqUserAccount extends BaseRequest<TbUserAccount,TqUserAccount> {
    private Integer freezeOperaType;
    private Integer availableOperaType;
    private BigDecimal operaAmount;
}