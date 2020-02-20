package com.bhanuchaddha.myenergy.service.smartmeter.model;

import com.bhanuchaddha.myenergy.resource.dto.request.EnrollSmartMeterRequestDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EnrollSmartMeterRequest {
    private String smartMeterId;
    private String pricePlanId;
    private String customerName;

    public static EnrollSmartMeterRequest from(EnrollSmartMeterRequestDto requestDto){
        return EnrollSmartMeterRequest.builder()
                .customerName(requestDto.getCustomerName())
                .pricePlanId(requestDto.getPricePlanId())
                .smartMeterId(requestDto.getSmartMeterId())
                .build();
    }
}
