package com.bhanuchaddha.myenergy.resource.dto;

import lombok.*;

import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanComparisonDto {

    @Singular
    private Map<String, Double> pricePlanComparisons;
    private String pricePlanId;
}
