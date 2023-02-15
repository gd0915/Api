package get_requests;

import base_urls.GMIBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojos.GMICountryPojo;
import pojos.GMICustomerPojo;
import pojos.GMIUserPojo;
import utils.JsonUtil;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.AuthenticationGMIBank.generateToken;

public class Get13 extends GMIBankBaseUrl {
    /*
        Given
	       https://www.gmibank.com/api/tp-customers/110452
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
                                        "id": 110452,
                                        "firstName": "Jasmine",
                                        "lastName": "Stehr",
                                        "middleInitial": "V",
                                        "email": "marni.zboncak@yahoo.com",
                                        "mobilePhoneNumber": "463-609-2097",
                                        "phoneNumber": "1-112-497-0270",
                                        "zipCode": "16525",
                                        "address": "14387 Al Ridge5343 Bert Burgs",
                                        "city": "Waltermouth",
                                        "ssn": "761-59-2911",
                                        "createDate": "2021-11-28T21:00:00Z",
                                        "zelleEnrolled": false,
                                        "country": {
                                            "id": 3,
                                            "name": "USA",
                                            "states": null
                                        },
                                        "state": "California",
                                        "user": {
                                            "id": 110016,
                                            "login": "leopoldo.reinger",
                                            "firstName": "Jasmine",
                                            "lastName": "Stehr",
                                            "email": "marni.zboncak@yahoo.com",
                                            "activated": true,
                                            "langKey": "en",
                                            "imageUrl": null,
                                            "resetDate": null
                                        },
                                        "accounts": []
                                    }
     */
    /**
     * GMI Bank Basic Authentication
      Username = john_doe
      Password = John.123
     */
    @Test
    public void get13(){
        //Set the Url
        spec.pathParams("first", "tp-customers", "second", 110452);

        //Set the expected data
        GMICountryPojo countryPojo = new GMICountryPojo(3, "USA", null);
        GMIUserPojo userPojo = new GMIUserPojo(110016,"leopoldo.reinger","Jasmine", "Stehr","marni.zboncak@yahoo.com", true,"en", null, null);
        ArrayList<Object> accounts = new ArrayList<>();
        GMICustomerPojo expectedData = new GMICustomerPojo(110452, "Jasmine", "Stehr", "V", "marni.zboncak@yahoo.com","463-609-2097","1-112-497-0270", "16525", "14387 Al Ridge5343 Bert Burgs", "Waltermouth", "761-59-2911", "2021-11-28T21:00:00Z", false, countryPojo, "California", userPojo, accounts);
        System.out.println("expectedData = "+expectedData);
        //Send the request and get the response
        Response response = given().
                                spec(spec).
                                headers("Authorization", "Bearer "+generateToken()).
                                when().
                                get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        GMICustomerPojo actualData = JsonUtil.convertJsonToJava(response.asString(), GMICustomerPojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getId(), actualData.getId());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getMiddleInitial(), actualData.getMiddleInitial());
        assertEquals(expectedData.getEmail(), actualData.getEmail());
        assertEquals(expectedData.getMobilePhoneNumber(), actualData.getMobilePhoneNumber());
        assertEquals(expectedData.getPhoneNumber(), actualData.getPhoneNumber());
        assertEquals(expectedData.getZipCode(), actualData.getZipCode());
        assertEquals(expectedData.getAddress(), actualData.getAddress());
        assertEquals(expectedData.getCity(), actualData.getCity());
        assertEquals(expectedData.getSsn(), actualData.getSsn());
        assertEquals(expectedData.getCreateDate(), actualData.getCreateDate());
        assertEquals(expectedData.isZelleEnrolled(), actualData.isZelleEnrolled());
        assertEquals(countryPojo.getId(), actualData.getCountry().getId());
        assertEquals(countryPojo.getName(), actualData.getCountry().getName());
        assertEquals(countryPojo.getStates(), actualData.getCountry().getStates());
        assertEquals(expectedData.getState(), actualData.getState());
        assertEquals(userPojo.getId(), actualData.getUser().getId());
        assertEquals(userPojo.getLogin(), actualData.getUser().getLogin());
        assertEquals(userPojo.getFirstName(), actualData.getUser().getFirstName());
        assertEquals(userPojo.getLastName(), actualData.getUser().getLastName());
        assertEquals(userPojo.getEmail(), actualData.getUser().getEmail());
        assertEquals(userPojo.isActivated(), actualData.getUser().isActivated());
        assertEquals(userPojo.getLangKey(), actualData.getUser().getLangKey());
        assertEquals(userPojo.getImageUrl(), actualData.getUser().getImageUrl());
        assertEquals(userPojo.getResetDate(), actualData.getUser().getResetDate());
        assertEquals(expectedData.getAccounts(), actualData.getAccounts());

    }

}
