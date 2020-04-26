package com.rong.assembly.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.rong.assembly.api.service.SmsHelper;
import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.module.Result;
import com.rong.common.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AliSmsHelperImpl implements SmsHelper {

    @Value("${ali.sms.accessKeyId}")
    private String accessKeyId;
    @Value("${ali.sms.accessKeySecret}")
    private String accessKeySecret;
    @Value("${ali.sms.domain}")
    private String domain;
    @Value("${ali.sms.version}")
    private String version;
    @Value("${ali.sms.action}")
    private String action;
    @Value("${ali.sms.regionId}")
    private String regionId;
    @Value("${ali.sms.signName:R总管}")
    private String signName;
    @Value("${ali.sms.registerTemplateCode}")
    private String registerTemplateCode;
    @Value("${ali.sms.findPwdTemplateCode}")
    private String findPwdTemplateCode;
    private final IAcsClient client;

    @Autowired
    public AliSmsHelperImpl(
            @Value("${ali.sms.regionId}")String regionId,
            @Value("${ali.sms.accessKeyId}")String accessKeyId,
            @Value("${ali.sms.accessKeySecret}")String accessKeySecret
    ) {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(profile);
    }

    private String getTemplateCode(CommonEnumContainer.TripartiteMessageContentType contentType){
        switch (contentType){
            case REGISTER:
                return registerTemplateCode;
            case RETRIEVE_PASSWORD:
                return findPwdTemplateCode;
            default:
                break;
        }
        return registerTemplateCode;
    }
    public Result send(CommonEnumContainer.TripartiteMessageContentType contentType, String phone, String ...contents){
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction(action);
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "R总管");
        request.putQueryParameter("TemplateCode", getTemplateCode(contentType));
        request.putQueryParameter("TemplateParam", "{code:\""+contents[0]+"\"}");
        String errMessage;
        try {
            CommonResponse response = client.getCommonResponse(request);

            JSONObject object = JSON.parseObject(response.getData());
            if(CommonUtil.isEqual(object.getString("Code"),"OK")){
                return Result.success();
            }
            log.warn(response.getData());
            errMessage = object.getString("Message");
        } catch (ClientException e) {
            e.printStackTrace();
            errMessage = e.getMessage();
        }
        return Result.miss(CommonEnumContainer.ResultStatus.ABNORMAL_OPERATION,errMessage);
    }
}
