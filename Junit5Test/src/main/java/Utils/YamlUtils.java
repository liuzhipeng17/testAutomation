package Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import entity.TestSuite;

import java.io.IOException;

public class YamlUtils {

    public static TestSuite loadYamlTestCase(String file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory()).findAndRegisterModules();
        return mapper.readValue(TestSuite.class.getClassLoader().getResourceAsStream(file), TestSuite.class);

    }

}
