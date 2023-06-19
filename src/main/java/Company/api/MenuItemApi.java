package Company.api;

import Company.dto.pagination.MenItemPagination;
import Company.dto.request.MenuItemRequest;
import Company.dto.response.MenuItemResponse;
import Company.dto.response.SimpleResponse;
import Company.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuItemApi {

    private final MenuItemService menuItemService;
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @GetMapping
    public MenItemPagination allPag(@RequestParam int page, @RequestParam int size){
        return menuItemService.getAllPag(page, size);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @PostMapping("/{rId}")
    public SimpleResponse save(@PathVariable Long rId, @RequestBody MenuItemRequest menuItemRequest){
        return menuItemService.save(rId, menuItemRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @GetMapping("/{id}")
    public MenuItemResponse getById(@PathVariable Long id){
        return menuItemService.getById(id);
    }




}
