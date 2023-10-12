package restfulbooker.utils;

import restfulbooker.models.CreateBookingResponseModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestDataGenerator {

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = TestDataGenerator.class.getClassLoader().getResourceAsStream("restfulbooker/config/testdata.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    private static final Properties props = loadProperties();

    public static CreateBookingResponseModel.Booking generateTestData() {
        CreateBookingResponseModel.Booking testData = new CreateBookingResponseModel.Booking();
        testData.setFirstName(props.getProperty("firstName"));
        testData.setLastName(props.getProperty("lastName"));
        testData.setTotalPrice(Integer.parseInt(props.getProperty("totalPrice")));
        testData.setDepositPaid(Boolean.parseBoolean(props.getProperty("depositPaid")));
        testData.setBookingDates(new CreateBookingResponseModel.Booking
                .BookingDates(props.getProperty("checkIn"), props.getProperty("checkOut")));
        testData.setAdditionalNeeds(props.getProperty("additionalNeeds"));

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

    public static CreateBookingResponseModel.Booking generateTestDataWithEmptyFields() {
        CreateBookingResponseModel.Booking testData = new CreateBookingResponseModel.Booking();
        testData.setFirstName("");
        testData.setLastName("");
        testData.setTotalPrice(0);
        testData.setDepositPaid(false);
        testData.setBookingDates(new CreateBookingResponseModel.Booking
                .BookingDates("", ""));
        testData.setAdditionalNeeds("");

        return testData;
    }
}
