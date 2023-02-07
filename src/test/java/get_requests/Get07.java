package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {
/*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        User send GET request to the URL
    Then
        HTTP Status Code should be 200
        1)Print all ids greater than 190 on the console
           Assert that there are 10 ids greater than 190
        2)Print all userIds less than 5 on the console
           Assert that maximum userId less than 5 is 4
        3)Print all title whose ids are less than 5
           Assert that "delectus aut autem" is one of the titles whose id is less than 5
 */

    @Test
    public void get07(){
        //Set the Url
        spec.pathParam("first", "todos");

        //Set the expected data

        //3)Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        System.out.println(response.prettyPrint());

        //Do Assertion
        response.then().assertThat().statusCode(200);

        //Print all ids greater than 190 on the console
        //           Assert that there are 10 ids greater than 190
        JsonPath json = response.jsonPath();
        List<Integer> idList = json.getList("findAll{it.id>190}.id"); // GROOVY LANGUAGE (comes from Java)
                                                                           // "it" means from the json data which we are working in
                                                                           // "second id" means I want to get just ids from the list
        System.out.println(idList);
        assertEquals(10, idList.size(), "Id list does not have expected size");

        //Print all userIds less than 5 on the console
        //           Assert that maximum userId less than 5 is 4
        List<Integer> userIds = json.getList("findAll{it.userId<5}.userId");
        System.out.println(userIds);
        Collections.sort(userIds);
        assertEquals(4, userIds.get(userIds.size()-1), "maximum userId less than 5 is not 4");

        //Print all title whose ids are less than 5
        //           Assert that "delectus aut autem" is one of the titles whose id is less than 5
        List<String> allTitles = json.getList("findAll{it.id<5}.title");
        System.out.println(allTitles);
        //1.Way:
              //  assertTrue(allTitles.contains("delectus aut autem"));
        //2.Way:
                assertTrue(allTitles.stream().anyMatch(t-> t.equals("delectus aut autem")));

    }
}
