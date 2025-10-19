package tests.petstore;

import helpers.CustomAllureListener;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class TestBase {
    public final String API_KEY = "special-key";

    @BeforeAll
    static void setUp() {
        baseURI = "https://petstore.swagger.io";
        basePath = "/v2";
    }
}