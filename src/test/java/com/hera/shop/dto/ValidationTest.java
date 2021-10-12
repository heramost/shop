package com.hera.shop.dto;

import org.junit.jupiter.api.BeforeEach;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

abstract class ValidationTest {
    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected void validate(Object o, String... violationMessages) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        assertThat(violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList())).containsExactlyInAnyOrder(violationMessages);
    }
}
