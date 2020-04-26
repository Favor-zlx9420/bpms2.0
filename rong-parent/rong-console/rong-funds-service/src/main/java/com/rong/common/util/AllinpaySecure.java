package com.rong.common.util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

/**
 * 通联H5加解密操作
 */
public class AllinpaySecure {
    private static PublicKey pubKey = null;

    private static PrivateKey priKey = null;

    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA"; // ?????

    public static final String KEY_GEN_ALGORITHM = "RSA"; // ???????

    private static final String PASSWORD = "111111";// ?????????

    private static final String SIGNMODE = "PKCS12";// ??????

    private static final String FORMAT = "X.509";// ????????

    private static final String PUB_KEY_URL = "pub.pem";

    private static final String PRI_KEY_URL = "zhhx.pfx";

    //???????????Ч???????????????????????server??????Ч
//    static
//    {
//        try
//        {
    // ?????? ??
//            String url = "D:\\workspaces\\WTSDemoM/lib/fps_h5/";
//            FileInputStream priStream = new FileInputStream(url + PRI_KEY_URL);
//            FileInputStream inStream = new FileInputStream(url + PUB_KEY_URL);

//            System.out.println("@@@@@@@@@PUB_KEY_URL" + url + PRI_KEY_URL);
//            System.out.println("@@@@@@@@@PRI_KEY_URL" + url + PUB_KEY_URL);

    // ??
//            KeyStore ks = KeyStore.getInstance(SIGNMODE);
//            ks.load(priStream, PASSWORD.toCharArray());
//            priStream.close();
//            Enumeration enumas = ks.aliases();
//            String keyAlias = null;
//            if (enumas.hasMoreElements())
//            {
//                keyAlias = (String) enumas.nextElement();
//            }
//            priKey = (PrivateKey) ks.getKey(keyAlias, PASSWORD.toCharArray()); // ?????
    // ???
//            CertificateFactory cf = CertificateFactory.getInstance(FORMAT);
//            X509Certificate cert2 = (X509Certificate) cf.generateCertificate(inStream);
//            pubKey = cert2.getPublicKey();// ???

//        }
//        catch (Exception e)
//        {
//            System.out.println("@@@@@@@@@@Read pubKey or priKey error");
//            e.printStackTrace();
//            throw new RuntimeException("???????????????????[" + e.getMessage() + "]");
//        }
//    }

    public static void initSysCert(String priKeyUrl, String pubKeyUrl) {
        try {
            FileInputStream priStream = new FileInputStream(priKeyUrl);
//            FileInputStream inStream = new FileInputStream(url + PUB_KEY_URL);

            KeyStore ks = KeyStore.getInstance(SIGNMODE);
            ks.load(priStream, PASSWORD.toCharArray());
            priStream.close();
            Enumeration enumas = ks.aliases();
            String keyAlias = null;
            if (enumas.hasMoreElements()) {
                keyAlias = (String) enumas.nextElement();
            }
            priKey = (PrivateKey) ks.getKey(keyAlias, PASSWORD.toCharArray()); // ?????

//            CertificateFactory cf = CertificateFactory.getInstance(FORMAT);
//            X509Certificate cert2 = (X509Certificate) cf.generateCertificate(inStream);
//            pubKey = cert2.getPublicKey();// ???

        } catch (Exception e) {
            System.out.println("@@@@@@@@@@Read pubKey or priKey error");
            e.printStackTrace();
            throw new RuntimeException("???????????????????[" + e.getMessage() + "]");
        }
    }

    /**
     * ???
     *
     * @param msg
     */
    public static String signMsg(String msg) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(priKey);
            signature.update(msg.getBytes());
            byte[] signData = signature.sign();

            BASE64Encoder base64Encode = new BASE64Encoder();

