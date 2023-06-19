package Company.repository;

import Company.dto.response.MenuItemResponse;
import Company.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("select new Company.dto.response.MenuItemResponse(m.id, m.name,m.image," +
            "m.price, m.description, m.isVegetarian) from MenuItem m " +
            "group by m.id, m.name, m.image, m.price, m.description,m.isVegetarian")
    Page<MenuItemResponse> all(Pageable pageable);
}
