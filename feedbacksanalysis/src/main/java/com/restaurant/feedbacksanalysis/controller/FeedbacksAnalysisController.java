package com.restaurant.feedbacksanalysis.controller;

import com.restaurant.feedbacksanalysis.dto.AgeGroupAnalysisDto;
import com.restaurant.feedbacksanalysis.dto.CustomAnalysisDto;
import com.restaurant.feedbacksanalysis.dto.CustomAnalysisFilterDto;
import com.restaurant.feedbacksanalysis.dto.RegionAnalysisDto;
import com.restaurant.feedbacksanalysis.service.FeedbacksAnalysisService;
import com.restaurant.feedbacksanalysis.validators.DateValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "Get Feedbacks Analysis")
public class FeedbacksAnalysisController {


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
                    description = "HTTP Status Bad Request"
            ),
            @ApiResponse(
                    responseCode = "408",
                    description = "HTTP Status Request Timeout"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @GetMapping("/region-analysis")
    public ResponseEntity<RegionAnalysisDto> feedbackAnalysisByRegion(@RequestParam(required = false) @PastOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initDate,
                                                                      @RequestParam(required = false) @PastOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate) throws ExecutionException, InterruptedException, TimeoutException {

        DateValidator.validateDateFilter(initDate, finalDate);

        CompletableFuture<RegionAnalysisDto> future = new CompletableFuture<>();

        feedbacksAnalysisService.setRegionAnalysisCallback(future::complete);
        feedbacksAnalysisService.getAnalysisByRegion(initDate, finalDate);

        RegionAnalysisDto regionAnalysisDto = future.get(5, TimeUnit.SECONDS);

        return ResponseEntity.ok(regionAnalysisDto);
    }

    @Operation(
            summary = "Get Feedbacks Analysis by Age Group",
            description = "Get analysis by age group from feedbacksstorage microsservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status Ok"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request"
            ),
            @ApiResponse(
                    responseCode = "408",
                    description = "HTTP Status Request Timeout"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @GetMapping("/age-group-analysis")
    public ResponseEntity<AgeGroupAnalysisDto> feedbackAnalysisByAgeGroup(@RequestParam(required = false) @PastOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initDate,
                                                                          @RequestParam(required = false) @PastOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate) throws ExecutionException, InterruptedException, TimeoutException {

        DateValidator.validateDateFilter(initDate, finalDate);

        CompletableFuture<AgeGroupAnalysisDto> future = new CompletableFuture<>();

        feedbacksAnalysisService.setAgeGroupAnalysisCallback(future::complete);
        feedbacksAnalysisService.getAnalysisByAgeGroup(initDate, finalDate);

        AgeGroupAnalysisDto ageGroupAnalysisDto = future.get(5, TimeUnit.SECONDS);

        return ResponseEntity.ok(ageGroupAnalysisDto);
    }

    @Operation(
            summary = "Get Custom Feedbacks Analysis",
            description = "Get custom analysis from feedbacksstorage microsservice given selected parameters"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status Ok"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request"
            ),
            @ApiResponse(
                    responseCode = "408",
                    description = "HTTP Status Request Timeout"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @GetMapping("/custom-analysis")
    public ResponseEntity<CustomAnalysisDto> customFeedbackAnalysis(@ModelAttribute CustomAnalysisFilterDto customAnalysisFilterDto) throws ExecutionException, InterruptedException, TimeoutException {

        // TODO json
        if (customAnalysisFilterDto.getInitDate() != null && customAnalysisFilterDto.getFinalDate() != null)
            DateValidator.validateDateFilter(LocalDate.parse(customAnalysisFilterDto.getInitDate(), DateTimeFormatter.ISO_LOCAL_DATE), LocalDate.parse(customAnalysisFilterDto.getFinalDate(), DateTimeFormatter.ISO_LOCAL_DATE));

        CompletableFuture<CustomAnalysisDto> future = new CompletableFuture<>();

        feedbacksAnalysisService.setCustomAnalysisCallback(future::complete);
        feedbacksAnalysisService.getCustomAnalysis(customAnalysisFilterDto);

        CustomAnalysisDto customAnalysisDto = future.get(2, TimeUnit.SECONDS);

        return ResponseEntity.ok(customAnalysisDto);
    }

}
