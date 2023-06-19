package Company.service.serviceImpl;

import Company.dto.request.RestaurantRequest;
import Company.dto.pagination.RestaurantPagination;
import Company.dto.response.RestaurantResponse;
import Company.dto.response.SimpleResponse;
import Company.entity.Restaurant;
import Company.entity.User;
import Company.enums.Role;
import Company.exception.BadRequestException;
import Company.exception.NotFoundException;
import Company.repository.RestaurantRepository;
import Company.repository.UserRepository;
import Company.service.RestaurantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    @Override
    public RestaurantPagination getPagination(int size, int page) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("name"));

        Page<RestaurantResponse> all = restaurantRepository.getAll(pageable);
        return RestaurantPagination.builder()
                .restaurantList(all.getContent())
                .size(all.getTotalPages())
                .currentPage(all.getNumber() + 1 )
                .build();
    }

    @Override
    public SimpleResponse save(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setRestType(restaurantRequest.getRestType());
        restaurant.setService(restaurantRequest.getService());
        restaurantRepository.save(restaurant);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Restaurant with id: %s was successfully saved!", restaurant.getId()))
                .build();
    }

    @Override
    public RestaurantResponse getById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Restaurant with id: " + id + "  not found "));
        return RestaurantResponse
                .builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .restType(restaurant.getRestType())
                .numberOfEmployees(restaurant.getNumberOfEmployees())
                .service(restaurant.getService())
                .build();
    }

    @Override
    public RestaurantResponse update(Long id, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Restaurant with id: " + id + "  not found "));
        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setRestType(restaurantRequest.getRestType());
        restaurant.setNumberOfEmployees(restaurant.getNumberOfEmployees());
        restaurant.setService(restaurantRequest.getService());
        restaurantRepository.save(restaurant);
        return  RestaurantResponse
                .builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .restType(restaurant.getRestType())
                .numberOfEmployees(restaurant.getNumberOfEmployees())
                .service(restaurant.getService())
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Restaurant with id: " + id + "  not found "));
        restaurantRepository.deleteById(id);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Restaurant with id: %s was deleted.",id))
                .build();
    }

    @Override
    public SimpleResponse assign(Long userId, Long restaurantId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("User with id : " + userId + " not found"));
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() ->
                new NotFoundException("Restaurant with id : " + restaurantId + " not found"));
        if (restaurant.getNumberOfEmployees() >= 15){
            throw new BadRequestException("There is no vacation, sorry!");
        }
        user.setRestaurant(restaurant);
        restaurant.getUserList().add(user);

        if (user.getRole() == Role.CANDIDATE_WAITER){
            user.setRole(Role.WAITER);
        }else if (user.getRole() == Role.CANDIDATE_CHEF){
            user.setRole(Role.CHEF);
        }
        userRepository.save(user);
        restaurantRepository.save(restaurant);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("User with id: " + userId +
                        "was successfully assigned to restaurant with id: " + restaurantId))
                .build();
    }
}
