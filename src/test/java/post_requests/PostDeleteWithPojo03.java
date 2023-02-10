package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostDeleteWithPojo03 extends JsonPlaceHolderBaseUrl {

             /*
       Given
             https://jsonplaceholder.typicode.com/todos

            {
            "userId": 9,
            "title": "Tidy your room",
            "completed": true
             }
        When
            User send a POST request to the URL
        And
            User send a DELETE request to the  Url
        Then
            Response body should be like;
            {
                "userId": 9,
                "title": "Tidy your room",
                "completed": true,
                "id": 201
            }
              */
        @Test
        public void test01() {
        //Set the Url
        spec.pathParam("first", "todos");

        //Set the request body
        JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(666, "Tidy your room", false);

        //Send the request and get the response
        Response response1 = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response1.prettyPrint();

        //Get the id of newly created data using JsonPath object
            // (I can put this part in a method with @After annotation in test class, so it will run after each method inside the class)
        JsonPath json = response1.jsonPath();
        Integer id = json.getInt("id");
        System.out.println(id);

        //Set the Url for DELETE request
        spec.pathParams("first", "todos", "second", id);

        //Send the DELETE request
        Response response2  = given().spec(spec).when().delete("/{first}/{second}");
        response2.prettyPrint();

        //Do Assertion
        Map<String, Object> actualData = response2.as(HashMap.class);
        System.out.println("Actual Data = "+actualData);
        assertTrue(actualData.size()==0);

        }

}
