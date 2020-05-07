import Utils.YamlUtils;
import com.alibaba.fastjson.JSON;
import entity.Request;
import entity.TestCase;
import entity.TestSuite;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class YamlUtilsTest {

    @Test
    public void loadTestYamlTest() throws IOException {
        String fileName = "testcase_message.yaml";
        TestSuite testSuite = YamlUtils.loadYamlTestCase(fileName);
        for (int i=0; i< testSuite.testCases.size(); i++){
            TestCase cases = testSuite.testCases.get(i);
            String apiName = cases.apiName;
            Request reqInfo = cases.reqInfo;
            String uri = reqInfo.uri;
            String method = reqInfo.method;
            HashMap<String, String> headers = reqInfo.headers;
            HashMap<String, Object> body = reqInfo.body;
            List<HashMap<String, Object>> validators = reqInfo.validators;

            System.out.println("\n"+apiName);
            System.out.println(uri);
            System.out.println(method);
            System.out.println(JSON.toJSONString(headers));
            System.out.println(JSON.toJSONString(body));
            for(int j=0; j<validators.size(); j++){
                System.out.println(JSON.toJSONString(validators.get(j)));
            }
        }

    }
}
