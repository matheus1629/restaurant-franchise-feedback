package com.restaurant.feedbacksstorage.dto;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeFilterDto {

    private Date initDate;
    private Date finalDate;
}
