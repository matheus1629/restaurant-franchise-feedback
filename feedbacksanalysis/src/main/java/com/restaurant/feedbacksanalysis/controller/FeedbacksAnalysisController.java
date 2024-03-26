package com.restaurant.feedbacksanalysis.controller;

import com.restaurant.feedbacksanalysis.dto.TimeFilterDto;
import com.restaurant.feedbacksanalysis.service.FeedbacksAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
@Validated
@RequiredArgsConstructor
@Tag(name = "Get Feedbacks Analysis")
public class FeedbacksAnalysisController {


    private final FeedbacksAnalysisService feedbacksAnalysisService;

    @Operation(
            summary = "Get Feedbacks Analysis by Region",
            description = "Create analysis from feedbacks storage in feedbacksstorage microsservice given selected parameters"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "HTTP Status No Content"
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "HTTP Status Unprocessable Entity"
            )
            ,
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @GetMapping("/region-analysis")
    public ResponseEntity<Void> sendFeedback(@Valid TimeFilterDto timeFilterDto) {
        feedbacksAnalysisService.getAnalysisByRegion(timeFilterDto);
        return ResponseEntity.noContent().build();
    }
}
