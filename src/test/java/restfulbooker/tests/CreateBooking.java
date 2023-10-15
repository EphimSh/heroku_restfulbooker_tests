package restfulbooker.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restfulbooker.helpers.annotations.BookingCreate;
import restfulbooker.helpers.annotations.NegativeTest;
import restfulbooker.helpers.annotations.PositiveTest;
import restfulbooker.models.CreateBookingResponseModel;
import restfulbooker.utils.TestDataGenerator;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static restfulbooker.specs.Specifications.*;
import static restfulbooker.utils.RandomUtils.randomBookingData;
import static restfulbooker.utils.TestDataGenerator.generateTestDataWithEmptyFields;

public class CreateBooking extends TestBase {

    @Test
    @PositiveTest
    @BookingCreate
    @Story("Create a booking")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Creating booking with random data")
    @Description("Test the POST /booking endpoint to create a booking using java-faker.")
    void createBookingWithRandomData() {
        CreateBookingResponseModel response = step("Make a request", () ->
                given(requestSpec)
                        .body(randomBookingData())
                        .when()
                        .post("/booking")
                        .then()
                        .spec(createBookingWithStatusCode200)
                        .extract().as(CreateBookingResponseModel.class));
        step("Id is Integer Type", () -> {
            assertInstanceOf(Integer.class, response.getBookingId());
        });
    }

    @Test
    @PositiveTest
    @BookingCreate
    @Story("Create a booking")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Creating booking using test data")
    @Description("Test the POST /booking endpoint to create a booking using test data")
    void createBookingWithTestData() {
        CreateBookingResponseModel.Booking testData = TestDataGenerator.generateTestDataWithProperties();
        CreateBookingResponseModel response = step("Make a request", () ->
                given(requestSpec)
                        .body(testData)
                        .when()
                        .post("/booking")
                        .then()
                        .spec(createBookingWithStatusCode200)
                        .extract().as(CreateBookingResponseModel.class)
        );
        step("Id is Integer Type", () -> {
            assertInstanceOf(Integer.class, response.getBookingId());
        });
        step("data is correct", () -> {
            assertEquals(response.getBooking().getFirstName(), testData.getFirstName());
            assertEquals(response.getBooking().getLastName(), testData.getLastName());
            assertEquals(response.getBooking().getTotalPrice(), testData.getTotalPrice());
            assertTrue(response.getBooking().isDepositPaid());
            assertEquals(response.getBooking().getAdditionalNeeds(), testData.getAdditionalNeeds());
            assertEquals(
                    response.getBooking().getBookingDates().getCheckIn(),
                    testData.getBookingDates().getCheckIn()
            );
            assertEquals(
                    response.getBooking().getBookingDates().getCheckOut(),
                    testData.getBookingDates().getCheckOut()
            );
        });
    }

    @Test
    @NegativeTest
    @BookingCreate
    @Story("Create a booking")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Creating booking with empty fields")
    @Description("Test the POST /booking endpoint to create a booking with empty fields.")
    void createBookingWithEmptyFields() {
        CreateBookingResponseModel response = step("Make a request", () ->
                given(requestSpec)
                        .body(generateTestDataWithEmptyFields())
                        .when()
                        .post("/booking")
                        .then()
                        .spec(createBookingWithEmptyData)
                        .extract().as(CreateBookingResponseModel.class));
        step("firstname is empty", () -> {
            assertTrue(response.getBooking().getFirstName().isEmpty());
        });
    }
}
