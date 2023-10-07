package restfulbooker.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restfulbooker.models.BookingResponseModel;
import restfulbooker.models.CreateBookingResponseModel;
import restfulbooker.utils.TestDataGenerator;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static restfulbooker.specs.Specifications.*;
import static restfulbooker.utils.RandomUtils.*;

@Owner("EphimSh")
@Feature("Booking")
public class BookingActions extends TestBase {


    @Test
    @Story("Create a booking")
    @DisplayName("Creating booking with random data")
    @Description("Test the POST /booking endpoint to create a booking using java-faker.")
    void createBookingWithRandomData() {

        CreateBookingResponseModel response = step("Make a request", () ->
                given(createBookingRequestSpec)
                        .body(randomBookingData())
                        .when()
                        .post("/booking")
                        .then()
                        .spec(createBookingWithStatusCode200)
                        .extract().as(CreateBookingResponseModel.class));

        step("booking id is received", () -> {
            assertNotNull(response.getBookingId());
        });

        step("name is not empty", () -> {
            assertNotEquals("", response.getBooking().getFirstName());
        });
    }

    @Test
    @Story("Create a booking")
    @DisplayName("Creating booking using test data")
    @Description("Test the POST /booking endpoint to create a booking using test data")
    void createBookingWithTestData() {
        CreateBookingResponseModel.Booking testData = TestDataGenerator.generateTestData();


        CreateBookingResponseModel response = step("Make a request", () ->
                given(createBookingRequestSpec)
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

    @Test
    @Story("Update a booking")
    @DisplayName("Create then update booking by id")
    @Description("")
    void createBookingWithTestDataThenUpdateIt() {

        String token = step("Getting a token to proceed the operations", () -> getToken());
        CreateBookingResponseModel.Booking testData = TestDataGenerator.generateTestData();
        CreateBookingResponseModel postResponse = step("Make POST request", () ->
                given(createBookingRequestSpec)
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
                given(updateBookingRequestSpec)
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
    @Story("Partial update a booking")
    @DisplayName("Create then partial update booking by id")
    @Description("")
    void createBookingWithTestDataThenPatchIt() {

        String token = step("Getting a token to proceed the operations", () -> getToken());
        CreateBookingResponseModel.Booking testData = TestDataGenerator.generateTestData();
        CreateBookingResponseModel postResponse = step("Make POST request", () ->
                given(createBookingRequestSpec)
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
                given(partialUpdateBookingRequestSpec)
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

    @Test
    @Story("Delete booking")
    @DisplayName("Create then delete booking by id")
    @Description("")
    void createBookingWithTestDataThenDelete() {

        String token = step("Getting a token to proceed the operations", () -> getToken());
        CreateBookingResponseModel.Booking testData = TestDataGenerator.generateTestData();
        CreateBookingResponseModel postResponse = step("Make POST request", () ->
                given(createBookingRequestSpec)
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
                given(deleteBookingRequestSpec)
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
