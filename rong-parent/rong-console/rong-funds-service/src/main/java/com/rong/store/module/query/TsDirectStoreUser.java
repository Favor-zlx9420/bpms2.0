package com.rong.store.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.store.module.entity.TbDirectStoreUser;
import lombok.Data;

@Data
public class TsDirectStoreUser extends QueryInfo<TbDirectStoreUser> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        type,
        userId,
        visible,
        recommend,
        sort,
        partyId,
        nickname,
        position,
        phone,
        remark,
        autoReplay,
        headPortrait,
        certificate1Url,
        certificate2Url,
        certificate3Url,
        certificate4Url,
        certificate5Url,
        applicationCodeUrl,
        state;
    }
}