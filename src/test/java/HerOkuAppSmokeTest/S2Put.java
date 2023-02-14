package HerOkuAppSmokeTest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuAppPostResponseBodyPojo;
import utils.JsonUtil;

import static HerOkuAppSmokeTest.S1Post.bookingId;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

public class S2Put extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/11819

            {
                "firstname" : "James",
                "lastname" : "Brown",
                "totalprice" : 500,
                "depositpaid" : false,
                "bookingdates" : {
                    "checkin" : "2023-03-01",
                    "checkout" : "2023-03-15"
                },
                "additionalneeds" : "Breakfast"
            }'
    When
        User send PUT request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response body should be like
            {
                "bookingid": 11819,
                "booking": {
                    "firstname": "James",
                    "lastname": "Brown",
                    "totalprice": 500,
                    "depositpaid": false,
                    "bookingdates": {
                       "checkin" : "2023-03-01",
                       "checkout" : "2023-03-15"
                    },
                    "additionalneeds": "Breakfast"
                }
     */

    @Test
    public void put() {
        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2023-03-01", "2023-03-15");
        BookingPojo expectedData = new BookingPojo("James", "Brown", 500, false, bookingDatesPojo, "Breakfast");

        //Send the request and get the response
        Response response = given().
                spec(spec).
                contentType(ContentType.JSON).
                headers("Cookie", "token=" + generateToken()).
                body(expectedData).
                when().
                put("/{first}/{second}");

        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = JsonUtil.convertJsonToJava(response.asString(), BookingPojo.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());


    }


}
