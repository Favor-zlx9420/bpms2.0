package com.zlx.bpms.utils;

import com.zlx.bpms.constant.BpmsConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @Package: com.zlx.bpms.utils
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:生成token工具
 */
@Slf4j
public class JwtTools {

    /**
     * 寻找证书文件
     */
    private static InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks");
    /**
     * 私钥
     */
    private static PrivateKey privateKey = null;
    /**
     * 公钥
     */
    private static PublicKey publicKey = null;


    static {
        try {
            //JAVA KEY STORE
            KeyStore store = KeyStore.getInstance("JKS");
            store.load(is, BpmsConstant.PASSWORD.toCharArray());
            privateKey = (PrivateKey) store.getKey("jwt", BpmsConstant.PASSWORD.toCharArray());
            publicKey = store.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成token
     *
     * @param subject           当前token的key值
     * @param expirationSeconds 失效时间
     * @return token
     */
    static String generateToken(String subject, int expirationSeconds) {
        return Jwts.builder()
                .setClaims(null)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(SignatureAlgorithm.RS512, privateKey)
                .compact();
    }

    /**
     * token解析
     *
     * @param token
     * @return
     */
    public static String parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    /**
     *  压缩
     * @param text 压缩文件路径
     * @return
     */
    public static String compress(String text) {
        if (StringTools.isEmpty(text)) {
            return null;
        }
        ByteArrayOutputStream baos = null;
        GZIPOutputStream zos = null;
        try {
            baos = new ByteArrayOutputStream();
            zos = new GZIPOutputStream(baos);
            zos.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (null != zos) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            text = baos.toString("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     *  解压
     * @param text
     * @return
     */
    @SuppressWarnings("unused")
    public static String uncompress(String text) {
        if (StringTools.isEmpty(text)) {
            return null;
        }
        ByteArrayOutputStream baos = null;
        ByteArrayInputStream bais = null;
        GZIPInputStream zis = null;
        try {
            baos = new ByteArrayOutputStream();
            bais = new ByteArrayInputStream(text.getBytes(StandardCharsets.ISO_8859_1));
            zis = new GZIPInputStream(bais);
            byte[] bytes = new byte[256];
            int len = 0;
            while ((len = zis.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
                text = baos.toString("GBK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (zis != null) {
                try {
                    zis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return text;
    }


}
