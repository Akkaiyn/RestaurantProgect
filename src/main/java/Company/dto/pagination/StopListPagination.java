package Company.dto.pagination;

import Company.dto.response.StopListResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class StopListPagination {
    private final List<StopListResponse> allPag;
    private int size;
    private int page;
}
