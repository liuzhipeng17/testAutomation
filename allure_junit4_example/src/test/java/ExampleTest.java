import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.Test;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExampleTest {


    @Before
    public void setUp(){
        System.out.println("setup");
    }


    @Description("Example test")
    @Test
    public void example(){
        assertThat(2, is(2));

    }

    @Description("EX")
    @Test
    public void ex(){
        find();
        second();
    }


    @Step
    private static void find(){
        assertThat(1, equalTo(1));
    }

    @Step
    private static void second(){
        assertThat("1", equalTo("1"));
    }


}