            return base64Encode.encode(signData);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("@@@@@@@@@@sign msg fail: " + e.getStackTrace()[0]);
            throw new RuntimeException("????????????RSA???????????????", e);
        } catch (InvalidKeyException e) {
            System.out.println("@@@@@@@@@@sign msg fail: " + e.getStackTrace()[0]);
            throw new RuntimeException("???????????Ч", e);
        } catch (SignatureException e) {
            System.out.println("@@@@@@@@@@sign msg fail: " + e.getStackTrace()[0]);
            throw new RuntimeException("???????signature?????????", e);
        }
    }

    /**
     * ???
     *
     * @param signMsg
     * @param singedMsg
     * @return
     */
    public boolean veriSign(String singedMsg, String signMsg) {
        Boolean rlt = false;
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(signMsg.getBytes());

            BASE64Decoder base64Decode = new BASE64Decoder();
            byte[] signed = base64Decode.decodeBuffer(singedMsg);

            if (signature.verify(signed)) {
                rlt = true;
            }
            System.out.println("@@@@@@@@@@@veriSign result: " + rlt);
            return rlt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("????????????RSA???????????????", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("????????????Ч", e);
        } catch (SignatureException e) {
            throw new RuntimeException("???????signature?????????", e);
        } catch (IOException e) {
            throw new RuntimeException("??????? IO Exception", e);
        }
    }

    /**
     * 加密
     *
     * @param dataRsaPubKey 加密公钥
     * @param content       要加密的字符串
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static String encryptByPublicKey(String dataRsaPubKey, String content)
            throws GeneralSecurityException, IOException {
        //公钥(BASE64编码)
        byte[] ctb = new byte[0];
        try {
            ctb = content.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] dataRsaPubKeyBytes = Base64.decodeBase64(dataRsaPubKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(dataRsaPubKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = ctb.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 512) {
                cache = cipher.doFinal(ctb, offSet, 512);
            } else {
                cache = cipher.doFinal(ctb, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 512;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        String encodeBase64String = Base64.encodeBase64String(encryptedData);
        return encodeBase64String;
    }

    /**
     * 解密
     *
     * @param dataRsaPriKey
     * @param content
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static String decryptByPrivateKey(String dataRsaPriKey, String content)
            throws GeneralSecurityException, IOException {
        byte[] dataRsaPriKeyBytes = Base64.decodeBase64(dataRsaPriKey);
        //私钥(BASE64编码)
        byte[] decodeBase64 = Base64.decodeBase64(content);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(dataRsaPriKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = decodeBase64.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 512) {
                cache = cipher.doFinal(decodeBase64, offSet, 512);
            } else {
                cache = cipher.doFinal(decodeBase64, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 512;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData, "utf-8");
    }

    /**
     * 生成签名
     *
     * @param privateKey
     * @param appId
     * @param bizContent
     * @param timestamp
     * @param version
     * @return
     * @throws GeneralSecurityException
     */
    public static String generateSign(String privateKey, String appId, String bizContent, String timestamp, String version)
            throws GeneralSecurityException {
        byte[] privateKeyBytes = Base64.decodeBase64(privateKey);
        StringBuilder builder = new StringBuilder();
        String checkSignStr = builder.append(appId).append(bizContent).append(timestamp).append(version).toString();
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        Signature signature;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            signature = Signature.getInstance("SHA256WithRSA");
            signature.initSign(privateK);
            signature.update(checkSignStr.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            throw e;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        } catch (InvalidKeyException e) {
            throw e;
        } catch (InvalidKeySpecException e) {
            throw e;
        }
        byte[] result = signature.sign();
        return Base64.encodeBase64String(result);
    }

    /**
     * 验签
     *
     * @param publicKey
     * @param appId
     * @param bizContent
     * @param timestamp
     * @param version
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verifySign(String publicKey, String appId, String bizContent, String timestamp, String version, String sign)
            throws Exception {
        byte[] publicKeyBytes = Base64.decodeBase64(publicKey);
        StringBuilder builder = new StringBuilder();
        String content = builder.append(appId).append(bizContent).append(timestamp).append(version).toString();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initVerify(publicK);
        signature.update(content.getBytes("utf-8"));
        return signature.verify(Base64.decodeBase64(sign));
    }

    public static void main(String[] args) {
        AllinpaySecure secure = new AllinpaySecure();
        String aa = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><Name>test</Name><Flag>0</Flag><UserId>123456</UserId><AllinpayNo>201703172141059031</AllinpayNo><Timestamp>20200415131400</Timestamp></Request>";

        String bb = secure.signMsg(aa);
        System.out.println("密文：" + bb);

//        System.out.println(secure.veriSign(bb, aa));
    }
}
