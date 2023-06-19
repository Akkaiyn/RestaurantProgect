package Company.api;

import Company.dto.request.ApplyRequest;
import Company.dto.request.SignInRequest;
import Company.dto.response.AuthResponse;
import Company.dto.response.SimpleResponse;
import Company.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class userApi {

    private final UserService userService;

    @PostMapping("/apply")
    public AuthResponse apply(@RequestBody @Valid ApplyRequest applyRequest){
      return   userService.apply(applyRequest);
    }
    @PostMapping("/signIn")
    public AuthResponse signIn(@RequestBody SignInRequest signInRequest){
        return userService.signIn(signInRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
      return   userService.delete(id);
    }
}
