package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Post02 extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos

                {
                "userId": 666,
                "title": "Tidy your room",
                "completed": false
                }
    When
        User send POST request to the URL
    Then
        HTTP Status Code should be 201
    And
        Response body like
                {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
                "id": 201
                }
     */

    @Test
    public void post02(){
        //Set the Url
        spec.pathParam("first", "todos");

        //Set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 666);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);
        //expectedData.put("StatusCode", 201);
        System.out.println("Expected data = " + expectedData);

        //Sent the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        System.out.println(response.prettyPrint());

        //Not to type contentType(ContentType.JSON) may cause an error or hide some data on the console
         expectedData.put("StatusCode", 201);

        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual data = " + actualData);

        assertEquals(expectedData.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));

    }
}
