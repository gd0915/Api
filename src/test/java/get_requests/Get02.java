package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;


public class Get02 extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/99999
    When
        User send a GET request to the url
    Then
        HTTP Status Code should be 404
    And
        Status Line should be HTTP/1.1 404 Not Found
    And
        Response body contains "Not Found"
    And
        Response body does not contain "TechProEd"
    And
        Server is "Cowboy"

     */

    //Note: Path Parameters are used to make resource smaller

    @Test
    public void get02(){
        //Set the url
        //Path Parameters : After the baseurl if there is something coming after forward slash it is a part parameter.
        spec.pathParams("first", "booking", "second", 99999);

        //Set the expected result

        //Type automation script to send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");
        //assertTrue(true) ==>> PASS , assertTrue(false) ==>FAIL
        assertTrue(response.asString().contains("Not Found"));//if response.asString() contains "Not Found" this method returns true ==>PASS
        //assertFalse(false) ==>> PASS , assertFalse(true) ==>FAIL
        assertFalse(response.asString().contains("TechProEd"));//if response.asString() does not contain "TechProEd" this method returns true ==>PASS


    }

}
