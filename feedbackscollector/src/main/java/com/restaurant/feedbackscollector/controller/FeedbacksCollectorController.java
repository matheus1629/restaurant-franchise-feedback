package com.restaurant.feedbackscollector.controller;

import com.restaurant.feedbackscollector.dto.ErrorResponseDto;
import com.restaurant.feedbackscollector.dto.FeedbackDto;
import com.restaurant.feedbackscollector.service.FeedbacksCollectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(path = "/api")
@Validated
@RequiredArgsConstructor
@Tag(name = "Send Feedback")
public class FeedbacksCollectorController {

    private static final Logger logger = LoggerFactory.getLogger(FeedbacksCollectorController.class);
    private final FeedbacksCollectorService feedbacksCollectorService;

    @Operation(
            summary = "Send Feedback",
            description = "Send feedback to feedbacksstorage microservice through kafka"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "202",
                    description = "HTTP Status Accepted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
            ,
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/send-feedback")
    public ResponseEntity<Void> sendFeedback(@Valid @RequestBody FeedbackDto feedbackDto) throws TimeoutException, ExecutionException, InterruptedException {
        logger.debug("sendFeedback method start");
        feedbacksCollectorService.sendFeedback(feedbackDto);
        logger.debug("sendFeedback method end");
        return ResponseEntity.accepted().build();
    }
}
