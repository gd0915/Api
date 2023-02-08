package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.internal.mapping.JsonbMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
        User send DELETE request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response body should be like {} (empty json)
     */

    @Test
    public void del01(){
        //Set the Url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
//        Map<String, Object> expectedMap = new HashMap<>();
//        System.out.println(expectedMap);

        //Send the request and get the response
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //GSON
        Map<String, Object> actualMap = response.as(HashMap.class);
        System.out.println(actualMap);

        //Do Assertion
        response.then().assertThat().statusCode(200);
        //assertEquals(expectedMap, actualMap);
        assertTrue(actualMap.size()==0);

    }
}
