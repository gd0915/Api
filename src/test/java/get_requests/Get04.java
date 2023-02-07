package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        User sendGET request to the URL
    And
        Accept Type is "application/json"
    Then
        HTTP Status Code should be 200
    And
        Response format should be "application/json"
    And
        There should be 200 Todos
    And
        "title" is "quis ut nam facilis et officia qui"
    And
        2, 7 and 9 should be among the "userId"s
     */
     @Test
    public void get04(){
         //Set the URL
         spec.pathParam("first", "todos");

         //Set the expected data

         //Send the request and get the response
         Response response = given().spec(spec).accept(ContentType.JSON).when().get("/{first}");
         System.out.println(response.prettyPrint());

         //Do Assertion
         response.then().
                 assertThat().
                 statusCode(200).
                 contentType(ContentType.JSON).
                 body("id", hasSize(200),
                         "title", hasItem("quis ut nam facilis et officia qui"),
                         "userId", hasItems(2, 7, 9));

     }

}
