package Utils;

import entity.TestSuite;

import java.io.File;
import java.io.IOException;
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
        // 怎么获取resource目录的文件
        String filePath = FileUtils.getFilePath(fileName);
        if(filePath.equals("")){
            throw new Exception(String.format("%s not exist", fileName));
        }

        //String pathName = TestSuite.class.getClassLoader().getResource(fileName).getPath();
        // 加个判断pathName是否存在
        TestSuite tcs = mapper.readValue(new File(filePath), TestSuite.class);
        // 将一个java对象convert string
        //System.out.println(ReflectionToStringBuilder.toString(tcs, ToStringStyle.MULTI_LINE_STYLE));
        return tcs;

    }





}
