package com.restaurant.feedbacksanalysis.controller;

import com.restaurant.feedbacksanalysis.service.FeedbacksAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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
                    responseCode = "200",
                    description = "HTTP Status Ok"
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
    public ResponseEntity<Object> sendFeedback(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initDate,
                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate) {

        feedbacksAnalysisService.getAnalysisByRegion(initDate, finalDate);
        return ResponseEntity.noContent().build();
    }
}
