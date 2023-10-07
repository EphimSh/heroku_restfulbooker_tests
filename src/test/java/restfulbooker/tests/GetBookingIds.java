package restfulbooker.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restfulbooker.models.GetBookingIdResponseModel;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static restfulbooker.specs.Specifications.getBookingIdsWithStatusCode200;
import static restfulbooker.specs.Specifications.requestSpec;

@Owner("EphimSh")
@Feature("Booking")
public class GetBookingIds extends TestBase {

    @Test
    @Story("Get booking")
    @Description("Test retrieving a list of booking IDs")
    @DisplayName("Get Booking IDs")
    void getBookingIds(){
        List<GetBookingIdResponseModel> response = step("Get list of booking ids", () ->
                given(requestSpec)
                        .when()
                        .get("/booking")
                        .then()
                        .spec(getBookingIdsWithStatusCode200)
                        .extract()
                        .jsonPath()
                        .getList(".", GetBookingIdResponseModel.class));

        step("Assert that list is not empty", () -> {
            assertFalse(response.isEmpty());
        });
    }
}
