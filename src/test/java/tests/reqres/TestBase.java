package tests.reqres;

import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class TestBase {
    @BeforeAll
    static void setUp() {
        baseURI = "https://reqres.in";
        basePath = "/api";
    }
}
