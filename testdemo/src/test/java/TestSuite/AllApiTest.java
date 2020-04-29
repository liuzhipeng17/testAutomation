package TestSuite;

import ApiTests.DataProviderTest;
import ApiTests.Message7019;
import ApiTests.Message7020;
import ApiTests.Message7021;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        Message7019.class,
        Message7020.class,
        Message7021.class,
        DataProviderTest.class,

})
public class AllApiTest {
}
