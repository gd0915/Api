package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {

     /*
        Given
            https://restful-booker.herokuapp.com/booking/5
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type "application/json"
        And
            Response body should be like;
            {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 573,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2020-10-23",
                "checkout": "2021-11-25"
            },
            "additionalneeds": "Breakfast"
}
      */

    @Test
    public void Get06(){
        //Set the URL
        spec.pathParams("first", "booking", "second", 5);

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        System.out.println(response.prettyPrint());

        //Do Assertion

        //1.Way:
//        response.
//                then().
//                assertThat().
//                statusCode(200).
//                contentType(ContentType.JSON).
//                body("firstname", equalTo("Sally"),
//                        "lastname", equalTo("Brown"),
//                        "totalprice", equalTo(573),
//                        "depositpaid", equalTo(true),
//                        "bookingdates.checkin", equalTo("2020-10-23"),
//                        "bookingdates.checkout", equalTo("2021-11-25"),
//                        "additionalneeds", equalTo("Breakfast"));

        //2.Way: Use JsonPath. JsonPath is a class, and it has many useful methods to navigate inside the Json Data.
//        response.
//                then().
//                assertThat().
//                statusCode(200).
//                contentType(ContentType.JSON);
//
//        //Create JsonPath object from response object
        JsonPath json = response.jsonPath();//using response object to create json object
//                                            //json object will have everything from response object
//
//        assertEquals("Sally", json.getString("firstname"), "First name is not matching");
//        assertEquals("Brown", json.getString("lastname"), "Lastname is not matching");
//        assertEquals(573, json.getInt("totalprice"), "Total price is not matching");
//        assertEquals(true, json.getBoolean("depositpaid"), "Deposit paid is not matching");
//        assertEquals("2020-10-23", json.getString("bookingdates.checkin"), "Checkin date is not matching");
//        assertEquals("2021-11-25", json.getString("bookingdates.checkout"), "Checkout date is not matching");
//        assertEquals("Breakfast", json.getString("additionalneeds"), "Additionalneeds is not matching");

        //3.Way: Soft Assertion
        //i) Create SoftAssert Object
        SoftAssert softAssert = new SoftAssert();

        //ii) By using softAssert object do assertion
        softAssert.assertEquals(json.getString("firstname"), "Mary", "First name is not matching");
        softAssert.assertEquals(json.getString("lastname"), "Jackson", "Last name is not matching");
        softAssert.assertEquals(json.getInt("totalprice"), 592, "Total price is not matching");
        softAssert.assertEquals(json.getBoolean("depositpaid"), false, "Deposit paid is not matching");
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2022-09-24", "Checkin date is not matching");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2023-02-05", "Checkout date is not matching");
       // softAssert.assertEquals(json.getString("additionalneeds"), "Breakfast", "Additionalneeds is not matchin");

        //iii) Do not forget use assertAll(). If you do not use assertAll() you will get green everytime, but it is not meaningful
        softAssert.assertAll();
    }

}
