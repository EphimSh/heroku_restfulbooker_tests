package restfulbooker.tests;

import io.restassured.http.ContentType;
import restfulbooker.config.WebConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import restfulbooker.models.CreateTokenRequestBodyModel;
import restfulbooker.models.CreateTokenResponseModel;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static restfulbooker.specs.Specifications.createTokenWithStatusCode200;
import static restfulbooker.specs.Specifications.requestSpec;

public class TestBase {

    static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

    @BeforeAll
    static void setup() {
        baseURI = config.getBaseUrl();
    }

    public static String getToken() {
        return given(requestSpec)
                .contentType(ContentType.JSON)
                .body(new CreateTokenRequestBodyModel() {{
                    setUsername(config.getUsername());
                    setPassword(config.getPassword());
                }})
                .when()
                .post("/auth")
                .then()
                .spec(createTokenWithStatusCode200)
                .extract().as(CreateTokenResponseModel.class).getToken();
    }
}
