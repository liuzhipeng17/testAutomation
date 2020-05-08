package Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.xml.internal.ws.policy.sourcemodel.AssertionData;
import entity.TestCase;
import entity.TestSuite;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.yaml.snakeyaml.Yaml;


public class YamlUtils {
    public static TestSuite loadYamlTestCase(String file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory()).findAndRegisterModules();
        return mapper.readValue(TestSuite.class.getClassLoader().getResourceAsStream(file), TestSuite.class);

    }


    public static TestSuite loadYamlWthJackson(String fileName) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String filePath = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
        if(filePath.equals("")){
            throw new Exception(String.format("%s not exist", fileName));
        }

        //String pathName = TestSuite.class.getClassLoader().getResource(fileName).getPath();
        // 加个判断pathName是否存在
        TestSuite tcs = mapper.readValue(new File(filePath), TestSuite.class);
        return tcs;
    }

    /**
     * 读取yaml文件
     * @param fileName
     * @return
     * @throws Exception
     */

    public static List<TestCase> readFromYaml(String fileName) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String filePath = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
        if(filePath.equals("")){
            throw new Exception(String.format("%s not exist", fileName));
        }

        List<TestCase> data = mapper.readValue(
                new File(filePath),
                new TypeReference<List<TestCase>>() {}
        );
        return data;
    }





}
