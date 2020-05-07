import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.TestCase;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class LoginAuth {



    public static HashMap<String, Object> constructBody(TestCase tc) throws UnsupportedEncodingException {

        String appId = tc.getHeaders().get("appId");
        String channelId = tc.getHeaders().get("channelId");
        String playerId = (String)tc.getBody().get("playerId");
        String uid = (String)tc.getBody().get("uid");
        String appKey = GLOBAL.LOGIN_APP_KEY;

        // 计算sign
        LinkedHashMap<String, String> data = JSON.parseObject("{}",LinkedHashMap.class, com.alibaba.fastjson.parser.Feature.OrderedField);
        data.put("playerId", playerId);
        data.put("uid", uid);
        JSONObject extension = new JSONObject();
        extension.put("SdkLoginDetail", data);

        String t = "appId=" + appId + "channelId=" + channelId + JSON.toJSONString(extension) + appKey;
        String sign = DigestUtils.md5DigestAsHex((t).getBytes("UTF-8"));

        // 计算body
        HashMap<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("sign", sign);
        bodyMap.put("extension", JSON.toJSONString(extension));
        return bodyMap;
    }


    public static void main(String[] args)  throws UnsupportedEncodingException {

        String appId = "0";
        String playerId = "123";
        String uid = "456";
        String channelId = "13";
        String appKey = GLOBAL.LOGIN_APP_KEY;

        LinkedHashMap<String, String> data = JSON.parseObject("{}",LinkedHashMap.class, com.alibaba.fastjson.parser.Feature.OrderedField);
        data.put("playerId", playerId);
        data.put("uid", uid);
        JSONObject extension = new JSONObject();
        extension.put("SdkLoginDetail", data);

        String t = "appId=" + appId + "channelId=" + channelId + JSON.toJSONString(extension) + appKey;
        System.out.println(DigestUtils.md5DigestAsHex((t).getBytes("UTF-8")));

        String s = "ewe";
        String[] ts = s.split(Pattern.quote("."));
        System.out.println(ts.length);
        System.out.println(ts[0]);
    }
}
