package tests.petstore;

import models.record.petstore.LoginResponseModel;
import models.record.petstore.PetBodyModel;
import models.record.petstore.PetResponseBodyModel;
import models.record.petstore.TagBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PetStoreTests extends TestBase {
    @Test
    @DisplayName("GET успешный логин")
    void loginPetStore() {
        TestData testData = new TestData();

        LoginResponseModel response = step("Make request", () ->
            given()
                .filter(withCustomTemplates())
                .header("x-api-key", API_KEY)
                .queryParam("username", testData.userName)
                .queryParam("password", testData.userPassword)
                .log()
                .uri()
            .when()
                .get("/user/login")
            .then()
                .statusCode(200)
                .extract().as(LoginResponseModel.class));

        step("Check response: session created", () -> {
            assertTrue(response.message().contains("logged in user session"));
            assertTrue(response.message().matches(".*\\d+.*"));
        });
    }

    @Test
    @DisplayName("PUT Обновить теги питомца")
    void renewPetTagsTest() {
        TestData testData = new TestData();

        PetBodyModel newPet = new PetBodyModel(testData.petId, testData.petName, testData.availableStatus, null);
        TagBodyModel[] tags = new TagBodyModel[] {new TagBodyModel(testData.petTagId, testData.petTag)};
        PetBodyModel petWithTags = new PetBodyModel(testData.petId, null, null, tags);

        step("Make new pet", () -> {
            given()
                    .body(newPet)
                    .header("x-api-key", API_KEY)
                    .contentType(JSON)
            .when()
                    .post("/pet")
            .then()
                    .log().body()
                    .statusCode(200);
        });

        PetResponseBodyModel response = step("Add tag to existing pet", () ->
            given()
                    .filter(withCustomTemplates())
                    .body(petWithTags)
                    .header("x-api-key", API_KEY)
                    .contentType(JSON)
                    .log().uri()
            .when()
                    .put("/pet")
            .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .extract().as(PetResponseBodyModel.class));

        step("Check response: tag added to created pet ", () -> {
            assertEquals(response.id(), testData.petId);
            assertEquals(response.tags()[0].id(), testData.petTagId);
            assertEquals(response.tags()[0].name(), testData.petTag);
        });
    }

    @Test
    @DisplayName("DELETE Удалить несуществующего питомца")
    void deletePetTest() {
        given()
                .filter(withCustomTemplates())
                .header("x-api-key", API_KEY)
                .log()
                .uri()
        .when()
                .delete("/pet/" + TestData.notExistPetId)
        .then()
                .statusCode(404);
    }
}
