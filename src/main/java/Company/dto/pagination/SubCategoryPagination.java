package Company.dto.pagination;

import Company.dto.response.SubCatResponseByCatName;
import Company.dto.response.SubCategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class SubCategoryPagination {
    private final List<SubCatResponseByCatName> list;
    private int size;
    private int page;

}
