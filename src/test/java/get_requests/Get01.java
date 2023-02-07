package get_requests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
    Gherkin Language    : In Api testing, we use some keywords: Given, When, Then, And.
    Given               : Declare pre-requisites
    When                : It is used to declare actions.
    Then                : It is for outputs.
    And                 : It is used to combine multiple given, when, then.
     */

    /*
    Junit is a framework, is used to testing. It makes testing easy.
    @Test annotation comes from Junit Library.
     */

    /*
    Given
        https://restful-booker.herokuapp.com/booking/2098
    When
        User send a GET request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK

     */
    @Test
    public void get01(){

        //Set the url
        String url = "https://restful-booker.herokuapp.com/booking/2098"; // not recommended

        //Set the expected data

        //Type automation script to send the HTTP GET request and get the response
        Response response = given().when().get(url);
        System.out.println(response.prettyPrint());
        System.out.println(response.getTime());// performance testing
        System.out.println(response.getHeaders());// all headers
        System.out.println(response.getContentType());
        System.out.println(response.getStatusCode());

        //Do Assertion
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

    }



}
