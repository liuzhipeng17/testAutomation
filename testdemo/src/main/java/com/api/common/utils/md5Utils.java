package com.api.common.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class md5Utils
{

    public static String getContentMd5(String body) throws UnsupportedEncodingException
    {

        byte[] bodyMd5 = DigestUtils.md5Digest(body.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(bodyMd5);
    }

    public static String getSignature(String appId, String messageId, String body,
                               String appKey, String timeStamp, String appSecret) throws UnsupportedEncodingException
    {
        // 获取 body md5值
        byte[] bodyMd5 = DigestUtils.md5Digest(body.getBytes("UTF-8"));
        String contentMd5 = Base64.getEncoder().encodeToString(bodyMd5);
        // 获取 sign
        //String timeStamp = Long.toString(new Date().getTime());
        String stringToSign = appId + "\n" + messageId + "\n" + contentMd5 + "\n" + appKey + "\n" + timeStamp + "\n";
        String signature = DigestUtils.md5DigestAsHex((stringToSign+appSecret).getBytes("UTF-8"));
        return signature;
    }
}
