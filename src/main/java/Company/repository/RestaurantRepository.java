package Company.repository;

import Company.dto.response.RestaurantResponse;
import Company.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("select  new Company.dto.response.RestaurantResponse(" +
            "r.id,r.name, r.location, r.restType, r.numberOfEmployees, r.service) from Restaurant r")
    List<RestaurantResponse> getAll();

    @Query("select  new Company.dto.response.RestaurantResponse(" +
            "r.id,r.name, r.location, r.restType, r.numberOfEmployees, r.service) from Restaurant r")
    Page<RestaurantResponse> getAll(Pageable pageable);
}
