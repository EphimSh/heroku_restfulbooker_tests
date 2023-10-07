package restfulbooker.config;

import org.aeonbits.owner.Config;
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources("classpath:restfulbooker/config/webconfiguration.properties")
public interface WebConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://restful-booker.herokuapp.com")
    String getBaseUrl();

    @Key("username")
    String getUsername();

    @Key("password")
    String getPassword();

}
