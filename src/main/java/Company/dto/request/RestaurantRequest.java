package Company.dto.request;

import Company.enums.RestType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

public class RestaurantRequest {
    private String  name;
    private String location;
    private RestType restType;
    private double service;

    public RestaurantRequest(String name, String location, RestType restType, double service) {
        this.name = name;
        this.location = location;
        this.restType = restType;
        this.service = service;

    }
}
