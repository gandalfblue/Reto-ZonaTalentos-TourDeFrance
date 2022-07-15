package com.co.sofka.api.tourfrance.exceptions;

import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class MaxSizeConstraintValidator implements ConstraintValidator<MaxSizeConstraint, Set<CiclistDTO>> {
    @Override
    public boolean isValid(Set<CiclistDTO> values, ConstraintValidatorContext context) {
        return values.size() <= 8;
    }
}
