package com.restaurant.feedbacksanalysis.validators;

import com.restaurant.feedbacksanalysis.exception.BusinessRuleException;

import java.time.LocalDate;

public class DateValidator {

    public static void validateDateFilter(LocalDate initDate, LocalDate finalDate){
        if ((initDate != null && finalDate != null) && initDate.isAfter(finalDate))
            throw new BusinessRuleException("initial date cannot be greater than finaldate");
    }
}
