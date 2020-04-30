
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



