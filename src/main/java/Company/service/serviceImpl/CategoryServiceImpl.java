package Company.service.serviceImpl;

import Company.dto.pagination.CategoryPagination;
import Company.dto.response.CategoryResponse;
import Company.dto.response.SimpleResponse;
import Company.entity.Category;
import Company.exception.NotFoundException;
import Company.repository.CategoryRepository;
import Company.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryPagination categories(int size, int page) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("name"));
        Page<CategoryResponse> categories = categoryRepository.categories(pageable);

        return CategoryPagination
                .builder()
                .categoryList(categories.getContent())
                .page(categories.getNumber() + 1)
                .size(categories.getTotalPages())
                .build();
    }

    @Override
    public SimpleResponse save(String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Category with id: %s  saved", category.getId() ))
                .build();
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category with id: " + id +
                "not found."));
         return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();

    }

    @Override
    public CategoryResponse update(Long id, String name) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category with id: " + id +
                "not found."));
        category.setName(name);
        categoryRepository.save(category);
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }


    @Override
    public SimpleResponse delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category with id: " + id +
                "not found."));

        categoryRepository.delete(category);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Category with id: %s  deleted", category.getId() ))
                .build();
    }
}
