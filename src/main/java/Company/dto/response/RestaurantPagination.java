package Company.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor

public class RestaurantPagination {
   private final List<RestaurantResponse> restaurantList;
   private int size;
   private int currentPage;
}
