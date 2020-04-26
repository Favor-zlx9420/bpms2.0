package com.rong.common.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Http 请求
 */
public class HttpCommon {

    public static String sendHttpReq(String url, String msg) {
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        paramList.add(new BasicNameValuePair("msgPlain", msg));

        // 创建http客户端
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(new UrlEncodedFormEntity(paramList, Consts.UTF_8));
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println("response ==" + response);
        HttpEntity entity = response.getEntity();

        String resultHtml = null;
        try {
            resultHtml = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("响应的密文串：" + resultHtml);
        return resultHtml;
    }

    public static String sendHttpReq(String url, String msg, String signStr) {
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        paramList.add(new BasicNameValuePair("requestXml", msg));
        paramList.add(new BasicNameValuePair("signMsg", signStr));

        // 创建http客户端
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(new UrlEncodedFormEntity(paramList, Consts.UTF_8));
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println("response ==" + response);
        HttpEntity entity = response.getEntity();

        String resultHtml = null;
        try {
            resultHtml = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("响应的密文串：" + resultHtml);
        return resultHtml;
    }

}