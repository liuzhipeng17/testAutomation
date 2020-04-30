package com.api.common.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class YamlUtils {

    public static Iterable<Object> loadFromStream(String file) throws IOException {
        InputStream input = new FileInputStream(new File(file));
        Yaml yaml = new Yaml();
        Iterable<Object> ret = yaml.loadAll(input);
        return ret;
    }

}
