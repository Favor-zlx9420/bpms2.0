package com.rong.admin.module.query;

import com.rong.admin.module.entity.TbAdmUser;
import com.rong.common.module.QueryInfo;
import lombok.Data;

@Data
public class TsAdmUser extends QueryInfo<TbAdmUser> {

    public enum Fields {
        id,
        createDate,
        updateDate,
        deltag,
        state,
        userName,
        password,
        salt,
        phone,
        email,
        realName,
        idNumber,
        nickName,
        roleId,
        authToken,
        lastLoginDate,
        passwordExpiration,
        permissionStr;
    }
}