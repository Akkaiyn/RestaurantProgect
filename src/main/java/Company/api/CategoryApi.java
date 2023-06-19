package Company.api;

import Company.dto.pagination.CategoryPagination;
import Company.dto.response.CategoryResponse;
import Company.dto.response.SimpleResponse;
import Company.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cat")
@RequiredArgsConstructor
public class CategoryApi {
    private final CategoryService categoryService;
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping
    public CategoryPagination all(@RequestParam int size, @RequestParam int page){
        return categoryService.categories(size,page);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PostMapping
    public SimpleResponse save(@RequestParam String name){
        return categoryService.save(name);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id){
        return categoryService.getById(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestParam String name){
        return categoryService.update(id,name);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return categoryService.delete(id);
    }






}
