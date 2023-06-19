package Company.dto.pagination;

import Company.dto.response.CategoryResponse;
import Company.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CategoryPagination {
   private final List<CategoryResponse> categoryList;
   private int size;
   private int page;

}
