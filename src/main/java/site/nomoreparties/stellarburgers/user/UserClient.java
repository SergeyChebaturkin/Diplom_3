package site.nomoreparties.stellarburgers.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.models.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String USER_REGISTER_URL = "api/auth/register";
    private static final String USER_GET_AND_CHANGE_INFO_URL = "api/auth/user";

    @Step("Creating user")
    public Response register(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(USER_REGISTER_URL);
    }

    @Step("Deleting user by token")
    public Response delete(String token) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .when()
                .delete(USER_GET_AND_CHANGE_INFO_URL);
    }
}
