package ApiTests;

import Utils.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertTrue;


@FixMethodOrder(MethodSorters.NAME_ASCENDING) //For Ascending order test execution
public class Example1Test {

    //First, I declared Response and JsonPath objects.
    private Response res = null; //Response object
    private JsonPath jp = null; //JsonPath object

    /*
    Second, we should do setup operations, get JSON response from the API and put it into JsonPath object
    Then we will do query and manipulations with JsonPath class’s methods.
    We can do all of the preparation operations after @Before Junit annotation.
    */
    @Before
    public void setup (){
        //Test Setup
        RestUtil.setBaseURI("http://api.5min.com"); //Setup Base URI
        RestUtil.setBasePath("search"); //Setup Base Path
        RestUtil.setContentType(ContentType.JSON); //Setup Content Type
        RestUtil.createSearchQueryPath("barack obama", "videos.json", "num_of_videos", "4"); //Construct the path
        res = RestUtil.getResponse(); //Get response
        jp = RestUtil.getJsonPath(res); //Get JsonPath
    }

    @Test
    public void T01_StatusCodeTest() {
        //Verify the http response status returned. Check Status Code is 200?
        HelperMethods.checkStatusIs200(res);
    }



}