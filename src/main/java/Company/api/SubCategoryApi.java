package Company.api;

import Company.dto.response.SimpleResponse;
import Company.dto.pagination.SubCategoryPagination;
import Company.dto.response.SubCategoryResponse;
import Company.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sub")
@RequiredArgsConstructor

public class SubCategoryApi {

    private final SubCategoryService service;


    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @GetMapping
    public SubCategoryPagination all (@RequestParam int page, @RequestParam int size){
        return service.all(page, size);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @PostMapping("/{categoryId}")
    public SimpleResponse save(@RequestParam String name, @PathVariable Long categoryId){
        return service.save(categoryId, name);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @GetMapping("/{id}")
    public SubCategoryResponse getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @PutMapping("/{id}")
    public SubCategoryResponse update(@PathVariable Long id, @RequestParam String name){
        return service.update(id, name);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @GetMapping("/cat/{id}")
    public List<SubCategoryResponse> allInCategory(@PathVariable Long id){
        return service.getByCategoryId(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return service.delete(id);
    }

}
