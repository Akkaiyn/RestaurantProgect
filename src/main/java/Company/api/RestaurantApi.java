package Company.api;

import Company.dto.request.RestaurantRequest;
import Company.dto.pagination.RestaurantPagination;
import Company.dto.response.RestaurantResponse;
import Company.dto.response.SimpleResponse;
import Company.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse save(@RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.save(restaurantRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{id}")
    public RestaurantResponse getRestaurantService(@PathVariable Long id) {
        return restaurantService.getById(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public RestaurantResponse update(@PathVariable  Long id, @RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.update(id, restaurantRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return restaurantService.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/{rId}/{uId}")
    public SimpleResponse assign(@PathVariable("rId" ) Long rId, @PathVariable("uId") Long uId){
        return restaurantService.assign(uId,rId);
    }

}
