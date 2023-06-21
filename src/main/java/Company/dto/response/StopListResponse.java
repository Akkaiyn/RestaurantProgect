package Company.dto.response;

import Company.entity.MenuItem;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
@Data
@Builder

public class StopListResponse {
    private Long id;
    private String reason;
    private ZonedDateTime date;
    private String menuItemName;

    public StopListResponse(Long id, String reason, ZonedDateTime date, String menuItemName) {
        this.id = id;
        this.reason = reason;
        this.date = date;
        this.menuItemName = menuItemName;
    }
}

