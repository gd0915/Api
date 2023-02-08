package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {

    /*
    Given
        https://jsonplaceholder.typicode.com/todos/198

                {
                "userId": 10,
                "title": "quis eius est sint explicabo",
                "completed": true
            }
    When
        User send PUT request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response body should be like
                {
                "userId": 10,
                "title": "quis eius est sint explicabo",
                "completed": true
            }
     */

    @Test
    public void put01() {
        //Set the Url
        spec.pathParams("first", "todos", "second", 198);

        //Set the request body
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String, Object> requestBodyMap = requestBody.expectedDataSetUp(10, "quis eius est sint explicabo", true);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().put("/{first}/{second}");
        System.out.println(response.prettyPrint());

        //Do Assertion
        //1st Way:
        response.
                then().
                assertThat().
                statusCode(200).
                body("userId", equalTo(requestBodyMap.get("userId")),
                "title", equalTo(requestBodyMap.get("title")),
                        "completed", equalTo(requestBodyMap.get("completed")));

        //2nd Way: Use GSON => De-Serialize(recommended) Converting Json data to Java object
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        assertEquals(requestBodyMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(requestBodyMap.get("title"), actualDataMap.get("title"));
        assertEquals(requestBodyMap.get("completed"), actualDataMap.get("completed"));

        //We do not assertion on ids because most of the time, ids are created by API, but we can do

        //How to use GSON in Serialization: Java Object ==>> Json Data
        Map<String, Object> ages = new HashMap<>(); //{ }
        ages.put("Ali Can", 13);
        ages.put("Veli Han", 15);
        ages.put("Ayse Kan", 21);
        ages.put("Mary Star", 65);
        System.out.println(ages);// {Mary Star=65, Ayse Kan=21, Ali Can=13, Veli Han=15}

        //Convert ages map into Json data
        //1.step: Create Gson Object
        Gson gson = new Gson();
        //2.Step: By using "gson" object select method to convert Java Object to Json Data
        String jsonFromMap = gson.toJson(ages);
        System.out.println(jsonFromMap);//{"Mary Star":65,"Ayse Kan":21,"Ali Can":13,"Veli Han":15}



    }




















}
