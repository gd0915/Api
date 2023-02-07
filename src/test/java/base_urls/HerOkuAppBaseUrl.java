package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public class HerOkuAppBaseUrl {

    //Create object in RequestSpecification data type
    //RequestSpecification is an interface
    //Interfaces can be a data type, but it is not possible to create an object from an interface
    protected RequestSpecification spec;

    //@BeforeEach ==>> JunitJupiter annotation/JunitJupiter dependency new feature
    //In Junit we use @Before method
    //It means the method will be executed before every test method
    @BeforeEach
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

    }

}
