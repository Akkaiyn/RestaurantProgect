package Company.service.serviceImpl;

import Company.dto.pagination.MenItemPagination;
import Company.dto.request.MenuItemRequest;
import Company.dto.response.MenuItemResponse;
import Company.dto.response.SimpleResponse;
import Company.entity.MenuItem;
import Company.entity.Restaurant;
import Company.exception.BadRequestException;
import Company.exception.NotFoundException;
import Company.repository.MenuItemRepository;
import Company.repository.RestaurantRepository;
import Company.service.MenuItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    @Override
    public MenItemPagination getAllPag(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<MenuItemResponse> all = menuItemRepository.all(pageable);
        return MenItemPagination.builder()
                .allPag(all.getContent())
                .size(all.getTotalPages())
                .page(all.getNumber() + 1 )
                .build();
    }

    @Override
    public SimpleResponse save(Long restaurantId, MenuItemRequest menuItemRequest) {
        if (menuItemRequest.getPrice() < 0){
            throw new BadRequestException("Price can't be minus value");
        }
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() ->
                new NotFoundException("Restaurant with id: " + restaurantId + "  not found "));
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemRequest.getName());
        menuItem.setImage(menuItemRequest.getImage());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setVegetarian(menuItemRequest.isVegetarian());
        menuItem.setRestaurant(restaurant);
        restaurant.getMenuItemList().add(menuItem);
        menuItemRepository.save(menuItem);
        restaurantRepository.save(restaurant);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("MenuItem with id : " + menuItem.getId() + "was successfully saved in" +
                        " restaurant with id: " + restaurantId))
                .build();
    }

    @Override
    public MenuItemResponse getById(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(() -> new NotFoundException("MenuItem with id: "
                + id + " not found."));
        return  MenuItemResponse
                .builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .image(menuItem.getImage())
                .price(menuItem.getPrice())
                .description(menuItem.getDescription())
                .isVegetarian(menuItem.isVegetarian())
                .build();

    }

    @Override
    public MenuItemResponse update(Long id, MenuItemRequest menuItemRequest) {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(() -> new NotFoundException("MenuItem with id: "
                + id + " not found."));

        menuItem.setName(menuItemRequest.getName());
        menuItem.setImage(menuItemRequest.getImage());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setVegetarian(menuItemRequest.isVegetarian());
        menuItemRepository.save(menuItem);
        return  MenuItemResponse
                .builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .image(menuItem.getImage())
                .price(menuItem.getPrice())
                .description(menuItem.getDescription())
                .isVegetarian(menuItem.isVegetarian())
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(() -> new NotFoundException("MenuItem with id: "
                + id + " not found."));
        menuItemRepository.delete(menuItem);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("MenuItem with id : " + menuItem.getId() + " was deleted."
                       ))
                .build();
    }
}
