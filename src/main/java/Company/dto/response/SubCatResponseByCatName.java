package Company.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubCatResponseByCatName {
    private Long id;
    private String name;
    private String categoryName;

    public SubCatResponseByCatName(Long id, String name, String categoryName) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
    }
}
