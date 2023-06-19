package Company.service;

import Company.dto.pagination.MenItemPagination;
import Company.dto.request.MenuItemRequest;
import Company.dto.response.MenuItemResponse;
import Company.dto.response.SimpleResponse;

public interface MenuItemService {

    MenItemPagination getAllPag(int page, int size);

    SimpleResponse save(Long restaurantId, MenuItemRequest menuItemRequest);
    MenuItemResponse getById(Long id);
    MenuItemResponse update(Long id, MenuItemRequest menuItemRequest);
    SimpleResponse  delete(Long id);



}
