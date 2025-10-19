package tests.petstore;

import io.restassured.response.ValidatableResponse;
import models.record.petstore.LoginResponseModel;
import models.record.petstore.PetModel;
import models.record.petstore.PetResponseModel;
import models.record.petstore.TagModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.PetStoreSpec.responseSpec;

public class PetStoreTests extends TestBase {
    @Test
    @DisplayName("GET успешный логин")
    void loginPetStore() {
        TestData testData = new TestData();

        LoginResponseModel response = step("Make request", () ->
            given()
                .spec(baseSpec)
                .queryParam("username", testData.userName)
                .queryParam("password", testData.userPassword)
            .when()
                .get("/user/login")
            .then()
                .spec(responseSpec(200))
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

        PetModel newPet = new PetModel(testData.petId, testData.petName, testData.availableStatus, null);
        TagModel[] tags = new TagModel[] {new TagModel(testData.petTagId, testData.petTag)};
        PetModel petWithTags = new PetModel(testData.petId, null, null, tags);

        step("Make new pet", () -> {
            given()
                    .spec(baseSpec)
                    .body(newPet)
                    .contentType(JSON)
            .when()
                    .post("/pet")
            .then()
                    .spec(responseSpec(200));
        });

        PetResponseModel response = step("Add tag to existing pet", () ->
            given()
                    .spec(baseSpec)
                    .body(petWithTags)
                    .contentType(JSON)
            .when()
                    .put("/pet")
            .then()
                    .spec(responseSpec(200))
                    .extract().as(PetResponseModel.class));

        step("Check response: tag added to created pet ", () -> {
            assertEquals(testData.petId, response.id());
            assertEquals(testData.petTagId, response.tags()[0].id());
            assertEquals(testData.petTag, response.tags()[0].name());
        });
    }

    @Test
    @DisplayName("DELETE Удалить несуществующего питомца")
    void deletePetTest() {
        ValidatableResponse response = step("Make request", () ->
            given()
                .spec(baseSpec)
            .when()
                .delete("/pet/" + TestData.notExistPetId)
            .then());

        step("Check status code 404", () ->
            response.statusCode(404));
    }
}
