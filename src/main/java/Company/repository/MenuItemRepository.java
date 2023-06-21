package Company.repository;

import Company.dto.response.MenuItemResponse;
import Company.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("select new Company.dto.response.MenuItemResponse(m.id, m.name,m.image," +
            "m.price, m.description, m.isVegetarian) from MenuItem m ")
    Page<MenuItemResponse> all(Pageable pageable);

    @Query("select new Company.dto.response.MenuItemResponse(m.id, m.name,m.image," +
            "m.price, m.description, m.isVegetarian) from MenuItem m ")
    List<MenuItemResponse> list();


    @Query("select new Company.dto.response.MenuItemResponse(m.id, m.name,m.image," +
            "m.price, m.description, m.isVegetarian) from MenuItem m where m.name like concat('%', : letter, '%')")

    List<MenuItemResponse> search(@Param("letter") String letter);

}
