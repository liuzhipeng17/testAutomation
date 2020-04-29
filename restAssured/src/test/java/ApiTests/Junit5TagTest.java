package ApiTests;


//import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Tag("adf")
class Junit5TagTest {
    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
    }

    @Test
    @Tag("no")
    void testingTax(){

    }
}
