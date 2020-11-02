package com.example.framadate.model.userDtos;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostUserDtoTest {

    private Validator validator;

    @BeforeAll
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
     void givenValidValues_WhenConstructing_thenValid(){
        PostUserDto user = new PostUserDto(" st","test@dsd.fr");
        Set<ConstraintViolation<PostUserDto>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }
    @Test
    void givenWhiteSpaceName_WhenConstructing_thenFail(){
        PostUserDto user = new PostUserDto("  ","test@dsd.fr");
        Set<ConstraintViolation<PostUserDto>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }
    @Test
    void givenNullName_WhenConstructing_thenFail(){
        PostUserDto user = new PostUserDto(null,"test@dsd.fr");
        Set<ConstraintViolation<PostUserDto>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void givenNotValidEmail_WhenConstructing_thenFail(){
        PostUserDto user = new PostUserDto("dada","testdsd.fr");
        Set<ConstraintViolation<PostUserDto>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }
    @Test
    void givenNullEmail_WhenConstructing_thenFail(){
        PostUserDto user = new PostUserDto("test",null);
        Set<ConstraintViolation<PostUserDto>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }
}