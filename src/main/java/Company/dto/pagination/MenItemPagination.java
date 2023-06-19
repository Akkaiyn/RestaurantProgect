package Company.dto.pagination;

import Company.dto.response.MenuItemResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenItemPagination {
    private List<MenuItemResponse> allPag;
    private int page;
    private int size;
}
