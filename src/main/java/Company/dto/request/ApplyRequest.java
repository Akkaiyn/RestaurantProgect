package Company.dto.request;

import Company.enums.Role;
import Company.validation.PhoneNumberSizeValid;
import Company.validation.PhoneNumberValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ApplyRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private LocalDate dateOfBirth;
    @Email
    private String email;

    private String  password;
    @PhoneNumberValid
    @PhoneNumberSizeValid
    private String  phoneNumber;
    @NotNull
    private Role role;
    @NotNull
    private int experience;
}
