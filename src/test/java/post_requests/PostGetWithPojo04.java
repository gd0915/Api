package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostGetWithPojo04 extends HerOkuAppBaseUrl {

    /*
    Given
            https://restful-booker.herokuapp.com/booking

            {
                "firstname": "Laura",
                "lastname": "Braun",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2023-03-31",
                    "checkout": "2023-04-28"
                },
                "additionalneeds": "Breakfast and Decaf Coffee"
            }
        When
            User send a POST request to the URL
            User send a GET request to the Url
        Then
            Status Code is 200
        And
            Response body should be like;
            {
                "firstname": "Laura",
                "lastname": "Braun",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2023-03-31",
                    "checkout": "2023-04-28"
                },
                "additionalneeds": "Breakfast and Decaf Coffee"
            }
     */
    @Test
    public void test01(){
        //Set the Url
        spec.pathParam("first", "booking");

        //Set the expected data(request body)
        BookingDatesPojo bookingdates = new BookingDatesPojo("2023-03-31", "2023-04-28");
        BookingPojo requestBody = new BookingPojo("Laura", "Braun", 999, true, bookingdates, "Breakfast and Decaf Coffee");
        System.out.println("Expected data = "+requestBody);

        //Send the request and get the response
        Response response1 = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response1.prettyPrint();

        //Create JsonPath object to get the "bookingid"
        JsonPath json = response1.jsonPath();
        System.out.println(json.getInt("bookingid"));
        Integer bookingId = json.getInt("bookingid");

        //Set the Url for GET request(dynamically
        spec.pathParams("first", "booking", "second", bookingId);

        //Send the GET request and get the response
        Response response2 = given().spec(spec).when().get("/{first}/{second}");
        response2.prettyPrint();

        //Do Assertion
        BookingPojo actualData = response2.as(BookingPojo.class);
        assertEquals(200, response2.getStatusCode());
        assertEquals(requestBody.getFirstname(), actualData.getFirstname());
        assertEquals(requestBody.getLastname(), actualData.getLastname());
        assertEquals(requestBody.getTotalprice(), actualData.getTotalprice());
        assertEquals(requestBody.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(bookingdates.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingdates.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(), actualData.getAdditionalneeds());

    }
}
