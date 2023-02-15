package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Post01 extends HerOkuAppBaseUrl {

    /*
    Given
            https://restful-booker.herokuapp.com/booking

            {
            "firstname": "John",
            "lastname": "Allen",
            "totalprice": 1111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2023-01-01",
                "checkout": "2023-02-01"
            },
            "additionalneeds": "super bowls"
        }

        When
            User send a POST request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response body should be like
            {
            "firstname": "John",
            "lastname": "Allen",
            "totalprice": 1111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2023-01-01",
                "checkout": "2023-02-01"
            },
            "additionalneeds": "super bowls"
        }

     */

    @Test
    public void post01(){
        //Set the Url
        spec.pathParam("first", "booking");

        //Set the expected data
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-01-01");
        bookingDates.put("checkout", "2023-02-01");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "John");
        expectedData.put("lastname", "Allen");
        expectedData.put("totalprice", 1111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingDates);
        expectedData.put("additionalneeds", "super bowls");
        System.out.println("Expected data = " +expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual data = " + actualData);

        assertEquals(expectedData.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingDates.get("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDates.get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));


    }
//@Test
//public void post01(){
//
//    //1.Step: Set the url
//    spec.pathParam("first","booking");
//
//    //2.Step: Set the expected data
//    Map<String, String> expectedBookingdates = new HashMap<>();
//    expectedBookingdates.put("checkin", "2021-09-09");
//    expectedBookingdates.put("checkout", "2021-09-21");
//
//    Map<String, Object> expectedData = new HashMap<>();
//    expectedData.put("firstname", "Selim");
//    expectedData.put("lastname", "Ak");
//    expectedData.put("totalprice", 11111);
//    expectedData.put("depositpaid", true);
//    expectedData.put("expectedBookingdates", expectedBookingdates);
//    expectedData.put("additionalneeds", "Breakfast");
//    System.out.println(expectedData);
//
//    //3.Step: Send the Post request and get the response
//    Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
//    //spec(spec).body() -->These two is pre-requisite, I write them before When()
//    response.prettyPrint();
//
//
//    //4.Step: Do assertion
//    Map<String, Object> actualData = response.as(HashMap.class);
//    System.out.println(actualData);
////        Map<String, Object> actualData =  JsonUtils.convertJsonToJavaObject(response.asString(), HashMap.class);
//
//    assertEquals(expectedData.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));     //actualData.get("booking") is Object, I have to convert it to Map for access get() method
//    assertEquals(expectedData.get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
//    assertEquals(expectedData.get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
//    assertEquals(expectedData.get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
//    assertEquals(expectedBookingdates.get("checkin"), ((Map) ((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
//    assertEquals(expectedBookingdates.get("checkout"), ((Map) ((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));
//
//}


}
