package Company.api;

import Company.dto.request.ApplyRequest;
import Company.dto.request.SignInRequest;
import Company.dto.response.AuthResponse;
import Company.dto.response.SimpleResponse;

import Company.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/signUp")
    public AuthResponse signUp(@RequestBody @Valid ApplyRequest authRequest) {
        return authService.signUp(authRequest);
    }

    @PostMapping("/signIn")
    public AuthResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id) {
        return authService.deleteUserById(id);
    }




}
