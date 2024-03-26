package com.restaurant.feedbacksanalysis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Schema(
        name = "Filter Time Parameters",
        description = "Schema to hold parameters that will be used for analysis."
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeFilterDto {

    @PastOrPresent
    private Date initDate;

    @PastOrPresent
    private Date finalDate;
}
