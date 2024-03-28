package com.restaurant.feedbacksstorage.dto;

import lombok.*;

import java.time.LocalDate;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeFilterDto {

    private LocalDate initDate;
    private LocalDate finalDate;
}
