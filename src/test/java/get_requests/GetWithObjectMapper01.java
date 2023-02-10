package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetWithObjectMapper01 extends JsonPlaceHolderBaseUrl {
    /*
     /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
        User send GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response body like
                {
                    "userId": 10,
                    "id": 198,
                    "title": "quis eius est sint explicabo",
                    "completed": true
                }
     */

     @Test
    public void getWithObjectMapper01(){
         //1.Step: Set the Url
         spec.pathParams("first", "todos", "second", 198);

         //2.Step: Set the expected data

         //1st Way:
         //I converted Json to String by putting it between double quotes below because my converting method uses String.
         //Putting any data into double quotes, convert it into String.
//         String expectedData = "{\n" +
//                                 "\"userId\": 10,\n" +
//                                 "\"id\": 198,\n" +
//                                 "\"title\": \"quis eius est sint explicabo\",\n" +
//                                 "\"completed\": true\n" +
//                                 "}";
//         HashMap<String, Object> expectedDataMap = JsonUtil.convertJsonToJava(expectedData, HashMap.class);


         //2nd Way:
         //I created a method that convert json to String and used it below instead of converting it using double quotes

         String expectedDataInString = new JsonPlaceHolderTestData().expectedDataInString(10,"quis eius est sint explicabo",true);
         Map<String, Object> expectedDataMap = JsonUtil.convertJsonToJava(expectedDataInString, HashMap.class);
         System.out.println("expectedData = " + expectedDataMap);

         //3.Step: Send the request and get the response
         Response response = given().spec(spec).when().get("/{first}/{second}");
         response.prettyPrint();

         //4.Step: Do Assertion
         // Convert response to Java Object
         HashMap<String, Object> actualDataMap = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));

     }

}
