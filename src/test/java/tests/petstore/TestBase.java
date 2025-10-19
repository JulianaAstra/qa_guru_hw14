package tests.petstore;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public class TestBase {
    @BeforeAll
    static void setUp() {
        baseURI = "https://petstore.swagger.io";
        basePath = "/v2";
    }
}