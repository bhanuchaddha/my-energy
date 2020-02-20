package com.bhanuchaddha.myenergy.resource.dto;

import lombok.*;

import java.util.Map;
import java.util.TreeMap;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanRecommendationDto {

    @Singular
    private Map<String, Double> pricePlanComparisons = new TreeMap<>();
}
