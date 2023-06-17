package Company.service.serviceImpl;

import Company.config.JwtService;
import Company.dto.request.ApplyRequest;
import Company.dto.request.SignInRequest;
import Company.dto.response.AuthResponse;
import Company.dto.response.SimpleResponse;
import Company.entity.Restaurant;
import Company.entity.User;
import Company.enums.Role;
import Company.exception.AlreadyExistException;
import Company.exception.BadRequestException;
import Company.exception.NotFoundException;
import Company.repository.RestaurantRepository;
import Company.repository.UserRepository;
import Company.service.AuthService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;



@Service
@Transactional
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public SimpleResponse signUpCandidateToRestaurant(Long restaurantId, Long candidateId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFoundException(
                "There is no restaurant with id: " + restaurantId));
        User user = userRepository.findById(candidateId).orElseThrow(() -> new NotFoundException(
                "There is no user with id: " + candidateId));

        if (user.getRole() != Role.CANDIDATE_WAITER) {
            throw new BadRequestException("User with id: " + candidateId + "is already employee in some restaurant");
        }
        if (user.getExperience() >= 1 && user.getExperience() <= 2
                && user.getDateOfBirth().getYear() >= 18 && user.getDateOfBirth().getYear() <= 30  ) {
            user.setRole(Role.WAITER);

            return Duble(restaurantId, candidateId, restaurant, user);

        }else if (user.getExperience() >= 2){
            user.setRole(Role.CHEF);

            return Duble(restaurantId, candidateId, restaurant, user);
        }


        return null;
    }

    private SimpleResponse Duble(Long restaurantId, Long candidateId, Restaurant restaurant, User user) {
        restaurant.getUserList().add(user);
        user.setRestaurant(restaurant);
        userRepository.save(user);
        restaurantRepository.save(restaurant);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("User with id: " + candidateId + "was assigned to " +
                        "restaurant with id: " + restaurantId + " as  a " + user.getRole()))
                .build();
    }

    @Override
    public AuthResponse signUp(ApplyRequest authRequest) {
        if (userRepository.existsByEmail(authRequest.getEmail())) {
            throw new AlreadyExistException("User with email: "
                    + authRequest.getEmail() + " already exists ");
        }
        ZoneId bishkek = ZoneId.of("Asia/Dhaka");
        LocalTime time = LocalTime.of(10, 15);
        return null;
    }

    @Override
    public AuthResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("User with email: " +
                        signInRequest.getEmail() + " doesn't found!"));
        if (signInRequest.getEmail().isBlank()) {
            throw new BadCredentialsException("Email doesn't exist");
        }
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect password! ");
        }
        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .id(user.getId())
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public SimpleResponse deleteUserById(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("User with id: " + id + "not found");
        }

        userRepository.deleteById(id);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("User with id: %s was deleted ", id))
                .build();
    }

//    @PostConstruct
//
//    public void initAdmin() {
//        User user = User.builder()
//                .firstName("Admin")
//                .lastName("Super")
//                .dateOfBirth(ZonedDateTime.now())
//                .email("admin@gmail.com")
//                .password(passwordEncoder.encode("admin"))
//                .role(Role.ADMIN)
//                .build();
//        userRepository.save(user);
//
//    }


}
