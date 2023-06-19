package Company.service;

import Company.dto.pagination.CategoryPagination;
import Company.dto.response.CategoryResponse;
import Company.dto.response.SimpleResponse;

public interface CategoryService {
    SimpleResponse save(String name);
    CategoryResponse getById(Long id);
    CategoryResponse update(Long id, String name);
    CategoryPagination categories(int size, int page);
    SimpleResponse delete(Long id);
}
