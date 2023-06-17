package Company.service.serviceImpl;

import Company.dto.response.RestaurantPagination;
import Company.dto.response.RestaurantResponse;
import Company.repository.RestaurantRepository;
import Company.service.RestaurantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
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
}
