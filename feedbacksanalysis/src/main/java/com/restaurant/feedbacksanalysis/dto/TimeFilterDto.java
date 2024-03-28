package com.restaurant.feedbacksanalysis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(
        name = "Filter Time Parameters",
        description = "Schema to hold parameters that will be used for analysis."
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeFilterDto {

    @PastOrPresent
    private LocalDate initDate;

    @PastOrPresent
    private LocalDate finalDate;
}
