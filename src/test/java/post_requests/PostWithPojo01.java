package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostWithPojo01 extends JsonPlaceHolderBaseUrl {

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
    public void postWithPojo01(){
        //Set the Url
        spec.pathParam("first", "todos");

        //Set the request body
        JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(666, "Tidy your room", false);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

        //Do Assertion
        //1st Way:
        response.
                then().
                statusCode(201).
                assertThat().
                body("userId", equalTo(requestBody.getUserId()),
                        "title", equalTo(requestBody.getTitle()),
                        "completed", equalTo(requestBody.getCompleted()));

        //2nd Way: De-serialization
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        assertEquals(requestBody.getUserId(), actualData.getUserId());
        assertEquals(requestBody.getTitle(), actualData.getTitle());
        assertEquals(requestBody.getCompleted(), actualData.getCompleted());

    }


}
