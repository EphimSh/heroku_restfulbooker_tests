package restfulbooker.models;

import lombok.Data;

@Data
public class CreateTokenRequestBodyModel {
    String username, password;
}
