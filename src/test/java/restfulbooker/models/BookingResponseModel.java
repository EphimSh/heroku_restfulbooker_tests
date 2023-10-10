package restfulbooker.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class BookingResponseModel {
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("totalprice")
    private int totalPrice;
    @JsonProperty("depositpaid")
    private boolean depositPaid;
    @JsonProperty("bookingdates")
    private BookingDate bookingDates;
    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    @Data
    private static class BookingDate {
        @JsonProperty("checkin")
        String checkIn;
        @JsonProperty("checkout")
        String checkOut;
    }


}
