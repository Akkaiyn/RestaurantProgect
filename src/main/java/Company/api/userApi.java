package Company.api;

import Company.dto.request.ApplyRequest;
import Company.dto.request.SignInRequest;
import Company.dto.response.AuthResponse;
import Company.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
