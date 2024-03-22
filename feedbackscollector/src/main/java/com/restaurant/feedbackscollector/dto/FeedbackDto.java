package com.restaurant.feedbackscollector.dto;

import com.restaurant.feedbackscollector.enums.Gender;
import com.restaurant.feedbackscollector.enums.LevelSatisfaction;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Schema(
        name = "Feedback",
        description = "Schema to hold feedback information"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {

    @Schema(description = "Id number of the restaurant", example = "1")
    @PositiveOrZero
    @NotNull
    private Integer idRestaurant;

    @Schema(description = "Customer's age", example = "25")
    @Positive
    @NotNull
    @Max(110)
    private Integer age;

    @Schema(description = "Customer's gender", example = "MALE")
    @NotNull
    private Gender gender;

    @Schema(description = "Rating ", example = "8")
    @NotNull
    @Min(1)
    @Max(10)
    private Integer rating;

    @Schema(description = "Level of satisfaction with the quality of the meal", example = "SATISFIED")
    @NotNull
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

    public FeedbackDto(Integer age, Gender gender, Integer rating, LevelSatisfaction mealQuality, Boolean wrongOrder, LevelSatisfaction waitingTime, LevelSatisfaction service, LevelSatisfaction ambience) {
        this.age = age;
        this.gender = gender;
        this.rating = rating;
        this.mealQuality = mealQuality;
        this.wrongOrder = wrongOrder;
        this.waitingTime = waitingTime;
        this.service = service;
        this.ambience = ambience;
    }
}
