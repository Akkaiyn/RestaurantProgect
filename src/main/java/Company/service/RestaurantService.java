package Company.service;

import Company.dto.request.RestaurantRequest;
import Company.dto.pagination.RestaurantPagination;
import Company.dto.response.RestaurantResponse;
import Company.dto.response.SimpleResponse;

public interface RestaurantService {

    RestaurantPagination getPagination(int size, int page);
    SimpleResponse save(RestaurantRequest restaurantRequest);
    RestaurantResponse getById(Long id);
    RestaurantResponse update(Long id, RestaurantRequest restaurantRequest);
    SimpleResponse delete(Long id);
    SimpleResponse assign(Long userId, Long restaurantId);
}
