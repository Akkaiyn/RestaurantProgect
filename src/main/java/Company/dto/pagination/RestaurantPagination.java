package Company.dto.pagination;

import Company.dto.response.RestaurantResponse;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Data

public class RestaurantPagination {
   private final List<RestaurantResponse> restaurantList;
   private int size;
   private int currentPage;
}
