package com.restaurant.feedbacksanalysis.dto;

import com.restaurant.feedbacksanalysis.enums.Gender;
import com.restaurant.feedbacksanalysis.enums.LevelSatisfaction;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "Analyzer Parameters",
        description = "Schema to hold parameters that will be used for analysis. Parameters that have not a value set won't be consider for the analysis"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackAnalyzerDto {

    @Schema(description = "Id number of the restaurant.", example = "1")
    private Integer idRestaurant;

    @Schema(description = "Minimum customer's age. Can't be higher than MaxAge", example = "20", minimum = "1", maximum = "110")
    @Min(16)
    @Max(110)
    private Integer MinAge;

    @Schema(description = "Minimum customer's age. Can't be lower than MinAge", example = "50", minimum = "1", maximum = "110")
    @Min(16)
    @Max(110)
    private Integer MaxAge;

    @Schema(description = "Customer's gender", example = "MALE")
    private Gender gender;

    @Schema(description = "Minimum rating. Can't be higher than maxRating", example = "5", minimum = "1", maximum = "10")
    @Min(1)
    @Max(10)
    private Integer minRating;

    @Schema(description = "Maximum rating. Can't be lower than maxRating", example = "10", minimum = "1", maximum = "10")
    @Min(1)
    @Max(10)
    private Integer maxRating;

    @Schema(description = "Level of satisfaction with the quality of the meal", example = "SATISFIED")
    private LevelSatisfaction mealQuality;

    @Schema(description = "Order came as expected", example = "True")
    @NotNull
    private Boolean wrongOrder;

    @Schema(description = "Level of satisfaction with the waiting time", example = "SATISFIED")
    @NotNull
    private LevelSatisfaction waitingTime;

    @Schema(description = "Level of satisfaction with the service", example = "SATISFIED")
    @NotNull
    private LevelSatisfaction service;

    @Schema(description = "Level of satisfaction with the ambience", example = "SATISFIED")
    @NotNull
    private LevelSatisfaction ambience;
}
