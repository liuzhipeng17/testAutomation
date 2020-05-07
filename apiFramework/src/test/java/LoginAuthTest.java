import Utils.YamlUtils;
import com.alibaba.fastjson.JSONObject;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import entity.TestCase;
import entity.TestSuite;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;

import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

@Feature("login auth class")
@RunWith(DataProviderRunner.class)
public class LoginAuthTest {

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @DataProvider
    public static Object[] yamlDataProviderActivity() throws Exception {
        TestSuite testSuite =  YamlUtils.loadYamlWthJackson(GLOBAL.TEST_CASE_LOGIN);
        List<TestCase> testCases = testSuite.testCases;
        int size = testCases.size();
        Object[] objs = new Object[size];
        for(int i=0; i<size; i++)
        {
            TestCase tc = testCases.get(i);
            //tc.setHeaders(Activity.setSignatureHeader(tc));
            HashMap<String, Object> body = LoginAuth.constructBody(tc);
            tc.setBody(body);
            objs[i] = tc;
        }

        return objs;
    }

    @Test
    @Step("test {tc.apiName} api")
    @Severity(SeverityLevel.BLOCKER)
    @UseDataProvider("yamlDataProviderActivity")
    public  void loginAuth(TestCase tc) throws Exception{

        // send request
         Map<String, Object> response = given().
                headers(tc.getHeaders()).log().all().
                 contentType("application/json").
                 request().
                 body(JSONObject.toJSONString(tc.getBody())).
                 when().
                 post(tc.getUri()).as(new io.restassured.mapper.TypeRef<Map<String, Object>>(){});
         // https://github.com/rest-assured/rest-assured/wiki/Usage#example-1---json
        // 3.30版本的restassured
        //System.out.println(response);
        List<HashMap<String, Object>> validators = tc.getValidators();
        CustomAssertion.softValidate(response, validators, collector);

    }


}
