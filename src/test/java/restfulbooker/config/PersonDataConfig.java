package restfulbooker.config;

import org.aeonbits.owner.Config;
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources("classpath:restfulbooker/config/testdata.properties")
public interface PersonDataConfig extends Config {
    @Key("firstName")
    String getFirstName();
    @Key("lastName")
    String getLastName();

    @Key("totalPrice")
    int getTotalPrice();

    @Key("depositPaid")
    boolean getDepositPaid();

    @Key("checkIn")
    String getCheckIn();

    @Key("checkOut")
    String getCheckOut();

    @Key("additionalNeeds")
    String getAdditionalNeeds();

}
