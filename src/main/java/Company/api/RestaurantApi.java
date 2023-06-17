package Company.api;

import Company.dto.response.RestaurantPagination;
import Company.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/res")
@RequiredArgsConstructor
public class RestaurantApi {
    private final RestaurantService restaurantService;
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/pag")
    public RestaurantPagination getPagination(@RequestParam int size, @RequestParam int page){
      return   restaurantService.getPagination(size, page);
    }
}
