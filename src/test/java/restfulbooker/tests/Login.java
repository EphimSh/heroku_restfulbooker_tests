package restfulbooker.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restfulbooker.models.CreateTokenRequestBodyModel;
import restfulbooker.models.CreateTokenResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static restfulbooker.specs.Specifications.*;

@Owner("EphimSh")
@Feature("Getting token")
@Story("Authentication")
public class Login extends TestBase {


    @Test
    @Description("Successful retrieval of authentication token")
    @DisplayName("Successful Get Token")
    void successfulGetToken() {
        CreateTokenResponseModel response = step("Make request to GET token", () ->
                given(requestSpec)
                        .contentType(ContentType.JSON)
                        .body(new CreateTokenRequestBodyModel() {{
                            setUsername(config.getUsername());
                            setPassword(config.getPassword());
                        }})
                        .when()
                        .post("/auth")
                        .then()
                        .spec(createTokenWithStatusCode200)
                        .extract().as(CreateTokenResponseModel.class));
        step("Check token length", () -> {
            assertEquals(15, response.getToken().length());
        });
    }

    @Test
    @Description("Attempt to retrieve authentication token with empty username")
    @DisplayName("Empty Username Get Token")
    void emptyUsernameGetToken() {
        CreateTokenResponseModel response = step("Make request to GET token", () ->
                given(requestSpec)
                        .contentType(ContentType.JSON)
                        .body(new CreateTokenRequestBodyModel() {{
                            setUsername("");
                            setPassword(config.getPassword());
                        }})
                        .when()
                        .post("/auth")
                        .then()
                        .spec(createTokenWithBadCredentials)
                        .extract().as(CreateTokenResponseModel.class));
        step("Check reason message", () -> {
            assertEquals("Bad credentials", response.getReason());
        });
    }

    @Test
    @Description("Attempt to retrieve authentication token with incorrect content type")
    @DisplayName("Wrong Content Type Get Token")
    void wrongContentTypeGetToken() {
        CreateTokenResponseModel response = step("Make request to GET token", () ->
                given(requestSpec)
                        .contentType("text/plain")
                        .body(
                                "username:" + config.getUsername() + "\n" + "password:" + config.getPassword())
                        .when()
                        .post("/auth")
                        .then()
                        .spec(createTokenWithBadCredentials)
                        .extract().as(CreateTokenResponseModel.class));
        step("Check reason message", () -> {
            assertEquals("Bad credentials", response.getReason());
        });
    }
}
