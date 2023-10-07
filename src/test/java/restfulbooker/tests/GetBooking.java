package restfulbooker.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restfulbooker.models.BookingResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static restfulbooker.specs.Specifications.requestSpec;
import static restfulbooker.utils.RandomUtils.faker;
import static restfulbooker.specs.Specifications.getBookingWithStatusCode200;

@Owner("EphimSh")
@Feature("Booking")
public class GetBooking extends TestBase {


    @Test
    @Story("Get booking")
    @Description("Test the GET /booking/{id} endpoint randomly; to retrieve booking details.")
    @DisplayName("Get booking data by random id; from 10 - 100")
    void getRandomBooking(){
        BookingResponseModel response = step("Make request", ()->
                given(requestSpec)
                        .when()
                        .get("booking/" + faker.number().numberBetween(10, 100))
                        .then()
                        .spec(getBookingWithStatusCode200)
                        .extract().as(BookingResponseModel.class)
                );
        step("Assert that first name is not empty", () -> {
            assertFalse(response.getFirstName().isEmpty());
        });
    }
}
