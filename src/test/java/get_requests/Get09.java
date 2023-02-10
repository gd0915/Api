package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {
    /*
       Given
            https://restful-booker.herokuapp.com/booking/1
        When
            User send a GET request to the URL
        Then
            Response body should be like;
        {
            "firstname": "Susan",
            "lastname": "Jackson",
            "totalprice": 125,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2015-10-18",
                "checkout": "2018-03-24"
            }
        }
     */
    @Test
    public void Get09(){
        //Set the Url
        spec.pathParams("first", "booking", "second", 1);

        //Set the expected data
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2018-10-26");
        bookingDates.put("checkout", "2019-05-26");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Mary");
        expectedData.put("lastname", "Jones");
        expectedData.put("totalprice", 545);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingDates);
        System.out.println("Expected data = " +expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        System.out.println(response.prettyPrint());

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual data = " + actualData);

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        //When we created the map we declared the data type of the values ({checkin=2018-10-26, checkout=2019-05-26}) as "object"
        //So, Data type of that part ({checkin=2018-10-26, checkout=2019-05-26})  is Object
        //Therefore we did explicit type casting (into Map) below
        assertEquals(bookingDates.get("chekin"), ((Map)actualData.get("bookingdates")).get("chekin"));
        assertEquals(bookingDates.get("chekout"), ((Map)actualData.get("bookingdates")).get("chekout"));

    }

}
