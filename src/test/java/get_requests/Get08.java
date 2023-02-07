package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {
    /*
    The biggest challenge in API  testing is data types
    Because Api's using json data
    Java use Object, Primitive, Maps...etc
    Solution : Deserialization.
    De-Serialization means you will get json data and convert it to Java object.

    In API testing we are using Rest Assured Library. Rest Assured Library is in Java.
    Api's use json data. If I use Java, data must be understood by Java.
    So, I have to convert json data to data type which Java uses. ==>> De-Serialization

    Notes   :
    1)  Java uses Objects and Primitives as data type, API uses XML, Json, etc.
        Java and API are using different data types, but they should communicate with each other.
        Communication is impossible with different data types.

        We have 2 options:
    i)  Convert Json to Java Object  ==>> De-Serialization(mostly we do De-Serialization)
    ii) Convert Java Object to Json ==>> Serialization

       For Serialization and De-Serialization, we have 2 options:
       a)GSON           : Google created it. By using Gson we can don Serialization adn De-Serialization
       b)Object Mapper  : More popular
     */

    /*
    Given
        https://jsonplaceholder.typicode.com/todos/2
    When
        User send GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        "completed" is false
    And
        "userId" is 1
    And
        "title" is "quis ut nam facilis et officia qui"
    And
        header "Via" is "1.1 Vegur"
    And
        header "Server" is "cloudflare"
     */

    @Test
    public void Get08(){
        //Set the Url
        spec.pathParams("first", "todos", "second", 2);

        //Set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        expectedData.put("Status Code", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        System.out.println("Expected data = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //Do assertion
        //By using GSON we are able to convert Json Data which is coming from API to Java Object
        Map<String, Object> actualData = response.as(HashMap.class); //as() method is coming from Gson
        System.out.println("Actual Data = " + actualData);

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("Status Code"), response.getStatusCode());
        assertEquals(expectedData.get("Via"), response.getHeader("Via"));
        assertEquals(expectedData.get("Server"), response.getHeader("Server"));




    }

}
