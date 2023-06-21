package Company.service.serviceImpl;

import Company.dto.pagination.StopListPagination;
import Company.dto.request.StopListRequest;
import Company.dto.response.SimpleResponse;
import Company.dto.response.StopListResponse;
import Company.entity.MenuItem;
import Company.entity.StopList;
import Company.exception.AlreadyExistException;
import Company.exception.BadRequestException;
import Company.exception.NotFoundException;
import Company.repository.MenuItemRepository;
import Company.repository.StopListRepository;
import Company.service.StopListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@RequiredArgsConstructor
public class StopListServiceImpl implements StopListService {
    private final MenuItemRepository menuItemRepository;
    private final StopListRepository stopListRepository;

    ZoneId bishkek = ZoneId.of("Asia/Dhaka");
    LocalTime time = LocalTime.of(10, 15);

    @Override
    public SimpleResponse save(StopListRequest stopListRequest, Long menuItemId) {


        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(() -> new NotFoundException("MenuItem with id: "
                + menuItemId + " not found."));


        StopList stopList = new StopList();
        stopList.setReason(stopListRequest.getReason());
        stopList.setDate(ZonedDateTime.of(stopListRequest.getDate(), time, bishkek));



        if (menuItem.getStopList() == null) {
            stopList.setMenuItem(menuItem);
            menuItem.setStopList(stopList);
            menuItemRepository.save(menuItem);
            stopListRepository.save(stopList);
            return SimpleResponse
                    .builder()
                    .httpStatus(HttpStatus.OK)
                    .message(String.format(" MenuItem with id: " + menuItemId + "was added to " +
                            "stop list with date : " + stopList.getDate()))
                    .build();

        }

        int year = menuItem.getStopList().getDate().getYear();
        int month = menuItem.getStopList().getDate().getMonthValue();
        int day = menuItem.getStopList().getDate().getDayOfMonth();
        LocalDate date = LocalDate.of(year, month, day);


        if (stopListRequest.getDate().equals(date)) {
            throw new AlreadyExistException("MenuItem with id: " + menuItemId + " already in stop list on : " + date);
        }
        stopList.setMenuItem(menuItem);
        menuItem.setStopList(stopList);
        menuItemRepository.save(menuItem);
        stopListRepository.save(stopList);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format(" MenuItem with id: " + menuItemId + "was added to " +
                        "stop list with date : " + stopList.getDate()))
                .build();

    }

    @Override
    public StopList getById(Long id) {
        return stopListRepository.findById(id).orElseThrow(() ->
                new NotFoundException(" Stop list with id: " + id + " not found."));


    }

    @Override
    public StopList update(Long id, StopListRequest stopListRequest) {
        StopList stopList = stopListRepository.findById(id).orElseThrow(() ->
                new NotFoundException(" Stop list with id: " + id + " not found."));
        stopList.setReason(stopListRequest.getReason());
        stopList.setDate(ZonedDateTime.of(stopListRequest.getDate(), time, bishkek));
        return null;
    }

    @Override
    public StopListPagination getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<StopListResponse> stopListResponses = stopListRepository.allPag(pageable);
        return StopListPagination
                .builder()
                .allPag(stopListResponses.getContent())
                .size(stopListResponses.getTotalPages())
                .page(stopListResponses.getNumber() + 1)
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        StopList stopList = stopListRepository.findById(id).orElseThrow(() ->
                new NotFoundException(" Stop list with id: " + id + " not found."));
        stopListRepository.delete(stopList);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Stop list with id: " + id + " was deleted"))
                .build();

    }
}
