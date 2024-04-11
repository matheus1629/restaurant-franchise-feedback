package com.restaurant.feedbacksanalysis.controller;

import com.restaurant.feedbacksanalysis.dto.RegionAnalysisDto;
import com.restaurant.feedbacksanalysis.exception.BusinessRuleException;
import com.restaurant.feedbacksanalysis.service.FeedbacksAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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
            description = "Create analysis from feedbacks storage in feedbacksstorage microsservice given selected parameters"
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

        if ((initDate != null && finalDate != null) && initDate.isAfter(finalDate))
            throw new BusinessRuleException("initial date cannot be greater than finaldate");

        CompletableFuture<RegionAnalysisDto> future = new CompletableFuture<>();

        feedbacksAnalysisService.setCallback(future::complete);
        feedbacksAnalysisService.getAnalysisByRegion(initDate, finalDate);


        RegionAnalysisDto regionAnalysisDto = future.get(5, TimeUnit.SECONDS);
        System.out.println(regionAnalysisDto);
        return ResponseEntity.ok(regionAnalysisDto);

    }

}
