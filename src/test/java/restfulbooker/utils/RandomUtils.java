package restfulbooker.utils;

import com.github.javafaker.Faker;
import restfulbooker.models.CreateBookingResponseModel;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class RandomUtils {
    public static Faker faker = new Faker();


    public static CreateBookingResponseModel.Booking randomBookingData() {
        CreateBookingResponseModel.Booking bookingData = new CreateBookingResponseModel.Booking();
        bookingData.setFirstName(Faker.instance().name().firstName());
        bookingData.setLastName(Faker.instance().name().lastName());
        bookingData.setTotalPrice(Faker.instance().number().numberBetween(100, 1000));
        bookingData.setDepositPaid(Faker.instance().random().nextBoolean());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        bookingData.setBookingDates(new CreateBookingResponseModel.Booking
                        .BookingDates(
                        dateFormat.format(faker.date().future(30, TimeUnit.DAYS)),
                        dateFormat.format(faker.date().future(30, TimeUnit.DAYS))
                )
        );
        bookingData.setAdditionalNeeds(faker.food().dish());
        return bookingData;
    }
}
