package restfulbooker.tests;

import io.qameta.allure.*;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restfulbooker.helpers.annotations.BookingDelete;
import restfulbooker.helpers.annotations.NegativeTest;
import restfulbooker.helpers.annotations.PositiveTest;
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
    @PositiveTest
    @BookingDelete
    @Severity(SeverityLevel.MINOR)
    @Story("Delete booking")
    @DisplayName("Create then delete booking by id")
    @Description("Create a booking, delete it, and verify the deletion process.")
    void createBookingThenDelete() {
//todo remove try-catch block
        try {
            String token = step("Getting a token to proceed the operations", TestBase::getToken);
            CreateBookingResponseModel.Booking testData = TestDataGenerator.generateTestDataWithProperties();
            CreateBookingResponseModel postResponse = step("Make POST request", () ->
                    given(requestSpec)
                            .body(testData)
                            .when()
                            .post("/booking")
                            .then()
                            .spec(createBookingWithStatusCode200)
                            .extract().as(CreateBookingResponseModel.class));

            String id = String.valueOf(postResponse.getBookingId());
            ExtractableResponse<Response> deleteResponse = step("Make DELETE request", () ->
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @NegativeTest
    @BookingDelete
    @Severity(SeverityLevel.MINOR)
    @Story("Delete booking")
    @DisplayName("Failed booking delete")
    @Description("Attempt to delete booking with bad token")
    void createBookingThenDeleteWithInvalidToken() {

        try {
            String token = "wrongtoken";
            CreateBookingResponseModel.Booking testData = TestDataGenerator.generateTestDataWithProperties();
            CreateBookingResponseModel postResponse = step("Make POST request", () ->
                    given(requestSpec)
                            .body(testData)
                            .when()
                            .post("/booking")
                            .then()
                            .spec(createBookingWithStatusCode200)
                            .extract().as(CreateBookingResponseModel.class));

            String id = String.valueOf(postResponse.getBookingId());
            ExtractableResponse<Response> deleteResponse = step("Make DELETE request", () ->
                    given(requestSpec)
                            .header("Cookie", "token=" + token)
                            .when()
                            .delete("/booking/" + id)
                            .then()
                            .spec(deleteBookingWithStatusCode403)
                            .extract()
            );
            step("Check response body", () -> {
                assertEquals("Forbidden", deleteResponse.body().asString());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
