package com.rong.user.service;

import com.rong.common.module.Result;
import com.rong.user.module.request.TqUserEmploymentCert;

/**
 * 用户金融从业认证服务接口
 */
public interface UserEmploymentCertService {

    /**
     * 保存用户从业认证信息
     */
    Result saveUserEmploymentCertInfo(TqUserEmploymentCert req);
}