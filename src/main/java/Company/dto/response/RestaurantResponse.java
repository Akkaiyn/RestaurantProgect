package Company.dto.response;

import Company.enums.RestType;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder

@NoArgsConstructor
public class RestaurantResponse {
    private  Long id;
    private String  name;
    private String location;
    private RestType restType;
    private int numberOfEmployees;
    private double service;

    public RestaurantResponse(Long id, String name, String location, RestType restType, int numberOfEmployees, double service) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.restType = restType;
        this.numberOfEmployees = numberOfEmployees;
        this.service = service;
    }
}
