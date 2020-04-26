package com.rong.member.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.member.module.entity.TbMemBase;
import lombok.Data;

@Data
public class TsMemBase extends QueryInfo<TbMemBase> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        sort,
        userName,
        groupId,
        cate,
        nickName,
        email,
        regFrom,
        qq,
        description,
        headPortrait,
        salt,
        password,
        tokenKey,
        question,
        answer,
        openCreditfile,
        lastLoginDate,
        recommendMemberId,
        recommendCode,
        type,
        level,
        realName,
        idType,
        idNo,
        idVerifyStatus,
        position,
        phone,
        jgInfo,
        call,
        address,
        greet,
    }
}