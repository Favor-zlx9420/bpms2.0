package com.zlx.bpms.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @Package: com.zlx.bpms.util
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:地址工具
 */
public class AddressTools {

    /**
     * www.ip138.com
     */
    private static final String IP138_ADDRES = "http://api.ip138.com/query/?ip=%s&datatype=%s";

    /**
     * 阿里获取ip地址
     */
    private static final String IPTAOBAO_ADDRES = "http://ip.taobao.com/service/getIpInfo.php";

    /**
     * 百度地图获取ip地址
     */
    public static final String IPBAIDU_ADDRES = "http://api.map.baidu.com/location/ip?ak=%s&ip=%s&coor=bd09ll";

    /**
     * 百度地图 AK
     */
    private static final String AK = "qC08SqaLPtKpBjZg8MY2bPpPNWr4pGMe";

    /**
     * 阿里ip地址拼接前缀
     */
    private static final String IPTAOBAO_ADDRES_PRIFEX = "ip=";

    /**
     * 数据类型
     */
    private static final String DATATYPE = "json";

    /**
     * UTF-8
     */
    private static final String UTF_8 = String.valueOf(StandardCharsets.UTF_8);

    /**
     *
     */
    private static final String TOKEN = "04fd7899255c2f81b71fdfdd28543e25";


    /**
     * 通过ip地址获取详细信息
     *
     * @param ipAddress   ip地址
     * @param charsetName 字符集名称
     * @return ip所处地域详细信息
     */
    public static String getIpAddress(String ipAddress, String charsetName) {
        ipAddress = String.format(IP138_ADDRES, ipAddress, DATATYPE);
        return getIp138Addres(ipAddress, charsetName);
    }


    /**
     * 获取 ip138 网址下的ip详细信息
     *
     * @param address     IP地址
     * @param charsetName 字符集名称
     * @return ip所处地域详细信息
     */
    private static String getIp138Addres(String address, String charsetName) {
        URL url = null;
        HttpURLConnection connection = null;

        try {
            url = new URL(address);
            // 新建连接实例
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间，单位毫秒
            connection.setConnectTimeout(5000);
            // 设置读取数据超时时间，单位毫秒
            connection.setReadTimeout(5000);
            //是否打开输出流
            connection.setDoOutput(true);
            //是否打开输入流
            connection.setDoInput(true);
            //提交方法 POST|GET
            connection.setRequestMethod("GET");
            //是否实例跟随重定向
            connection.setInstanceFollowRedirects(false);
            connection.setRequestProperty("token", TOKEN);
            //是否缓存
            connection.setUseCaches(false);
            //打开连接端口
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder builder = new StringBuilder();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), charsetName));
                for (String s = br.readLine(); s != null; s = br
                        .readLine()) {
                    builder.append(s);
                }
                br.close();
                return builder.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getIpTaoBaoAddress(String ip) {
        return getTaoBaoAddress(IPTAOBAO_ADDRES, IPTAOBAO_ADDRES_PRIFEX + ip, UTF_8);
    }

    private static String getTaoBaoAddress(String address, String content, String charsetName) {
        return getTaoBaoResult(address, content, charsetName);
    }

    /**
     * 从http://whois.pconline.com.cn/取得IP所在的省市区信息
     *
     * @param urlStr         接口地址
     * @param content        参数
     * @param encodingString 编码格式
     * @return 信息
     */
    private static String getTaoBaoResult(String urlStr, String content, String encodingString) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            //新建连接实例
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间，单位毫秒
            connection.setConnectTimeout(2000);
            // 设置读取数据超时时间，单位毫秒
            connection.setReadTimeout(2000);
            //是否打开输出流
            connection.setDoOutput(true);
            //是否打开输入流
            connection.setDoInput(true);
            //提交方法 POST|GET
            connection.setRequestMethod("POST");
            //是否缓存
            connection.setUseCaches(false);
            //打开连接端口
            connection.connect();
            //打开输出流往对端服务器写数据
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            //写数据，即提交表单 name=xxx&pwd=xxx
            out.writeBytes(content);
            //刷新
            out.flush();
            //关闭输出流
            out.close();
            // 往对端写完数据对端服务器返回数据 ,以BufferedReader流来读取 //StandardCharsets.UTF_8
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encodingString));
            // ,以BufferedReader流来读取
            StringBuilder buffer = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }


    /**
     * 获取指定IP对应的经纬度（为空返回当前机器经纬度）
     *
     * @param ip ip值
     * @return 经纬度数组
     */
    public static String[] getIpLatAndLng(String ip) {

        if (StringTools.isEmpty(ip)) {
            ip = "";
        }

        try {
            URL url = new URL(String.format(IPBAIDU_ADDRES, AK, ip));
            InputStream inputStream = url.openStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputReader);
            StringBuffer sb = new StringBuffer();
            String str;
            String strAd;
            do {
                str = reader.readLine();
                sb.append(str);
            } while (null != str);
            str = sb.toString();
            if (null == str || str.isEmpty()) {
                return null;
            }
            System.out.println(str);
            strAd = str;
            // 获取坐标位子
            int index = str.indexOf("point");
            int end = str.indexOf("}}", index);
            if (index == -1 || end == -1) {
                return null;
            }
            str = str.substring(index - 1, end + 1);
            if (null == str || str.isEmpty()) {
                return null;
            }
            String[] ss = str.split(":");
            if (ss.length != 4) {
                return null;
            }
            String x = ss[2].split(",")[0];
            String y = ss[3];
            x = x.substring(x.indexOf("\"") + 1, x.indexOf("\"", 1));
            y = y.substring(y.indexOf("\"") + 1, y.indexOf("\"", 1));

            // 获取地址信息
            int indexAd = strAd.indexOf(":{\"address");
            int endAd = strAd.indexOf("address_detail", indexAd);
            if (indexAd == -1 || endAd == -1) {
                return null;
            }
            strAd = strAd.substring(indexAd + 13, endAd - 3);
            if (null == strAd || strAd.isEmpty()) {
                return null;
            }
            System.out.println(StringTools.decodeUnicode(strAd));
            return new String[]{x, y};
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
//        String str = getIpAddress("113.118.102.158", "utf-8");
        String str = getIpAddress("192.168.10.250", "utf-8");
        System.out.println(str);
    }

}
