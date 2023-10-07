package restfulbooker.utils;

import org.aeonbits.owner.ConfigFactory;
import restfulbooker.config.PersonDataConfig;
import restfulbooker.models.CreateBookingResponseModel;

public class TestDataGenerator {

    static PersonDataConfig config = ConfigFactory.create(PersonDataConfig.class, System.getProperties());
    public static CreateBookingResponseModel.Booking generateTestData() {

        CreateBookingResponseModel.Booking testData = new CreateBookingResponseModel.Booking();
        testData.setFirstName(config.getFirstName());
        testData.setLastName(config.getLastName());
        testData.setTotalPrice(config.getTotalPrice());
        testData.setDepositPaid(config.getDepositPaid());
        testData.setBookingDates(new CreateBookingResponseModel.Booking
                .BookingDates(config.getCheckIn(), config.getCheckOut()));
        testData.setAdditionalNeeds(config.getAdditionalNeeds());

        return testData;
    }

    public static CreateBookingResponseModel.Booking generateTestDataPutRequest() {

        CreateBookingResponseModel.Booking testData = new CreateBookingResponseModel.Booking();
        testData.setFirstName("Elrond");
        testData.setLastName("Mastodon");
        testData.setTotalPrice(1000101011);
        testData.setDepositPaid(true);
        testData.setBookingDates(new CreateBookingResponseModel.Booking
                .BookingDates("2023-07-10", "2023-12-12"));
        testData.setAdditionalNeeds("spaceship");

        return testData;
    }

}
