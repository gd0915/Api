package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public class GoRestApiBaseUrl {

    protected RequestSpecification spec;

    @BeforeEach
    public void SetUp(){
        spec = new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public/v1").build();
    }

}
