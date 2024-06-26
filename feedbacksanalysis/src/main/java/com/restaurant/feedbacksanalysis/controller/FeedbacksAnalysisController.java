package com.restaurant.feedbacksanalysis.controller;

import com.restaurant.feedbacksanalysis.dto.*;
import com.restaurant.feedbacksanalysis.exception.ResourceNotFoundException;
import com.restaurant.feedbacksanalysis.service.FeedbacksAnalysisService;
import com.restaurant.feedbacksanalysis.validators.Validator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(path = "/api")
@Validated
@RequiredArgsConstructor
@Tag(name = "Get Feedbacks Analysis",
        description = "Get analysis of feedbacks stored in the database")
public class FeedbacksAnalysisController {

    private static final Logger logger = LoggerFactory.getLogger(FeedbacksAnalysisController.class);
    private final FeedbacksAnalysisService feedbacksAnalysisService;

    @Operation(
            summary = "Get Feedbacks Analysis by Region",
            description = "Get analysis by region from feedbacksstorage microsservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status Ok"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "408",
                    description = "HTTP Status Request Timeout",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/region-analysis")
    public ResponseEntity<RegionAnalysisDto> feedbackAnalysisByRegion(@RequestParam(required = false) @PastOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initDate,
                                                                      @RequestParam(required = false) @PastOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate) throws ExecutionException, InterruptedException, TimeoutException {

        Validator.validateDateFilter(initDate, finalDate);

        CompletableFuture<RegionAnalysisDto> future = new CompletableFuture<>();

        feedbacksAnalysisService.setRegionAnalysisCallback(future::complete);
        logger.debug("getAnalysisByRegion method start");
        feedbacksAnalysisService.getAnalysisByRegion(initDate, finalDate);

        RegionAnalysisDto regionAnalysisDto = future.get(5, TimeUnit.SECONDS);
        logger.debug("getAnalysisByRegion method end");
        if (regionAnalysisDto.getSouth() == null &&
                regionAnalysisDto.getSoutheast() == null &&
                regionAnalysisDto.getMidwest() == null &&
                regionAnalysisDto.getNortheast() == null &&
                regionAnalysisDto.getNorth() == null) {
            throw new ResourceNotFoundException("No feedback was found based on the selected parameters");
        }

        return ResponseEntity.ok(regionAnalysisDto);
    }

    @Operation(
            summary = "Get Feedbacks Analysis by Age Group",
            description = "Get analysis by age group from feedbacksstorage microsservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status Ok",
                    content = @Content(
                            schema = @Schema(implementation = RegionAnalysisDto.class)

                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "408",
                    description = "HTTP Status Request Timeout",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/age-group-analysis")
    public ResponseEntity<AgeGroupAnalysisDto> feedbackAnalysisByAgeGroup(@RequestParam(required = false) @PastOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initDate,
                                                                          @RequestParam(required = false) @PastOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate) throws ExecutionException, InterruptedException, TimeoutException {

        Validator.validateDateFilter(initDate, finalDate);

        CompletableFuture<AgeGroupAnalysisDto> future = new CompletableFuture<>();

        feedbacksAnalysisService.setAgeGroupAnalysisCallback(future::complete);
        logger.debug("getAnalysisByAgeGroup method start");
        feedbacksAnalysisService.getAnalysisByAgeGroup(initDate, finalDate);

        AgeGroupAnalysisDto ageGroupAnalysisDto = future.get(5, TimeUnit.SECONDS);
        logger.debug("getAnalysisByAgeGroup method end");
        if (ageGroupAnalysisDto.getAgeGroup16To24() == null &&
                ageGroupAnalysisDto.getAgeGroup25To35() == null &&
                ageGroupAnalysisDto.getAgeGroup36To50() == null &&
                ageGroupAnalysisDto.getAgeGroup51To70() == null &&
                ageGroupAnalysisDto.getAgeGroup71To110() == null) {
            throw new ResourceNotFoundException("No feedback was found based on the selected parameters");
        }

        return ResponseEntity.ok(ageGroupAnalysisDto);
    }

    @Operation(
            summary = "Get Custom Feedbacks Analysis",
            description = "Get custom analysis from feedbacksstorage microsservice given selected parameters"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status Ok"),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "408",
                    description = "HTTP Status Request Timeout",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/custom-analysis")
    public ResponseEntity<CustomAnalysisDto> customFeedbackAnalysis(@ModelAttribute CustomAnalysisFilterDto customAnalysisFilterDto) throws ExecutionException, InterruptedException, TimeoutException {

        if (customAnalysisFilterDto.getInitDate() != null && customAnalysisFilterDto.getFinalDate() != null) {
            Validator.validateDateFilter(LocalDate.parse(customAnalysisFilterDto.getInitDate(), DateTimeFormatter.ISO_LOCAL_DATE), LocalDate.parse(customAnalysisFilterDto.getFinalDate(), DateTimeFormatter.ISO_LOCAL_DATE));
        }
        Validator.validateRatingFilter(customAnalysisFilterDto.getMinRating(), customAnalysisFilterDto.getMaxRating());

        CompletableFuture<CustomAnalysisDto> future = new CompletableFuture<>();

        feedbacksAnalysisService.setCustomAnalysisCallback(future::complete);
        logger.debug("getCustomAnalysis method start");
        feedbacksAnalysisService.getCustomAnalysis(customAnalysisFilterDto);

        CustomAnalysisDto customAnalysisDto = future.get(5, TimeUnit.SECONDS);
        logger.debug("getCustomAnalysis method end");
        if (customAnalysisDto.getFiltersAdded() == null) {
            throw new ResourceNotFoundException("No feedback was found based on the selected parameters");
        }

        return ResponseEntity.ok(customAnalysisDto);
    }

}
