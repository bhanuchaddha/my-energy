package com.bhanuchaddha.myenergy.service.meterreading.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetMeterReadingRequest {
    private String smartMeterId;

    public static GetMeterReadingRequest from(String smartMeterId){
        return GetMeterReadingRequest.builder()
                .smartMeterId(smartMeterId)
                .build();
    }
}
