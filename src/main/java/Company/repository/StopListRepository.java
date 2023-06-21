package Company.repository;

import Company.dto.response.StopListResponse;
import Company.entity.StopList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StopListRepository extends JpaRepository<StopList, Long> {
    @Query("select new Company.dto.response.StopListResponse(s.id, s.reason, s.date, s.menuItem.name) " +
            "from StopList s ")
    Page<StopListResponse> allPag(Pageable pageable);
}
