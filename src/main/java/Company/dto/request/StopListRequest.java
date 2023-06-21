package Company.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class StopListRequest {
    private String reason;
    private LocalDate date;

    public StopListRequest(String reason, LocalDate date) {
        this.reason = reason;
        this.date = date;
    }
}
