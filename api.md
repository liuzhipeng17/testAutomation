# junit5

## 依赖

junit5 = JunitPlatform + junitJnpiter + junitVintage

```
<dependencies>

    <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-launcher</artifactId>
        <version>1.0.1</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.0.1</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.vintage</groupId>
        <artifactId>junit-vintage-engine</artifactId>
        <version>4.12.1</version>
        <scope>test</scope>
    </dependency>
    
</dependencies>
```

#  yaml文件读取

## 依赖

```html
<properties>
    <fasterxml.jackson>2.10.2</fasterxml.jackson>
</properties>

<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>${fasterxml.jackson}</version>
</dependency>

<!-- factory-->
 <dependency>
   <groupId>com.fasterxml.jackson.dataformat</groupId>
   <artifactId>jackson-dataformat-yaml</artifactId>
   <version>${fasterxml.jackson}</version>
</dependency>

```

#### 网址

## junit4 实现yaml数据驱动 dataprovider

- 依赖

  ``` html
  <dependencies>
      <!-- ... -->
      <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.12</version>
          <scope>test</scope>
      </dependency>
      <dependency>
          <groupId>com.tngtech.java</groupId>
          <artifactId>junit-dataprovider</artifactId>
          <version>1.10.0</version>
          <scope>test</scope>
      </dependency>
      <!-- ... -->
  </dependencies>
  ```

  

- official documents

  [官网](https://github.com/TNG/junit-dataprovider)

- example

```java
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class DataProviderTest {

    @DataProvider
    public static Object[][] dataProviderAdd() {
        // @formatter:off
        return new Object[][] {
                { 0, 0, 0 },
                { 1, 1, 2 },
                /* ... */
        };
        // @formatter:on
    }

    @Test
    @UseDataProvider("dataProviderAdd")
    public void testAdd(int a, int b, int expected) {
        // Given:

        // When:
        int result = a + b;

        // Then:
        assertEquals(expected, result);
    }
}
```

- 读取Yaml文件 snakeYaml 

  
  
  中心思想： 将dataProviderAdd的返回值变成读取yaml数据

# hamcrest断言

[网址](https://www.jianshu.com/p/e7d4c3bdac6e)

[网址2](https://www.cnblogs.com/LinkinPark/p/5232868.html)

- 依赖

  ```html
  https://www.cnblogs.com/LinkinPark/p/5232868.html
  ```

  

## junit5 实现yaml数据驱动

- 网址

  https://zhuanlan.zhihu.com/p/114170332

- 需要添加依赖

```html
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-params</artifactId>
    <version>5.2.0</version>
    <scope>test</scope>
</dependency>
```

- 测试用例数据驱动

参数化 @ParameterizedTest +@ValueSource

```java

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MethodSourceTest {

    @ParameterizedTest
    @MethodSource("stringTestCaseProvider1")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(5, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }

/*
    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.of("apple", 1, Arrays.asList("a", "b")),
                Arguments.of("lemon", 2, Arrays.asList("x", "y"))
        );
    }*/

    static Stream<Arguments> stringTestCaseProvider1() {

        List<Arguments> list = new ArrayList<>();
        list.add(Arguments.of("apple", 1, Arrays.asList("a", "b")));
        list.add(Arguments.of("lemon", 2, Arrays.asList("x", "y")));
        //return Stream.of(
         //       list.get(0), list.get(1)
        //);
        Stream s = Stream.of(list.get(0));
        for (int i=1; i< list.size();i++){

            s = Stream.concat(s, Stream.of(list.get(i)));
        }
        return s;
        //return Stream.concat(Stream.of(list.get(0)), Stream.of(list.get(1)));
    }


}
```

# yaml的读取

## jackson

- 教程

  https://dzone.com/articles/read-yaml-in-java-with-jackson

- 依赖

```html
<dependencies>
    <dependency>
        <groupId>com.fasterxml.jackson.dataformat</groupId>
        <artifactId>jackson-dataformat-yaml</artifactId>
        <version>2.3.0</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.2.3</version>
    </dependency>
    <!-- 将java对象转换成string -->
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.4</version>
    </dependency>
</dependencies>
```

- 从Yaml文件读取java对象

```yaml
# Details of a user yaml file
---
name: Test User
age: 30
address:
  line1: My Address Line 1
  line2: Address line 2
  city: Washington D.C.
  zip: 20000
roles: 
  - User
  - Editor
```

- 对应的java对象

```java
public class User {
    private String name;
    private int age;
    private Map<String, String> address;
    private String[] roles;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Map<String, String> getAddress() {
        return address;
    }
    public void setAddress(Map<String, String> address) {
        this.address = address;
    }
    public String[] getRoles() {
        return roles;
    }
    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
```

- 使用jackson.yamlFactory读取

```java

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
public class YamlTesting {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            User user = mapper.readValue(new File("user.yaml"), User.class);
        System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
```





## snakeyaml

- 依赖

```html
<dependency>
    <groupId>org.yaml</groupId>
    <artifactId>snakeyaml</artifactId>
    <version>1.17</version>
</dependency>
```

- example

```java
@Test
public void testLoad() {
    String yamlStr = "key: hello yaml";
    Yaml yaml = new Yaml();
    Object ret = yaml.load(yamlStr);
    System.out.println(ret);
}
// 
```



# issue

- idea mvn test命令提示 no compiler<plugin>

    <build>
    <plugins>
    <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
    <source>8</source>
    <target>8</target>
    </configuration>
    </plugin>
    </plugins>
    </build>
配置java的环境变量， JAVA_HOEM, CLASS_PATH

https://www.runoob.com/java/java-environment-setup.html



# allure 常用注解

## @Step

测试步骤动作，在报告会显示#1, #2 这里面的1，2就是步骤

## @Attachments



# junit4 指定执行顺序

``` java
import org.junit.FixMethodOrder;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimpleTest {}

# 然后就会按测试类下的test方法名字ascii码进行顺序执行
```



# jackson

[戳我](http://www.myexample.cc/jackson/jackson-yaml/)

## pom.xml

```html
<!-- jackson -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.10.2</version>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-yaml</artifactId>
    <version>2.9.8</version>
</dependency>

```

