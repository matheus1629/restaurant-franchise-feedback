package com.restaurant.feedbacksstorage.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeFilterDto {

    private LocalDate initDate;
    private LocalDate finalDate;
}
