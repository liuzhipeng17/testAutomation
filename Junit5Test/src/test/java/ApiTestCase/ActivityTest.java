package ApiTestCase;

import Utils.YamlUtils;
import com.alibaba.fastjson.JSONObject;
import entity.TestCase;
import entity.TestSuite;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Stream;

import org.springframework.util.DigestUtils;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ActivityTest {

    @ParameterizedTest
    @MethodSource("stringTestCaseProvider")
    public void message(TestCase tc) throws UnsupportedEncodingException {

        HashMap<String, String> headers = tc.reqInfo.headers;
        HashMap<String, Object> body = tc.reqInfo.body;
        List<HashMap<String, Object>> validators = tc.reqInfo.validators;
        String appSecret = tc.appSecret;
        String appId = tc.reqInfo.headers.get("X-Tpf-App-Id");
        String messageId = tc.reqInfo.headers.get("X-Tpf-Msg-Id");
        String appKey = tc.reqInfo.headers.get("X-Tpf-App-Key");
        String url = tc.reqInfo.uri;

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

        Response res = given().log().all().
                headers(headers).
                header("accept", "application/json").
                contentType("application/json").
                body(bodyStr).
                when().
                post(url);

                res.then().log().all().statusCode(200);

    }

    static Stream<Arguments> stringTestCaseProvider() throws IOException {
        String fileName = "testcase_message.yaml";

        TestSuite testSuite = YamlUtils.loadYamlTestCase(fileName);
        List<TestCase> testCase = testSuite.testCases;

        List<Arguments> list = new ArrayList<>();
        for(int i=0; i< testCase.size(); i++){
            list.add(Arguments.of(testCase.get(i)));
        }

        Stream s = Stream.of(list.get(0));
        for (int i=1; i< list.size();i++){

            s = Stream.concat(s, Stream.of(list.get(i)));
        }
        return s;

    }


}

