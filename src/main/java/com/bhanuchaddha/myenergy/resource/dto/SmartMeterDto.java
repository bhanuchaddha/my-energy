package com.bhanuchaddha.myenergy.resource.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@Getter @Setter
@AllArgsConstructor
public class SmartMeterDto {
    private String smartMeterId;
    private String pricePlanId;
    private String customerName;
    private double unitRate;
}
