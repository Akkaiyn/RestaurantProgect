package Company.service;

import Company.dto.pagination.StopListPagination;
import Company.dto.request.StopListRequest;
import Company.dto.response.SimpleResponse;
import Company.entity.StopList;

public interface StopListService {

    SimpleResponse save(StopListRequest stopListRequest, Long menuItemId);
    StopList getById(Long id);
    StopList update(Long id, StopListRequest stopListRequest);
    StopListPagination getAll(int page, int size);
    SimpleResponse delete(Long id);
}
