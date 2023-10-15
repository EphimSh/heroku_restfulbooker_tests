package restfulbooker.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restfulbooker.helpers.annotations.BookingPartialUpdate;
import restfulbooker.helpers.annotations.BookingUpdate;
import restfulbooker.helpers.annotations.PositiveTest;
import restfulbooker.models.BookingResponseModel;
import restfulbooker.models.CreateBookingResponseModel;
import restfulbooker.utils.TestDataGenerator;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static restfulbooker.specs.Specifications.*;

public class UpdateBooking extends TestBase {
    @Test
    @PositiveTest
    @BookingUpdate
    @Severity(SeverityLevel.MINOR)
    @Story("Update a booking")
    @DisplayName("Create then update booking by id")
    @Description("Create a booking, then update it using PUT request and verify the changes.")
    void createBookingWithTestDataThenUpdateIt() {
        String token = step("Getting a token to proceed the operations", () -> getToken());
        CreateBookingResponseModel.Booking testData = TestDataGenerator.generateTestDataWithProperties();
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
        CreateBookingResponseModel.Booking updateBooking = TestDataGenerator.generateTestDataPutRequest();
        BookingResponseModel putResponse = step("Make PUT request", () ->
                given(requestSpec)
                        .header("Cookie", "token=" + token)
                        .body(updateBooking)
                        .when()
                        .put("/booking/" + id)
                        .then()
                        .spec(updateBookingWithStatusCode200)
                        .extract().as(BookingResponseModel.class)
        );
        step("Check first name", () -> {
            assertFalse(postResponse.getBooking().getFirstName().equals(putResponse.getFirstName()));
        });
    }

    @Test
    @PositiveTest
    @BookingPartialUpdate
    @Severity(SeverityLevel.MINOR)
    @Story("Partial update a booking")
    @DisplayName("Create then partial update booking by id")
    @Description("Create a booking, partially update it, and verify the changes.")
    void createBookingWithTestDataThenPatchIt() {
        String token = step("Getting a token to proceed the operations", () -> getToken());
        CreateBookingResponseModel.Booking testData = TestDataGenerator.generateTestDataWithProperties();
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
        testData.setFirstName("James");
        testData.setLastName("Brownie");
        BookingResponseModel patchResponse = step("Make PATCH request", () ->
                given(requestSpec)
                        .header("Cookie", "token=" + token)
                        .body(testData)
                        .when()
                        .patch("/booking/" + id)
                        .then()
                        .spec(partialUpdateBookingWithStatusCode200)
                        .extract().as(BookingResponseModel.class)
        );
        step("Check first name and last name", () -> {
            assertNotEquals(postResponse.getBooking().getFirstName(), patchResponse.getFirstName());
            assertNotEquals(postResponse.getBooking().getLastName(), patchResponse.getLastName());
        });
    }
}
