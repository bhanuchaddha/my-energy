package com.bhanuchaddha.myenergy.service.priceplan.model;

import com.bhanuchaddha.myenergy.resource.dto.request.CreatePricePlanRequestDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreatePricePlanRequest {
    private String planName;
    private String companyName;
    private double unitRate;


    public static CreatePricePlanRequest from(CreatePricePlanRequestDto request){
        return CreatePricePlanRequest.builder()
                .planName(request.getPlanName())
                .unitRate(request.getUnitRate())
                .companyName(request.getCompanyName())
                .build();
    }
}
