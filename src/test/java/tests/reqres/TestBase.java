package tests.reqres;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.*;

public class TestBase {
    public static final String API_KEY = "reqres-free-v1";
    public static RequestSpecification baseSpec;

    @BeforeAll
    static void setUp() {
        baseURI = "https://reqres.in";
        basePath = "/api";

        baseSpec = with()
                .filter(withCustomTemplates())
                .header("x-api-key", API_KEY)
                .log()
                .uri();
    }
}
