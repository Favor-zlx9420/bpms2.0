package com.rong.admin.service;

import com.rong.admin.module.entity.TbAdmUser;
import com.rong.admin.module.request.TqAdmUser;
import com.rong.admin.module.view.TvAdmUser;
import com.rong.common.service.BasicService;

import java.util.Set;

public interface AdmUserService extends BasicService<TbAdmUser, TqAdmUser, TvAdmUser> {
    void updateByPassword(TbAdmUser admin, String newPassword, int durationOfDay);
    String encryPassword(TbAdmUser entity, String customerPassword);
    String encryToken(TbAdmUser entity);
    void checkAdmin(TvAdmUser admin);
    Set<Long> getTotalPermissions(TvAdmUser admin);
}