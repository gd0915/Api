package get_requests;

import base_urls.GoRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get10 extends GoRestApiBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users/371270
    When
        User send GET request to the Url
    Then
        Status Code should be 200
    And
        Response body should be like
               {
                "meta": null,
                "data": {
                    "id": 371270,
                    "name": "Prasanna Verma",
                    "email": "prasanna_verma@waters.net",
                    "gender": "male",
                    "status": "inactive"
                }
            }
     */
    @Test
    public void get10(){
        //Set the Url
        spec.pathParams("first", "users", "second", 371270);

        //Set the expectedData
        GoRestDataPojo dataPojo = new GoRestDataPojo("Prasanna Verma","prasanna_verma@waters.net","male","inactive");
        GoRestPojo expectedDataPojo = new GoRestPojo(null, dataPojo);
        //System.out.println("Expected data = "+expectedDataPojo);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        //Do Assertion
        GoRestPojo actualDataPojo = JsonUtil.convertJsonToJava(response.asString(), GoRestPojo.class);
        //System.out.println("Actual data = " +actualDataPojo);

        assertEquals(200, response.statusCode());
        assertEquals(expectedDataPojo.getMeta(), actualDataPojo.getMeta());
        assertEquals(expectedDataPojo.getData().getName(), actualDataPojo.getData().getName());
        assertEquals(expectedDataPojo.getData().getEmail(), actualDataPojo.getData().getEmail());
        assertEquals(expectedDataPojo.getData().getGender(), actualDataPojo.getData().getGender());
        assertEquals(expectedDataPojo.getData().getStatus(), actualDataPojo.getData().getStatus());

    }
}
