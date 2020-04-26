package com.rong.user.service.impl;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.user.mapper.UserEmploymentCertMapper;
import com.rong.user.module.entity.TbUserEmploymentCert;
import com.rong.user.module.request.TqUserEmploymentCert;
import com.rong.user.service.UserEmploymentCertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class UserEmploymentCertServiceImpl implements UserEmploymentCertService {

    @Resource
    private UserEmploymentCertMapper userEmploymentCertMapper;

    @Override
    public Result saveUserEmploymentCertInfo(TqUserEmploymentCert req) {
        TbUserEmploymentCert info = new TbUserEmploymentCert();
        int count = 0;
        try{
            BeanUtils.copyProperties(req, info);
            info.setCreateDate(new Date());
            info.setUpdateDate(new Date());
            count = userEmploymentCertMapper.insert(info);
        } catch (Exception e){
            e.printStackTrace();
            log.error("用户ID：{}，从业认证信息写入异常，异常原因{}", req.getUserId(), e.getMessage());
        }
        if(count > 0){
            log.info("用户ID：{}，从业认证信息写入成功!", req.getUserId());
            return Result.success();
        }
        log.warn("用户ID：{}，从业认证信息写入失败!", req.getUserId());
        return Result.miss(CommonEnumContainer.ResultStatus.USER_INFO_WRITE_FAILED, CommonEnumContainer.ResultStatus.USER_INFO_WRITE_FAILED.getDesc());
    }
}