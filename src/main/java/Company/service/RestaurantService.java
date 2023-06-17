package Company.service;

import Company.dto.response.RestaurantPagination;

public interface RestaurantService {

    RestaurantPagination getPagination(int size, int page);

}
