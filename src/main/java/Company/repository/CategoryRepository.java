package Company.repository;

import Company.dto.response.CategoryResponse;
import Company.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select new Company.dto.response.CategoryResponse(c.id, c.name) from Category c")
    Page<CategoryResponse> categories(Pageable pageable);
}
