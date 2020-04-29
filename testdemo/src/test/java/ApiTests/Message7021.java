package ApiTests;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import java.io.UnsupportedEncodingException;

import org.springframework.util.DigestUtils;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matcher.*;



public class Message7021 {

    @Test
    public void getActivityDetailReqV3Test() throws UnsupportedEncodingException{

        // 客户端版本的信息，应用管理-激情版-11
        String appKey = "f6d4070c0b5da540e0976796c79d527f";
        String  appId = "11";
        String appSecret = "205c643a637a9e2cf7792dd39e118ff8";

        // 服务活动系统的接口 700011标识为活动系统， 7019标识为method
        String provider = "700011";
        String messageId = "7021";
        // 设置headers
        HashMap<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("X-Tpf-Provider-Id", provider); //服务：活动系统的应用标识， 应用管理可看
        headersMap.put("X-Tpf-App-Key", appKey);
        headersMap.put("X-Tpf-Method", "1");  //这个有疑问
        headersMap.put("X-Tpf-App-Id", appId);
        headersMap.put("X-Tpf-Msg-Id", messageId);

        // body
        LinkedHashMap<String, Object> json = JSON.parseObject("{}",LinkedHashMap.class, Feature.OrderedField);
        json.put("appId", 11);
        json.put("id", "ba5358a9-ceca-4a01-a6e4-27ed589b3cbc");
        json.put("taskInfo", "10001002");
        json.put("roleId", "9258399");
        json.put("serverId", "226");
        json.put("version", "1");
        json.put("extra", "1");
        JSONObject bodyJson = new JSONObject(true);
        bodyJson.putAll(json);
        String bodyStr = bodyJson.toString();
        // 获取 body md5值
        byte[] bodyMd5 = DigestUtils.md5Digest(bodyStr.getBytes("UTF-8"));
        String contentMd5 = Base64.getEncoder().encodeToString(bodyMd5);
        // 获取 sign
        String timeStamp = Long.toString(new Date().getTime());
        String stringToSign = appId + "\n" + messageId + "\n" + contentMd5 + "\n" + appKey + "\n" + timeStamp + "\n";
        String signature = DigestUtils.md5DigestAsHex((stringToSign+appSecret).getBytes("UTF-8"));

        headersMap.put("X-Tpf-Signature", signature);
        headersMap.put("X-Tpf-Content-MD5", contentMd5);
        headersMap.put("X-Tpf-Timestamp", timeStamp);
        headersMap.put("X-Tpf-Nonce", "1");
        headersMap.put("X-Tpf-Extra", "1");
        headersMap.put("X-Tpf-Version", "1");

        // 设置header结束
        given().log().all().
                headers(headersMap).
                header("accept", "application/json").
                contentType("application/json").
                body(bodyStr).
                when().
                post("http://10.100.1.22:20010/2000").then().log().all().statusCode(200);

    }










}
