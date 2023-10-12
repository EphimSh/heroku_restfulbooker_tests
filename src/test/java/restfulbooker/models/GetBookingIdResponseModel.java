package restfulbooker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBookingIdResponseModel {
    @JsonProperty("bookingid")
    private Integer bookingId;
}

