package ApiTests;


import org.hamcrest.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;

import  java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ParamTest {

    @ParameterizedTest
    @ValueSource(strings = {"abd", "DFDF"})
    void palindromes(String candidate){
        assert(candidate.equals("abd"));
    }
    // ValueSource可对 strings, ints, logs, doubles进行传


    // method source， 通其他的java方法函数作为参数源。 引用的方法返回值必须是iteator,iterable, stream
    // 而且方法不接受任何参数, 方法为static
    // 如果测试方法是多个参数，引用的方法返回一个集合或Arguments实例流

    @ParameterizedTest
    @MethodSource("stringGenerator")
    void shouldNotBeNullString(String arg){
        assertNotNull(arg);
    }

     static Stream<String> stringProvider() {
        return Stream.of("foo", "bar");
    }

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num) {
        assertEquals(3, str.length());
        assertTrue(num >=1 && num <=2);
        //assertEquals(2, list.size());
    }
    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.of("foo", 1),
                Arguments.of("bar", 2)
        );
    }


}
