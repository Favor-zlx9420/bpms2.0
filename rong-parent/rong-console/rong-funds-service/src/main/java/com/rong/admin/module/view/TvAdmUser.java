package com.rong.admin.module.view;

import com.rong.admin.module.entity.TbAdmUser;
import lombok.Data;

@Data
public class TvAdmUser extends TbAdmUser {
    private String rolePermissionStr;
    private String roleName;
    private String newPassword;
}