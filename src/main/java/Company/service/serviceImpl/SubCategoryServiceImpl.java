package Company.service.serviceImpl;

import Company.dto.response.SimpleResponse;
import Company.dto.pagination.SubCategoryPagination;
import Company.dto.response.SubCatResponseByCatName;
import Company.dto.response.SubCategoryResponse;
import Company.entity.Category;
import Company.entity.SubCategory;
import Company.exception.NotFoundException;
import Company.repository.CategoryRepository;
import Company.repository.SubCategoryRepository;
import Company.service.SubCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public SubCategoryPagination all(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SubCatResponseByCatName> all = subCategoryRepository.allWithPage(pageable);

        return SubCategoryPagination
                .builder()
                .list(all.getContent())
                .page(all.getNumber() + 1)
                .size(all.getTotalPages())
                .build();
    }

    @Override
    public SimpleResponse save(Long categoryId, String name) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category with id: " + categoryId +
                "not found."));
        SubCategory subCategory = new SubCategory();
        subCategory.setName(name);
        subCategory.setCategory(category);
        subCategoryRepository.save(subCategory);
        category.getSubCategoryList().add(subCategory);
        categoryRepository.save(category);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Sub category with id: " + subCategory.getId()
                        +" was successfully saved  in Category with id: " + categoryId))
                .build();
    }

    @Override
    public SubCategoryResponse getById(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SubCategory with id: " + id +
                        "not found."));
        return SubCategoryResponse
                .builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .build();
    }

    @Override
    public SubCategoryResponse update(Long id, String name) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SubCategory with id: " + id +
                        "not found."));
        subCategory.setName(name);
        return SubCategoryResponse
                .builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SubCategory with id: " + id +
                        "not found."));

        subCategoryRepository.delete(subCategory);
          return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Sub category with id: " + id + " was deleted."))
                .build();
    }

    @Override
    public List<SubCategoryResponse> getByCategoryId(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id: " + id +
                        "not found."));
        List<SubCategoryResponse> all = new ArrayList<>();
        for (int i = 0; i < category.getSubCategoryList().size(); i++) {
            SubCategory subCategory = category.getSubCategoryList().get(i);
             all.add(
                     new SubCategoryResponse(
                             subCategory.getId(),
                             subCategory.getName()));
        }
        return all;
    }
}
