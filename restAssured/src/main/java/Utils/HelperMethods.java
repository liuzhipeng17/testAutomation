package Utils;

import io.restassured.response.Response;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class HelperMethods {
    /*
    Verify the http response status returned. Check Status Code is 200?
    We can use Rest Assured library's response's getStatusCode method
    */
    public static void checkStatusIs200 (Response res) {
        assertEquals("Status Check Failed!", 200, res.getStatusCode());
    }

}