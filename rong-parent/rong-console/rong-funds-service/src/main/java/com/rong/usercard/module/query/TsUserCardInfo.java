package com.rong.usercard.module.query;

import com.rong.common.module.QueryInfo;
import com.rong.usercard.module.entity.TbUserCardInfo;
import lombok.Data;

@Data
public class TsUserCardInfo extends QueryInfo<TbUserCardInfo> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        userId,
        visible,
        hotSearch,
        sort,
        recommend,
        headPortrait,
        firstName,
        lastName,
        phone,
        email,
        wxNo,
        company,
        position,
        call,
        fax,
        address,
        hometown,
        personalIntroduction,
        companyIntroduction,
        teamIntroduction,
        glory,
        style,
        background,
        swapVerify,
        contactPublicVisible;
    }
}