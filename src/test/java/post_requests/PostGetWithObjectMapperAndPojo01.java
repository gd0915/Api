package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuAppPostResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostGetWithObjectMapperAndPojo01 extends HerOkuAppBaseUrl {

    /*
    Given
            https://restful-booker.herokuapp.com/booking

                        {
                        "firstname": "Josh",
                        "lastname": "Allen",
                        "totalprice": 111,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2023-03-01",
                            "checkout": "2023-04-01"
                        },
                        "additionalneeds": "Breakfast"
                    }

        When
            User send a POST request to the URL
        And
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response body should be like
            {
            "firstname": "Josh",
            "lastname": "Allen",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2023-03-01",
                "checkout": "2023-04-01"
            },
            "additionalneeds": "Breakfast"
        }
     */

    @Test
    public void test01(){
        //1.Set the URL
        spec.pathParam("first", "booking");

        //2.Set the request body
        BookingDatesPojo bookingDates = new BookingDatesPojo("2023-03-01", "2023-04-01");
        BookingPojo requestBody = new BookingPojo("Josh", "Allen", 111, true, bookingDates, "Breakfast");

        //3.Send the request and get the response
        Response response1 = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response1.prettyPrint();

        //4.Create responseBodyPojo to get bookingid
            ////I CONVERTED POST RESPONSE BODY TO JAVA OBJECT BY USING OBJECT MAPPER
        HerOkuAppPostResponseBodyPojo postResponseBodyInPojo = JsonUtil.convertJsonToJava(response1.asString(), HerOkuAppPostResponseBodyPojo.class);
        System.out.println(postResponseBodyInPojo);

            //FROM postResponseBodyInPojo I GOT THE VALUE of bookingid by using GETTER of bookingid
        Integer bookingId = postResponseBodyInPojo.getBookingid();

        //1.Set the Url for GET request(BY USING bookingid)
        spec.pathParams("first", "booking", "second", bookingId);

        //Note: No need to create expected data because the data which is sent in the POST request will be the expected data

        //2.Send the request and get the response
        Response response2 = given().spec(spec).when().get("/{first}/{second}");
        response2.prettyPrint();

        //3.Do Assertion
            //I CONVERTED GET RESPONSE BODY TO JAVA OBJECT BY USING OBJECT MAPPER
        BookingPojo getResponseBodyInPojo = JsonUtil.convertJsonToJava(response2.asString(), BookingPojo.class);
        System.out.println("Actual Data = "+ getResponseBodyInPojo);

            //BY USING requestbody and getResponseBodyInPojo, I DID ASSERTIONS
        assertEquals(200, response2.statusCode());
        assertEquals(requestBody.getFirstname(), getResponseBodyInPojo.getFirstname());
        assertEquals(requestBody.getLastname(), getResponseBodyInPojo.getLastname());
        assertEquals(requestBody.getTotalprice(), getResponseBodyInPojo.getTotalprice());
        assertEquals(requestBody.getDepositpaid(), getResponseBodyInPojo.getDepositpaid());
        assertEquals(bookingDates.getCheckin(), getResponseBodyInPojo.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), getResponseBodyInPojo.getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(), getResponseBodyInPojo.getAdditionalneeds());

}

































}
