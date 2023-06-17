package Company.service;

import Company.dto.request.ApplyRequest;
import Company.dto.request.SignInRequest;
import Company.dto.response.AuthResponse;
import Company.dto.response.SimpleResponse;

public interface UserService  {
    AuthResponse apply(ApplyRequest a);
    AuthResponse signIn(SignInRequest signInRequest);

//    SimpleResponse assign(Long restaurantId, Long employeeId);

}
