package restfulbooker.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restfulbooker.models.GetBookingIdResponseModel;

import java.util.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static restfulbooker.specs.Specifications.*;


@Owner("EphimSh")
@Feature("Booking")
public class GetBookingIds extends TestBase {

    @Test
    @Story("Get booking")
    @Description("Test retrieving a list of booking IDs")
    @DisplayName("Get Booking IDs")
    void getBookingIds() {
        List<GetBookingIdResponseModel> response = step("Get list of booking ids", () ->
                given(requestSpec)
                        .when()
                        .get("/booking")
                        .then()
                        .spec(getBookingIdsWithStatusCode200)
                        .extract()
                        .jsonPath()
                        .getList(".", GetBookingIdResponseModel.class));
        step("Check that each id is unique", () -> {
            Set<Integer> uniqueIds = new HashSet<>();
            for (GetBookingIdResponseModel booking : response) {
                uniqueIds.add(booking.getBookingId());
            }
            assertEquals(response.size(), uniqueIds.size());
        });
    }

    @Test
    @Story("Get booking")
    @Description("Test retrieving a list of booking IDs and checking their type")
    @DisplayName("Check Booking ID Types")
    void checkBookingIdTypes() {
        List<GetBookingIdResponseModel> response = step("Get list of booking ids", () ->
                given(requestSpec)
                        .when()
                        .get("/booking")
                        .then()
                        .spec(getBookingIdsWithStatusCode200)
                        .extract()
                        .jsonPath()
                        .getList(".", GetBookingIdResponseModel.class));
        step("Check that each id is an Integer type", () -> {
            for (GetBookingIdResponseModel booking : response) {
                assertInstanceOf(Integer.class, booking.getBookingId());
            }
        });
    }

    @Test
    @Story("Get booking")
    @Description("Test retrieving a list of booking IDs with specific filters including an empty checkout date")
    @DisplayName("Get Booking IDs with Filters Including Empty Checkout Date")
    void getBookingIdsWithFilters() {
        String response = step("Get list of booking ids with specific firstname", () ->
                given(requestSpec)
                        .queryParam("firstname", "John")
                        .queryParam("checkin", "1999-09-09")
                        .queryParam("checkout", "")
                        .when()
                        .get("/booking")
                        .then()
                        .spec(getBookingIdsWithStatusCode500)
                        .extract().body()
                        .asString());
        step("Verify response for empty checkout date", () -> {
            assertEquals("Internal Server Error", response);
        });

    }
}
