package restfulbooker.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restfulbooker.models.CreateBookingResponseModel;
import restfulbooker.utils.TestDataGenerator;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static restfulbooker.specs.Specifications.*;

@Owner("EphimSh")
@Feature("Booking")
public class DeleteBooking extends TestBase {
    @Test
    @Story("Delete booking")
    @DisplayName("Create then delete booking by id")
    @Description("Create a booking, delete it, and verify the deletion process.")
    void createBookingWithTestDataThenDelete() {
        String token = step("Getting a token to proceed the operations", () -> getToken());
        CreateBookingResponseModel.Booking testData = TestDataGenerator.generateTestData();
        CreateBookingResponseModel postResponse = step("Make POST request", () ->
                given(requestSpec)
                        .body(testData)
                        .when()
                        .post("/booking")
                        .then()
                        .spec(createBookingWithStatusCode200)
                        .extract().as(CreateBookingResponseModel.class)
        );
        step("id received", () -> {
            assertNotNull(postResponse.getBookingId());
        });
        String id = String.valueOf(postResponse.getBookingId());
        ExtractableResponse deleteResponse = step("Make DELETE request", () ->
                given(requestSpec)
                        .header("Cookie", "token=" + token)
                        .when()
                        .delete("/booking/" + id)
                        .then()
                        .spec(deleteBookingWithStatusCode201)
                        .extract()
        );
        step("Check response body", () -> {
            assertEquals("Created", deleteResponse.body().asString());
        });
    }
}
