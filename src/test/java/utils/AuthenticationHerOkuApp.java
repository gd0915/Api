package utils;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationHerOkuApp {

    public static void main(String[] args) {
        //generateToken();
        System.out.println(generateToken());
    }

    public static String generateToken(){
        String url = "https://restful-booker.herokuapp.com/auth";
        Map<String, String> postBody = new HashMap<>();
        postBody.put("username","admin");
        postBody.put("password","password123");
        Response response = given().contentType(ContentType.JSON).body(postBody).when().post(url);
        //response.prettyPrint();
        return response.jsonPath().getString("token");
    }
    //We can also create this method with parameters(username and password, but  we need to type username and password every time in test class.

}
