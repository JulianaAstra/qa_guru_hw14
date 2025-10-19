package tests.reqres;

import models.pojo.reqres.UserBodyModel;
import models.pojo.reqres.UserResponseModel;
import models.pojo.reqres.UsersResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.ReqresSpec.creationResponseSpec;
import static specs.ReqresSpec.successResponseSpec;

public class ReqresTests extends TestBase {
    @Test
    @DisplayName("GET Получить список всех пользователей")
    void getUsersTest() {
        UsersResponseModel response = step("Make response to get list of users", () ->
            given()
                    .spec(baseSpec)
                    .queryParam("page", 1)
                    .queryParam("per_page", 12)
            .when()
                    .get("/users")
            .then()
                    .spec(successResponseSpec)
                    .extract().as(UsersResponseModel.class));

        step("Check response pagination parameters", () -> {
            assertEquals(1, response.getPage());
            assertEquals(12, response.getPerPage());
            assertEquals(12, response.getTotal());
            assertEquals(1, response.getTotalPages());
        });
    }

    @Test
    @DisplayName("POST Добавить нового пользователя")
    void addNewUser() {
        TestData testData = new TestData();
        UserBodyModel user = new UserBodyModel();
        user.setName(testData.userName);
        user.setJob(testData.job);

        UserResponseModel response = step("Make response: add new user", () ->
            given()
                .spec(baseSpec)
                .contentType(JSON)
                .body(user)
            .when()
                .post("/users")
            .then()
                .spec(creationResponseSpec)
                .extract().as(UserResponseModel.class));

        step("Check response user data", () -> {
            assertEquals(testData.userName, response.getName());
            assertEquals(testData.job, response.getJob());
        });
    }
}
