package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;


import static io.restassured.RestAssured.*;

public class Get05 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Among the data there should be someone whose firstname is "Josh" and lastname is "Allen"
     */

    //Note : In pathParam() parameter name can be anything, but in queryParam, parameter name must be like in test case.

    @Test
    public void get05(){
        //Set the URL
        spec.pathParam("first", "booking").
                queryParams("firstname", "Josh", "lastname", "Allen");

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion
        response.then().assertThat().statusCode(200);
        Assert.assertTrue(response.asString().contains("bookingid"));

    }

}
