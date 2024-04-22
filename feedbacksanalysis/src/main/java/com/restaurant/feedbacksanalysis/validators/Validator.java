package com.restaurant.feedbacksanalysis.validators;

import com.restaurant.feedbacksanalysis.exception.BusinessRuleException;

import java.time.LocalDate;

public class Validator {

    public static void validateDateFilter(LocalDate initDate, LocalDate finalDate) {
        if ((initDate != null && finalDate != null) && initDate.isAfter(finalDate))
            throw new BusinessRuleException("initial date cannot be greater than finaldate");
    }

    public static void validateRatingFilter(Integer minRating, Integer maxRating) {
        if (minRating > maxRating) throw new BusinessRuleException("minRating date cannot be greater than maxRating");
    }
}
