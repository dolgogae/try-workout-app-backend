package com.tryworkout.backend.domain.user.dto.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        // 간단한 유효성 검사를 수행
        // 실제로는 더 정교한 검사를 수행해야 할 수 있습니다.
        return phoneNumber != null && phoneNumber.matches("^\\d{10,11}$");
    }
}
