package com.bhanuchaddha.myenergy.resource;

import com.bhanuchaddha.myenergy.resource.dto.PlanComparisonDto;
import com.bhanuchaddha.myenergy.service.smartmeter.SmartMeterService;
import com.bhanuchaddha.myenergy.service.smartmeter.model.PlanComparisonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/price-plans")
@RequiredArgsConstructor
public class PricePlanResource {
    private final SmartMeterService smartMeterService;

    @GetMapping("/compare-all/{smartMeterId}")
    public PlanComparisonDto getComparison(@PathVariable("smartMeterId") String smartMeterId ){
        return smartMeterService.getPlanComparison(PlanComparisonRequest.from(smartMeterId));
    }

    // Currently Comparison and Recommendation use the same service. As same service method is enough. DRY principal.
    @GetMapping("/recommend/{smartMeterId}")
    public Set<Map.Entry<String, Double>> getRecommendation(@PathVariable("smartMeterId") String smartMeterId ){
        return smartMeterService.getPlanComparison(PlanComparisonRequest.from(smartMeterId)).getPricePlanComparisons().entrySet();
    }
}
