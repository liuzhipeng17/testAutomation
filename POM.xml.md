# POM.xml

## Junit Library

单元测试框架

## Hamcrest Library

断言method

## Jayway RestAssured Library

接口测试框架

## mvn 命令行运行

For running the test from **command prompt** with maven I also added **AllApiTests profile** at the end of the pom.xml. We can run all tests with “**mvn test –PallApiTests**” command.



## 完整的pom.xml

```html
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
 
    <groupId>com.5min.apitest</groupId>
    <artifactId>5min-apitest</artifactId>
    <version>1.0-SNAPSHOT</version>
 
    <!--Dependencies-->
    <dependencies>
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-all</artifactId>
            <version>1.3</version>
        </dependency>
 
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-junit</artifactId>
            <version>2.0.0.0</version>
        </dependency>
 
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
 
        <dependency>
            <groupId>com.jayway.restassured</groupId>
            <artifactId>json-schema-validator</artifactId>
            <version>2.8.0</version>
        </dependency>
 
        <dependency>
            <groupId>com.jayway.restassured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>2.8.0</version>
        </dependency>
    </dependencies>
 
    <!--Profiles-->
    <profiles>
        <profile>
            <id>AllApiTests</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <build>
                <pluginManagement>
                    <plugins>
                        <plugin>
                            <groupId>org.apache.maven.plugins</groupId>
                            <artifactId>maven-surefire-plugin</artifactId>
                            <version>2.19.1</version>
                            <configuration>
                                <includes>
                                    <include>**/AllApiTest.class</include>
                                </includes>
                            </configuration>
                        </plugin>
                    </plugins>
                </pluginManagement>
            </build>
        </profile>
    </profiles>
 
</project>
```

