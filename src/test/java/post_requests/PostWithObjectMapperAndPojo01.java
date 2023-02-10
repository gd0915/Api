package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.BookingPojo;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostWithObjectMapperAndPojo01 extends JsonPlaceHolderBaseUrl {

     /*
    Given
        https://jsonplaceholder.typicode.com/todos
               {
                "userId": 55,
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
    public void postWithObjectMapperAndPojo01(){
        //Set the Url
        spec.pathParam("first", "todos");

        //Set the expected data
        JsonPlaceHolderTestData expected = new JsonPlaceHolderTestData();
        String expectedData = expected.expectedDataInString(55, "Tidy your room", false);
        JsonPlaceHolderPojo expectedDataPojo = JsonUtil.convertJsonToJava(expectedData, JsonPlaceHolderPojo.class);
        System.out.println("Expected data = " + expectedDataPojo);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataPojo).when().post("/{first}");
        response.prettyPrint();

        //Do Assertion
        JsonPlaceHolderPojo actualData = JsonUtil.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);
        assertEquals(expectedDataPojo.getUserId(), actualData.getUserId());
        assertEquals(expectedDataPojo.getTitle(), actualData.getTitle());
        assertEquals(expectedDataPojo.getCompleted(), actualData.getCompleted());


    }
}
