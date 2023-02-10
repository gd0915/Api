package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import test_data.HerOkuAppTestData;
import utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetWithObjectMapper02 extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/2
    When
            User send GET request to the Url
    Then
            Status code should be 200
    And
            Response body is like
            {
                        "firstname": "Josh",
                        "lastname": "Allen",
                        "totalprice": 111,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2018-01-01",
                            "checkout": "2019-01-01"
                        },
                        "additionalneeds": "super bowls"
                    }
     */

    @Test
    public void getWithObjectMapper02(){
        //Set the url
        spec.pathParams("first", "booking", "second", 2824);

        //Set the expected data
        //1st Way:
        String expectedData = "{\n" +
                                "\"firstname\": \"Josh\",\n" +
                                "\"lastname\": \"Allen\",\n" +
                                "\"totalprice\": 111,\n" +
                                "\"depositpaid\": true,\n" +
                                "\"bookingdates\": {\n" +
                                "\"checkin\": \"2018-01-01\",\n" +
                                "\"checkout\": \"2019-01-01\"\n" +
                                "},\n" +
                                "\"additionalneeds\": \"super bowls\"\n" +
                                "}";
        HashMap<String, Object> expectedDataMap = JsonUtil.convertJsonToJava(expectedData, HashMap.class);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        HashMap<String, Object> actualData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("Actual data = " +actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataMap.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedDataMap.get("additionalneeds"), actualData.get("additionalneeds"));

    }

    @Test
    public void getWithObjectMapper03(){
        //Set the url
        spec.pathParams("first", "booking", "second", 2824);

        //Set the expected data
        //2nd Way: Creating a setUp method to convert Json data to String dynamically
        HerOkuAppTestData expect = new HerOkuAppTestData();
        String expectedData = expect.expectedDataInString("Josh", "Allen", 111, true, "2018-01-01", "2019-01-01", "super bowls");
        HashMap<String, Object> expectedDataMap = JsonUtil.convertJsonToJava(expectedData, HashMap.class);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        HashMap<String, Object> actualData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("Actual data = " +actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataMap.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedDataMap.get("additionalneeds"), actualData.get("additionalneeds"));


    }
}
