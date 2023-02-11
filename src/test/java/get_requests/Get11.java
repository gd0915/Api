package get_requests;

import base_urls.GoRestApiBaseUrl;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Get11 extends GoRestApiBaseUrl {

    /*
    Given
         https://gorest.co.in/public/v1/users
    When
        User send a GET request to the URL
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should be 10
    And
        The number of active users should be 5
    And
        "Giri Patel", "Ekaaksh Pilla" and "Vyas Ganaka" are among the users
    And
        The female users are more than male users
     */

    @Test
    public void get11(){
        //Set the Url
        spec.pathParam("first", "users");

        //Set the expected Data
        //JsonPath is the best option to use in this test because the Json data structure is very detailed

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Create JsonPath object to navigate inside Json data
        JsonPath json = response.jsonPath();

        //Do Assertions
        //1st Way:
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit", equalTo(10),
                "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id", hasSize(10),
                        "data.name", hasItems("Anish Dhawan","Lal Panicker","Vyas Ganaka"));

        //The number of active users should be 5
        List<String> statusActiveList = json.getList("data.findAll{it.status==\"active\"}.status");
        System.out.println("Number of active status users = " + statusActiveList.size());

        //The female users are more than male users
        List<String> allFemales = json.getList("data.findAll{it.gender==\"female\"}.gender");
        //System.out.println("The number of females = "+allFemales.size());
        List<String> allGenders = json.getList("data.findAll{it.gender}.gender");
        //System.out.println("The number of all genders = "+allGenders.size());
        System.out.println("The number of males = "+(allGenders.size()-allFemales.size()));
        assertTrue(allFemales.size()>(allGenders.size()-allFemales.size()));

        //2nd Way:using JsonPath object
        assertEquals(10, json.getInt("meta.pagination.limit"));
        assertEquals("https://gorest.co.in/public/v1/users?page=1", json.getString("meta.pagination.links.current"));
        assertEquals(10, json.getList("data.id").size());
        assertTrue(json.getList("data.status").contains("active"));

        List<String> expectedNameList = Arrays.asList("Anish Dhawan","Lal Panicker","Vyas Ganaka");
        assertTrue(json.getList("data.name").containsAll(expectedNameList)); //containsAll() method works with collection


    }
























}
