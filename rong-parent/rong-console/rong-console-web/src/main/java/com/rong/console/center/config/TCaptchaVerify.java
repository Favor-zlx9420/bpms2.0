package com.rong.console.center.config;

import com.rong.common.util.HttpUtil;
import com.rong.common.util.JSONUtil;
import com.rong.common.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * creator : whh-lether
 * date    : 2019/8/21 11:46
 * desc    :
 **/
@Component
@Slf4j
public class TCaptchaVerify {
    @Value("${tq.chaVerify.app_id:2014115124}")
    private String APP_ID = "";
    @Value("${tq.chaVerify.app_secret:123456}")
    private String APP_SECRET = "";
    private final String VERIFY_URI = "https://ssl.captcha.qq.com/ticket/verify?aid=%s&AppSecretKey=%s&Ticket=%s&Randstr=%s&UserIP=%s";

    public int verifyTicket(String ticket, String rand, HttpServletRequest request) {
        String userIp = com.rong.console.center.util.HttpUtil.getIP(request);
        try {
            String res = HttpUtil.getInstance().okHttpGet(String.format(VERIFY_URI,
                    APP_ID,
                    APP_SECRET,
                    URLEncoder.encode(ticket, "UTF-8"),
                    URLEncoder.encode(rand, "UTF-8"),
                    URLEncoder.encode(userIp, "UTF-8")),null,null);
            log.info(res); // 临时输出
            HashMap map = JSONUtil.parseObject(res, HashMap.class);
            // 返回码
            int code = NumberUtil.toInteger(map.get("response"));
            // 恶意等级
            int evilLevel = NumberUtil.toInteger(map.get("evil_level"));

            // 验证成功
            if (code == 1) return evilLevel;
        } catch (java.io.IOException e) {
            // 忽略
        } finally {}

        return -1;
    }
}
