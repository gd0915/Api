package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetWithPojo01 extends HerOkuAppBaseUrl {

    /*
       Given
            https://restful-booker.herokuapp.com/booking/2
        When
            User send a GET request to the URL
        Then
            Status Code is 200
        And
            Response body should be like;
            {
                "firstname": "Josh",
                "lastname": "Allen",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "midnight snack"
            }
     */

    @Test
    public void GetWithPojo01(){
        //Set the Url
        spec.pathParams("first", "booking", "second", 22);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Josh", "Allen", 111, true, bookingDatesPojo,"midnight snack" );
        System.out.println("Expected data = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("Actual data = "+actualData);

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

    }
}
