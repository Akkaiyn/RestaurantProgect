package Company.service.serviceImpl;

import Company.config.JwtService;
import Company.dto.request.ApplyRequest;
import Company.dto.request.SignInRequest;
import Company.dto.response.AuthResponse;
import Company.dto.response.SimpleResponse;

import Company.entity.User;
import Company.enums.Role;
import Company.exception.AlreadyExistException;
import Company.exception.BadRequestException;
import Company.exception.NotFoundException;

import Company.repository.UserRepository;
import Company.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public AuthResponse apply(ApplyRequest a) {

        // TODO  (Nothing to do, just notice for COMMON logic)********************************

        if (userRepository.existsByEmail(a.getEmail())) {
            throw new AlreadyExistException("User with email: "
                    + a.getEmail() + " already exists ");
        }

        ZoneId bishkek = ZoneId.of("Asia/Dhaka");
        LocalTime time = LocalTime.of(10, 15);
        Period age = Period.between(a.getDateOfBirth(),LocalDate.now());


        if (a.getPassword().length() < 4) {
            throw new BadRequestException("Password must include at least 4 symbol");
        }



        if (!a.getRole().equals(Role.CANDIDATE_WAITER)
                && !a.getRole().equals(Role.CANDIDATE_CHEF)) {
            throw new BadRequestException("You already work in some restaurant " +
                    "or you don't have role");
        }
        // TODO  (Nothing to do, just notice for WAITER logic)********************************
        if (a.getRole().equals(Role.CANDIDATE_WAITER)) {
            if (a.getExperience() < 1) {
                throw new BadRequestException("Experience for chef must be at least 1 year");
            }
            if (age.getYears() < 18 || age.getYears() > 30) {
                throw new BadRequestException("Age for chef must be more than 18 " +
                        " and less than 30");
            }

            User user = User
                        .builder()
                        .firstName(a.getFirstName())
                        .lastName(a.getLastName())
                        .dateOfBirth(ZonedDateTime.of(a.getDateOfBirth(), time, bishkek))
                        .email(a.getEmail())
                        .password(passwordEncoder.encode(a.getPassword()))
                        .phoneNumber(a.getPhoneNumber())
                        .role(a.getRole())
                        .experience(a.getExperience())
                        .build();

                userRepository.save(user);
                String jwtToken = jwtService.generateToken(user);
                return AuthResponse
                        .builder()
                        .id(user.getId())
                        .token(jwtToken)
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build();


        }

        // TODO  (Nothing to do, just notice for CHEF logic)********************************

        AuthResponse authResponse = new AuthResponse();
        if (a.getRole().equals(Role.CANDIDATE_CHEF)) {
            if (a.getExperience() < 2) {
                throw new BadRequestException("Experience for chef must be at least 2 year");
            }
            if (age.getYears() < 25 || age.getYears() > 45) {
                throw new BadRequestException("Age for chef must be more than 25" +
                        " and less than 45");
            }
                User user = User
                        .builder()
                        .firstName(a.getFirstName())
                        .lastName(a.getLastName())
                        .dateOfBirth(ZonedDateTime.of(a.getDateOfBirth(), time, bishkek))
                        .email(a.getEmail())
                        .password(passwordEncoder.encode(a.getPassword()))
                        .phoneNumber(a.getPhoneNumber())
                        .role(a.getRole())
                        .experience(a.getExperience())
                        .build();


                userRepository.save(user);
                String jwtToken = jwtService.generateToken(user);

                authResponse.setId(user.getId());
                authResponse.setToken(jwtToken);
                authResponse.setEmail(user.getEmail());
                authResponse.setRole(user.getRole());


        }

        return authResponse;
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
    public SimpleResponse delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User with id : " + id + " not found"));
        userRepository.deleteById(id);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("User with id: %s successfully deleted.", id))
                .build();
    }


}

