package com.bhanuchaddha.myenergy.resource.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor @AllArgsConstructor
public class EnrollSmartMeterRequestDto {
    private String smartMeterId;
    private String pricePlanId;
    private String customerName;
}
