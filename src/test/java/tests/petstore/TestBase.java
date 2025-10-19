package tests.petstore;

import helpers.CustomAllureListener;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.*;

public class TestBase {
    public static final String API_KEY = "special-key";
    public static RequestSpecification baseSpec;
    @BeforeAll
    static void setUp() {
        baseURI = "https://petstore.swagger.io";
        basePath = "/v2";

        baseSpec = with()
                .filter(withCustomTemplates())
                .header("x-api-key", API_KEY)
                .log()
                .uri();
    }
}