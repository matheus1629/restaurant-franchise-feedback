package com.restaurant.feedbacksanalysis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeFilterDto {

    private String initDate;
    private String finalDate;
}
