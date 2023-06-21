package Company.api;

import Company.dto.pagination.MenItemPagination;
import Company.dto.request.MenuItemRequest;
import Company.dto.response.MenuItemResponse;
import Company.dto.response.SimpleResponse;
import Company.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuItemApi {

    private final MenuItemService menuItemService;
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @GetMapping
    public MenItemPagination allPag(
            @RequestParam int page, @RequestParam int size,
            @RequestParam String ascOrDesc, @RequestParam boolean isVeg){
        return menuItemService.getAllPag(page, size,isVeg , ascOrDesc );
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

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @PutMapping("/{id}")
    public MenuItemResponse update(@PathVariable Long id,
                                   @RequestBody MenuItemRequest menuItemRequest){
        return menuItemService.update(id, menuItemRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return menuItemService.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @GetMapping("/search")
    public List<MenuItemResponse> search(@RequestParam String letter){
        return menuItemService.search(letter);
    }



}
