package delete_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utils.AuthenticationHerOkuApp;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Delete02 extends HerOkuAppBaseUrl {
    /*
     Given
        https://restful-booker.herokuapp.com/booking/{bookingId}
    When
        User send DELETE request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response body is "Created"
     */

    /**
      Basic Token: HerOkuApp
         Username : Admin
         Password: password123
      */


    @Test
    public void delete02(){
       //Set the Url
       spec.pathParams("first", "booking", "second", 1121);

       //Set the expected data
        String expectedData = "Created";

       //Send the request and get the response
       Response response = given().
                               spec(spec).
                               headers("Cookie",  "token="+ AuthenticationHerOkuApp.generateToken()).
                               contentType(ContentType.JSON).
                               when().
                               delete("/{first}/{second}");
       response.prettyPrint();

       //Do Assertion
        assertEquals(201, response.getStatusCode());
       assertEquals(expectedData, response.asString());


    }

}
