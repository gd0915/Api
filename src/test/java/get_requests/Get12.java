package get_requests;

import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get12 extends DummyApiBaseUrl {
    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends GET request to the Url
    Then
        Status code should be 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
       The greatest age is 66
    And
       The name of the lowest ages person is "[]"
    And
       Total salary of all employees is 6,644,770

     */
    @Test
    public void get12(){
        //Set the url
        spec.pathParams("first", "employees");

        //Set the expected data


        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                body("data", hasSize(24),
                        "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));

        //The greatest age is 66
        JsonPath json = response.jsonPath();
        List<Integer> ages = json.getList("data.employee_age");
        System.out.println("ages = "+ages);
        Collections.sort(ages);
        System.out.println("ages = "+ages);
        System.out.println("The greatest age = "+ ages.get(ages.size() - 1));
        assertEquals(66, (int)ages.get(ages.size() - 1));

        //The name of the lowest ages person is "[]"
        //int lowestAge = ages.get(0);
        //List<String> namesOfLowestAge = json.getList("data.findAll{it.employee_age==19}.employee_name");//There is only one, so I can put it into a String container
        //String namesOfLowestAge = json.getString("data.findAll{it.employee_age==19}.employee_name");//I made the code more dynamic using concatenation
        String namesOfLowestAge = json.getString("data.findAll{it.employee_age=="+ages.get(0)+"}.employee_name");
        System.out.println("Name of the lowest age = " + namesOfLowestAge);
        assertEquals("[Tatyana Fitzpatrick]", namesOfLowestAge);

        //Total salary of all employees is 6,644,770
        //1st Way
        List<Integer> salaries = json.getList("data.employee_salary");
        System.out.println("Salaries = "+salaries);
        int sumOfSalaries = 0;
        for(Integer w : salaries){
            sumOfSalaries += w;
        }
        System.out.println("Sum of salaries = "+sumOfSalaries);

        //2nd Way: using Lambda
        int sumOfSalariesLambda = salaries.stream().reduce(0, (t,u)-> (t+u));
        int sumOfSalariesLambda2 = salaries.stream().reduce(0, Math::addExact);

        assertEquals(6644770, sumOfSalaries);
        assertEquals(6644770, sumOfSalariesLambda);
        assertEquals(6644770, sumOfSalariesLambda2);

    }






}
