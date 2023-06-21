package Company.service;

import Company.dto.pagination.MenItemPagination;
import Company.dto.request.MenuItemRequest;
import Company.dto.response.MenuItemResponse;
import Company.dto.response.SimpleResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemService {

    MenItemPagination getAllPag(int page, int size, boolean isVeg, String ascOrDesc);

    SimpleResponse save(Long restaurantId, MenuItemRequest menuItemRequest);
    MenuItemResponse getById(Long id);
    MenuItemResponse update(Long id, MenuItemRequest menuItemRequest);
    SimpleResponse  delete(Long id);



    List<MenuItemResponse> search(String  letter);



}
