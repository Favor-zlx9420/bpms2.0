package com.rong.openaccount.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.openaccount.module.entity.TbUserOpenAccount;
import lombok.Data;

@Data
public class TsUserOpenAccount extends QueryInfo<TbUserOpenAccount> {

    public enum Fields {
        id,
        userId,
        userName,
        phoneNum,
        sex,
        idNum,
        cardNum,
        cardOrg,
        signNum,
        reqTraceNum,
        openAccountDate,
        openAccountStatus,
        createDate,
        updateDate;
    }
}