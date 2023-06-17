package Company.service;

import Company.dto.request.SignInRequest;
import Company.dto.response.AuthResponse;
import Company.dto.request.ApplyRequest;
import Company.dto.response.SimpleResponse;

public interface AuthService {
    SimpleResponse signUpCandidateToRestaurant(Long restaurantId, Long candidateId);
    AuthResponse signUp(ApplyRequest authRequest);
    AuthResponse signIn(SignInRequest signInRequest);

    SimpleResponse deleteUserById(Long id);
}
