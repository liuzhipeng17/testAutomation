import com.alibaba.fastjson.JSONObject;
import entity.TestCase;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;



public class Activity {

    /**
     * 计算sign
     * @param tc
     * @return
     * @throws UnsupportedEncodingException
     */
    public static HashMap<String, String> setSignatureHeader(TestCase tc) throws UnsupportedEncodingException {
        //
        HashMap<String, Object> body = tc.getBody();
        List<HashMap<String, Object>> validators = tc.getValidators();
        String appSecret = GLOBAL.ACTIVITY_APP_SECRET;

        HashMap<String, String> headers = tc.getHeaders();

        String appId = headers.get("X-Tpf-App-Id");
        String messageId = headers.get("X-Tpf-Msg-Id");
        String appKey = headers.get("X-Tpf-App-Key");
        String url = tc.getUri();
        // body 转换成字符串
        String bodyStr = JSONObject.toJSONString(body);
        // 计算 body md5
        byte[] bodyMd5 = DigestUtils.md5Digest(bodyStr.getBytes("UTF-8"));
        String contentMd5 = Base64.getEncoder().encodeToString(bodyMd5);
        // 计算签名 sign
        String timeStamp = Long.toString(new Date().getTime());
        String stringToSign = appId + "\n" + messageId + "\n"
                + contentMd5 + "\n" + appKey + "\n" + timeStamp + "\n";
        String signature = DigestUtils.md5DigestAsHex((stringToSign+appSecret).getBytes("UTF-8"));
        headers.put("X-Tpf-Signature", signature);
        headers.put("X-Tpf-Content-MD5", contentMd5);
        headers.put("X-Tpf-Timestamp", timeStamp);
        //tc.setHeaders(headers);
        return headers;
    }

    /**
     * 软断言
     * @param validators
     */
    public static void softValidator(List<HashMap<String, Object>> validators){



    }
}
