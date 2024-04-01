package com.restaurant.feedbacksstorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegionAnalysisDto {
    private Map<String, String> south;
    private Map<String, String> southeast;
    private Map<String, String> midwest;
    private Map<String, String> northeast;
    private Map<String, String> north;
}
