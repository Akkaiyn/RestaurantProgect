package Company.api;

import Company.dto.pagination.StopListPagination;
import Company.dto.request.StopListRequest;
import Company.dto.response.SimpleResponse;
import Company.entity.StopList;
import Company.service.StopListService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stop")
@RequiredArgsConstructor

public class StopListApi {

    private final StopListService service;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @PostMapping("/{menuItemId}")
    public SimpleResponse save(
            @PathVariable Long menuItemId,
            @RequestBody StopListRequest stopListRequest){
        return service.save( stopListRequest,menuItemId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @GetMapping("/{id}")
    public StopList getById(@PathVariable Long id){
        return service.getById(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @PutMapping("/{id}")
    public StopList update(@PathVariable Long id, @RequestBody StopListRequest stopListRequest){
        return service.update(id, stopListRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF', 'WAITER')")
    @GetMapping
    public StopListPagination getAll(@RequestParam int page, @RequestParam int size){
        return service.getAll(page, size);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return service.delete(id);
    }

}
