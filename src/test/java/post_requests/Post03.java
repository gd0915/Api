package post_requests;

import base_urls.GMIBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.GMICountryPostResponsePojo;
import pojos.GMIStatePojo;
import utils.JsonUtil;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.AuthenticationGMIBank.generateToken;

public class Post03 extends GMIBankBaseUrl {
     /*
         Given
            https://www.gmibank.com/api/tp-countries
            {
              "name": "USA",
              "states": [
                {
                  "id": 12,
                  "name": "California",
                  "tpcountry": null
                }
              ]
            }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 201
 		And
 		    Response body is like {
                                    "id": 174451,
                                    "name": "USA",
                                    "states": [
                                        {
                                            "id": 12,
                                            "name": "California",
                                            "tpcountry": null
                                        }
                                    ]
                                }
     */
    @Test
    public void post03(){
        //Set the Url
        spec.pathParams("first", "tp-countries");

        //Set the expected data
        GMIStatePojo statePojo = new GMIStatePojo(12, "California", null);
        ArrayList<GMIStatePojo> states = new ArrayList<>();
        states.add(statePojo);
        GMICountryPostResponsePojo expectedData = new GMICountryPostResponsePojo("USA",states);
        System.out.println("expectedData = "+ expectedData);

        //Send the request and get the response
        Response response = given().
                            spec(spec).
                            contentType(ContentType.JSON).
                            headers("Authorization", "Bearer "+generateToken()).
                            body(expectedData).
                            when().
                            post("/{first}");
        response.prettyPrint();

        //Do Assertion
        GMICountryPostResponsePojo actualData = JsonUtil.convertJsonToJava(response.asString(), GMICountryPostResponsePojo.class);
        System.out.println("actualData = "+ actualData);

        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(statePojo.getId(), actualData.getStates().get(0).getId());
        assertEquals(statePojo.getName(), actualData.getStates().get(0).getName());
        assertEquals(statePojo.getTpcountry(), actualData.getStates().get(0).getTpcountry());

    }
}
