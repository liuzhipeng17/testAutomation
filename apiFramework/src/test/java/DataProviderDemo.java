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
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;

import java.util.*;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@Feature("activity class")
@RunWith(DataProviderRunner.class)
public class DataProviderDemo {

    @Rule
    public ErrorCollector c = new ErrorCollector();

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @DataProvider
    public static Object[][] dataProviderAdd3(){

        return new Object[][]{
                {0, 0, 0},
                {1, 1, 2},
        };
    }

    @Test
    @UseDataProvider("dataProviderAdd3")
    public void testAdd(int first, int second, int result){

        c.checkThat(first+second, IsEqual.equalTo(result));
        //assertEquals(first + second, result);
        c.checkThat(1+2, IsEqual.equalTo(31));

    }


    @DataProvider
    public static Object[] yamlDataProviderActivity() throws Exception {
        TestSuite testSuite =  YamlUtils.loadYamlWthJackson(GLOBAL.TEST_CASE_ACTIVITY);
        List<TestCase> testCases = testSuite.testCases;
        int size = testCases.size();
        Object[] objs = new Object[size];
        for(int i=0; i<size; i++)
        {
            TestCase tc = testCases.get(i);
            tc.setHeaders(Activity.setSignatureHeader(tc));
            objs[i] = tc;
        }

        return objs;
    }

    /**
     * 商业活动API，
     * @param tc
     * @throws Exception
     */


    @Test
    @Step("test {tc.apiName} api")
    @Severity(SeverityLevel.BLOCKER)
    @UseDataProvider("yamlDataProviderActivity")
    public void testActivity(TestCase tc) throws Exception{


        // send request
        String response = given().log().all().
                headers(tc.getHeaders()).
                contentType("application/json").
                request().
                body(JSONObject.toJSONString(tc.getBody())).
                when().
                post(tc.getUri()).asString();
        System.out.println(response);
    }

}
