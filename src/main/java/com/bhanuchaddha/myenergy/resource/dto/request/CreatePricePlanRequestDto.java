package com.bhanuchaddha.myenergy.resource.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class CreatePricePlanRequestDto {
    private String planName;
    private String companyName;
    private double unitRate;
}
