package Company.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

public class MenuItemRequest {
    private String name;
    private String image;
    private int price;
    private String description;
    private boolean isVegetarian;

    public MenuItemRequest(String name, String image, int price,
                           String description, boolean isVegetarian) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.isVegetarian = isVegetarian;
    }
}
