package com.restaurant.feedbackscollector.controller;

import com.restaurant.feedbackscollector.dto.FeedbackDto;
import com.restaurant.feedbackscollector.service.FeedbacksCollectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
@Validated
@RequiredArgsConstructor
@Tag(name = "Send Feedback")
public class FeedbacksCollectorController {

    private final FeedbacksCollectorService feedbacksCollectorService;

    @Operation(
            summary = "Send Feedback",
            description = "Send feedback to analysis microservice through kafka producer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "HTTP Status No Content"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found"
            )
            ,
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @PostMapping("/send-feedback")
    public ResponseEntity<Void> sendFeedback(@Valid @RequestBody FeedbackDto feedbackDto) {
        feedbacksCollectorService.sendFeedback(feedbackDto);
        return ResponseEntity.noContent().build();
    }
}
