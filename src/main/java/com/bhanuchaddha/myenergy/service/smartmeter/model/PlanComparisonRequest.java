package com.bhanuchaddha.myenergy.service.smartmeter.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlanComparisonRequest {
    private String smartMeterId;

    public static PlanComparisonRequest from(String smartMeterId){
        return PlanComparisonRequest.builder()
                .smartMeterId(smartMeterId)
                .build();
    }
}
