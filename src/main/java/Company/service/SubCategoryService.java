package Company.service;

import Company.dto.response.SimpleResponse;
import Company.dto.pagination.SubCategoryPagination;
import Company.dto.response.SubCategoryResponse;

import java.util.List;

public interface SubCategoryService {

    SubCategoryPagination all(int page, int size);
    SimpleResponse save(Long categoryId,String name);
    SubCategoryResponse getById(Long id);
    SubCategoryResponse update(Long id, String name);
    SimpleResponse delete(Long id);
    List<SubCategoryResponse> getByCategoryId(Long id);

}
