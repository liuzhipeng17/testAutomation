import Utils.YamlUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import entity.TestCase;
import entity.TestSuite;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.runner.RunWith;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.util.DigestUtils;

import static Utils.EncryptUtils.md5;
import static io.restassured.RestAssured.given;


@Feature("yaml data provider class")
public class YamlUtilTest {



    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("test demo")
    @DisplayName("load yaml file")
    public void JacksonTest(){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try{
            String pathName = this.getClass().getClassLoader().getResource("testcase_activity.yaml").getPath();
            TestSuite tcs = mapper.readValue(new File(pathName), TestSuite.class);
            // 将一个java对象convert string
            System.out.println(ReflectionToStringBuilder.toString(tcs.testCases,ToStringStyle.MULTI_LINE_STYLE));
        } catch (Exception e){
            e.printStackTrace();

        }

    }

    @DisplayName("loadYamlWithJackson")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void YamlUtils2() throws Exception {
        TestSuite tcs = YamlUtils.loadYamlWthJackson("testcase_login.yaml");
        System.out.println(tcs.testCases);
        System.out.println(tcs.testCases.get(0).getBody());

    }


}
