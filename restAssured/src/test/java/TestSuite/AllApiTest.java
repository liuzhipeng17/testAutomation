package TestSuite;

import ApiTests.Example1Test;
import ApiTests.Example2Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
//import ApiTests.UseCase1Test;
//import ApiTests.UseCase2Test;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        Example1Test.class,
        Example2Test.class,
})
public class AllApiTest {
}
