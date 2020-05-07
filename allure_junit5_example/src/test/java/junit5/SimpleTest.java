package junit5;


import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;



public class SimpleTest {


    @Test
    void testOutput(){
        firstStep();
        secondStep();
    }



    @Step
    private void firstStep(){
        System.out.println("first");
    }

    @Step
    private void secondStep(){
        System.out.println("second");
    }
}
