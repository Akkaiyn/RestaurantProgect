package Company.repository;

import Company.dto.response.SubCatResponseByCatName;
import Company.dto.response.SubCategoryResponse;
import Company.entity.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    @Query("select new Company.dto.response.SubCatResponseByCatName(s.id, s.name, c.name) " +
            "from  SubCategory s join Category c group by  s.id, s.name, c.name")
    Page<SubCatResponseByCatName> allWithPage(Pageable pageable);

}
