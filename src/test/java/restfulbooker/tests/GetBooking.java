package restfulbooker.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restfulbooker.helpers.annotations.BookingGet;
import restfulbooker.helpers.annotations.NegativeTest;
import restfulbooker.helpers.annotations.PositiveTest;
import restfulbooker.models.BookingResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static restfulbooker.specs.Specifications.*;
import static restfulbooker.utils.RandomUtils.faker;

@Owner("EphimSh")
@Feature("Booking")
public class GetBooking extends TestBase {
    @Test
    @BookingGet
    @PositiveTest
    @Severity(SeverityLevel.MINOR)
    @Story("Get booking")
    @Description("Test the GET /booking/{id} endpoint randomly; to retrieve booking details.")
    @DisplayName("Get booking data by random id; from 10 - 100")
    void getRandomBooking() {
        BookingResponseModel response = step("Make request", () ->
                given(getBookingRequestSpec)
                        .when()
                        .get("booking/" + faker.number().numberBetween(10, 100))
                        .then()
                        .spec(getBookingWithStatusCode200)
                        .extract().as(BookingResponseModel.class));
        step("Assert that first name is not empty", () -> {
            assertFalse(response.getFirstName().isEmpty());
        });
    }

    @Test
    @BookingGet
    @NegativeTest
    @Severity(SeverityLevel.MINOR)
    @Story("Get booking")
    @DisplayName("Get booking data with invalid ID")
    @Description("Attempt to retrieve booking details with an invalid ID. Verify that the API returns a 404 Not Found response.")
    void getBookingByInvalidId() {
        String response = step("Make request", () ->
                given(getBookingRequestSpec)
                        .when()
                        .get("booking/" + "invalid_id")
                        .then()
                        .spec(getBookingWithStatusCode404)
                        .extract().asString());
        step("Check server response is 404", () -> {
            assertEquals("Not Found", response);
        });
    }
}
