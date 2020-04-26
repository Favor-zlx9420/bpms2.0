package com.rong.common.util;

import com.rong.openaccount.module.entity.TbUserOpenAccount;
import com.rong.openaccount.module.request.TqUserOpenAccount;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

/**
 * 通联H5认证
 */
@Slf4j
public class AllinpayH5Auth {

    /**
     * 通联认证接口
     */
    public static final String url = "http://uat.zhhx.tonghuafund.com.cn:8996/zhhxWap/trustLogin";

    /**
     * 公钥key,用于敏感信息加密时使用
     */
    String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsIFu132SVxuc0IqCB8QaIhL7oH6W9K6O3E6+sNgsA67vFyICQqXjGdK561A479tjNqbwpNxO5OMmtuDfvDkXzJH3UQnP6zrpbbDd8z3ycguBnnbjdwdRFL3R44SGMoEKQoUMyYy4eQnYwOOLrsrT5Rlq8AOcPRnNEPYoxsrgRzwIDAQAB";

    /**
     * 调用通联完成认证
     * @param req
     * @param info
     */
    public static String invokeAllinpayH5Auth(String allinpayH5AuthUrl, String allinPayH5AuthPubKey, TqUserOpenAccount req, TbUserOpenAccount info) {
        // 报文字符串
        String timestamp = "";
        try {
            timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        long userId = req.getUserId();
        int flag = Integer.parseInt(req.getFlag());
        // 通联会员号
        String allinpayNo = info.getSignNum();
        // 客户姓名
        String name = info.getUserName();
        // 证件号码
        String idNo = info.getIdNum();
        try {
            name = AllinpaySecure.encryptByPublicKey(allinPayH5AuthPubKey, name);
            idNo = AllinpaySecure.encryptByPublicKey(allinPayH5AuthPubKey, idNo);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String msgBody = "<IdNo>"+idNo+"</IdNo><Name>"+name+"</Name><UserId>"+userId+"</UserId><Flag>"+flag+"</Flag><AllinpayNo>"+allinpayNo+"</AllinpayNo><Timestamp>" + timestamp + "</Timestamp>";
        String requestXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request>" + msgBody + "</Request>";

        log.info("输出请求通联的报文明文：{}", requestXml);
        // 请求报文加密
        String encryptStr = "";
        try {
            encryptStr = new String(Base64.getEncoder().encode(requestXml.getBytes("UTF-8")), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 请求报文密文签名
        String signStr = AllinpaySecure.signMsg(msgBody);

        // 请求通联完成认证
//        String html = HttpCommon.sendHttpReq(url, encryptStr, signStr);
        // 返回拼接的链接给H5
        String linkStr  = allinpayH5AuthUrl + "?requestXml=" + encryptStr + "&signMsg=" + signStr;

        log.info("输出调用通联的H5 url：{}", linkStr);
        return linkStr.replaceAll("\r\n|\r|\n", "");
    }
}
