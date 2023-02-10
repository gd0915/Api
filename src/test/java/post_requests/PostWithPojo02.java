package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuAppPostResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostWithPojo02 extends HerOkuAppBaseUrl {
    /*
       Given
            https://restful-booker.herokuapp.com/booking

            {
                "firstname": "Laura",
                "lastname": "Brown",
                "totalprice": 899,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2023-03-13",
                    "checkout": "2023-04-09"
                },
                "additionalneeds": "Breakfast with Holland Gouda Cheese and Black Tea"
            }
        When
            User send a POST request to the URL
        Then
            Status Code is 200
        And
            Response body should be like;
            {
                "firstname": "Laura",
                "lastname": "Brown",
                "totalprice": 899,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2023-03-13",
                    "checkout": "2023-04-09"
                },
                "additionalneeds": "Breakfast with Holland Gouda Cheese and Black Tea"
            }
     */

    @Test
    public void postWithPojo02(){
        //Set the Url
        spec.pathParams("first", "booking");

        //Set request body
        BookingDatesPojo bookingDates = new BookingDatesPojo("2023-03-13", "2023-04-09");
        BookingPojo requestBody = new BookingPojo("Laura", "Brown", 89, true, bookingDates, "Breakfast with Holland Gouda Cheese and Black Tea");

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

        //Do Assertion
        //response.then().assertThat().statusCode(200);
        assertEquals(200, response.getStatusCode());
        HerOkuAppPostResponseBodyPojo actualData = response.as(HerOkuAppPostResponseBodyPojo.class);

        assertEquals(requestBody.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(requestBody.getLastname(), actualData.getBooking().getLastname());
        assertEquals(requestBody.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(requestBody.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());
    }

    @Test
    public void postWithPojo03(){
        //Set the Url
        spec.pathParams("first", "booking");

        //Set request body
        BookingDatesPojo bookingDates = new BookingDatesPojo("2023-03-13", "2023-04-09");
        BookingPojo requestBody = new BookingPojo("Laura", "Brown", 89, true, bookingDates, "Breakfast with Holland Gouda Cheese and Black Tea");

        //Send the request and get the response
        Response response1 = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response1.prettyPrint();

        //Create JsonPath object to get id
        JsonPath json = response1.jsonPath();
        Integer bookingId = json.getInt("bookingid");
        System.out.println(bookingId); // 4414

        //Set the Url for GET Request
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
        assertEquals(bookingDates.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(), actualData.getAdditionalneeds());
    }


}
