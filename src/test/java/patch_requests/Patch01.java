package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {

    /*
     /*
    Given
        https://jsonplaceholder.typicode.com/todos/198

                {
               "title": "Wash the dishes",
             }
    When
        User send PATCH request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response body should be like
                {
                "userId": 10,
                "title": "quis eius est sint explicabo",
                "completed": true,
                "id": 198
     */

    @Test
    public void patch01(){

        //Set the Url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String, Object> requestBodyMap = requestBody.expectedDataSetUpForPatch(null, "Wash the dishes", null);
        System.out.println(requestBodyMap);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().patch("/{first}/{second}");
        System.out.println(response.prettyPrint());

        //Do Assertion

        //1.Logic: No need to verify data which you did not touch
        response.then().assertThat().statusCode(200).body("title", equalTo(requestBodyMap.get("title")));

        //2.Logic:
        Map<String, Object> expectedDataMap = requestBody.expectedDataSetUpForPut(10, "Wash the dishes", true);
        response.
                then().
                assertThat().
                statusCode(200).
                body("userId", equalTo(expectedDataMap.get("userId")),
                "title", equalTo(expectedDataMap.get("title")),
                "completed", equalTo(expectedDataMap.get("completed")));

        //GSON to do assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("Actual data = " + actualDataMap);
        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));
    }

}
