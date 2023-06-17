package Company.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberSizeValidator implements ConstraintValidator<PhoneNumberSizeValid, String > {
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return phoneNumber.length() == 13;
    }
}
