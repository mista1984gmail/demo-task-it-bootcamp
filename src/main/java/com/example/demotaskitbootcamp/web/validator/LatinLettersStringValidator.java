package com.example.demotaskitbootcamp.web.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LatinLettersStringValidator implements
        ConstraintValidator<OnlyLatinLetters, String> {
  @Override
  public void initialize(OnlyLatinLetters contactNumber) {
  }

  String patterns ="^[a-zA-Z]+$";

  @Override
  public boolean isValid(String contactField,
                         ConstraintValidatorContext cxt) {
    if (contactField == null) {
      return true;
    }
    return contactField.matches(patterns);
  }
}
