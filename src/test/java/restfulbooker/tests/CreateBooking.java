package restfulbooker.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restfulbooker.models.CreateBookingResponseModel;
import restfulbooker.utils.TestDataGenerator;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static restfulbooker.specs.Specifications.createBookingWithStatusCode200;
import static restfulbooker.specs.Specifications.requestSpec;
import static restfulbooker.utils.RandomUtils.randomBookingData;

public class CreateBooking extends TestBase{

    @Test
    @Story("Create a booking")
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
        step("booking id is received", () -> {
            assertNotNull(response.getBookingId());
        });
        step("first name is not empty", () -> {
            assertFalse(response.getBooking().getFirstName().isEmpty());
        });
    }

    @Test
    @Story("Create a booking")
    @DisplayName("Creating booking using test data")
    @Description("Test the POST /booking endpoint to create a booking using test data")
    void createBookingWithTestData() {
        CreateBookingResponseModel.Booking testData = TestDataGenerator.generateTestData();
        CreateBookingResponseModel response = step("Make a request", () ->
                given(requestSpec)
                        .body(testData)
                        .when()
                        .post("/booking")
                        .then()
                        .spec(createBookingWithStatusCode200)
                        .extract().as(CreateBookingResponseModel.class)
        );
        step("first name is correct", () -> {
            assertEquals(response.getBooking().getFirstName(), testData.getFirstName());
        });
        step("id is received", () -> {
            assertNotNull(response.getBookingId());
        });
    }
}
