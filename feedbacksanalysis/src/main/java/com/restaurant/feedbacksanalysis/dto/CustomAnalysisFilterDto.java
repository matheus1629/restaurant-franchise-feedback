package com.restaurant.feedbacksanalysis.dto;

import com.restaurant.feedbacksanalysis.enums.Gender;
import com.restaurant.feedbacksanalysis.enums.LevelSatisfaction;
import com.restaurant.feedbacksanalysis.enums.Region;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(
        name = "Analysis Parameters",
        description = "Schema to hold parameters that will be used for analysis. Parameters that have not a value set won't be consider for the analysis"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomAnalysisFilterDto {

    @Schema(description = "Initial date", example = "2022-03-05", defaultValue = "1970-01-01")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String initDate;

    @Schema(description = "Final date", example = "2023-03-05", defaultValue = "Today's date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String finalDate;

    @Schema(description = "Region of the restaurants.", example = "MIDWEST")
    private Region region;

    @Schema(description = "Minimum customer's age. Can't be higher than MaxAge", example = "20", minimum = "1", maximum = "110", defaultValue = "16")
    @Min(16)
    @Max(110)
    private Integer minAge = 16;

    @Schema(description = "Minimum customer's age. Can't be lower than MinAge", example = "50", minimum = "1", maximum = "110", defaultValue = "110")
    @Min(16)
    @Max(110)
    private Integer maxAge = 110;

    @Schema(description = "Customer's gender", example = "FEMALE")
    private Gender gender;

    @Schema(description = "Minimum rating. Can't be higher than maxRating", example = "5", minimum = "1", maximum = "10", defaultValue = "1")
    @Min(1)
    @Max(10)
    private Integer minRating = 1;

    @Schema(description = "Maximum rating. Can't be lower than maxRating", example = "10", minimum = "1", maximum = "10", defaultValue = "10")
    @Min(1)
    @Max(10)
    private Integer maxRating = 10;

    @Schema(description = "Level of satisfaction with the quality of the meal", example = "SATISFIED")
    private LevelSatisfaction mealQuality;

    @Schema(description = "Order came as expected", example = "true")
    private Boolean wrongOrder;

    @Schema(description = "Level of satisfaction with the waiting time", example = "SATISFIED")
    private LevelSatisfaction waitingTime;

    @Schema(description = "Level of satisfaction with the service", example = "SATISFIED")
    private LevelSatisfaction service;

    @Schema(description = "Level of satisfaction with the ambience", example = "SATISFIED")
    private LevelSatisfaction ambience;
}
