package test.camel.restletjdbc;

import org.junit.BeforeClass;

import static io.restassured.RestAssured.*;

public class BaseTest {
    @BeforeClass
    public static void init(){
        baseURI = "http://localhost";
        port = 14527;
        basePath = "/rs";
    }
}
