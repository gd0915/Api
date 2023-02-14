package HerOkuAppSmokeTest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuAppPostResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class S1Post extends HerOkuAppBaseUrl {
    /*
     Type an automation smoke test by using "https://restful-booker.herokuapp.com/apidoc/index.html" documentation.
     Create a booking then update, read and delete the booking you created.
     */
    /*
    Given
            https://restful-booker.herokuapp.com/booking

            {
                "firstname" : "Jim",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Breakfast"
            }'
    When
        User send POST request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response body should be like
            {
                "bookingid": 11819,
                "booking": {
                    "firstname": "Jim",
                    "lastname": "Brown",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Breakfast"
                }
            }
     */

    static int bookingId;
    @Test
    @Order(1)
    public void post01(){
        //Set the Url
        spec.pathParam("first", "booking");

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Jim", "Brown", 111, true, bookingDatesPojo, "Breakfast");

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do Assertion
        HerOkuAppPostResponseBodyPojo actualData = JsonUtil.convertJsonToJava(response.asString(), HerOkuAppPostResponseBodyPojo.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        //Get the bookingid
        // 1st Way: using JsonPath object
//        JsonPath json = response.jsonPath();
//        bookingId = json.getInt("bookingid");
        //2nd Way:
        bookingId = actualData.getBookingid();
    }

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
    @Order(2)
    public void put01() {
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

    /*
       Given
                https://restful-booker.herokuapp.com/booking/:id
        When
            User send GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response body should be like
                    {
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
    @Order(3)
    public void get01(){
        //Set the Url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2023-03-01", "2023-03-15");
        BookingPojo expectedData = new BookingPojo("James", "Brown", 500, false, bookingDatesPojo, "Breakfast");

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
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

    /*
     Given
           https://restful-booker.herokuapp.com/booking/:id
        When
            User send DELETE request to the URL
        Then
            HTTP Status Code should be 201
        And
            Response body should be "Created"
     */

    @Test
    @Order(4)
    public void delete01(){
        //Set the Url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        String expectedData = "Created";

        //Send the request and get the response
        Response response = given().spec(spec).headers("Cookie", "token="+generateToken()).when().delete("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData, response.asString());
    }

    /*
    Given
            https://restful-booker.herokuapp.com/booking/:id
        When
            User send GET request to the URL
        Then
            HTTP Status Code should be 404
        And
            Response body should be like {}
     */

    @Test
    @Order(5)
    public void get02(){
        //Set the Url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        String expectedData = "Not Found";

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        assertEquals(404, response.getStatusCode());
        assertEquals(expectedData, response.asString());

    }


}
