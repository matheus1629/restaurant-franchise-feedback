package com.restaurant.feedbacksanalysis.functions;

import com.restaurant.feedbacksanalysis.dto.RegionAnalysisDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AnalysisFunction {

    @Bean
    public Consumer<RegionAnalysisDto> receiveAnalysis() {
        return regionAnalysisDto -> {
            System.out.println("GGGGGGGGGGG " + regionAnalysisDto);
        };
    }
}
