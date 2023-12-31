package Company.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
    private Long id;
    private String name;

    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
