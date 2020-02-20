package com.bhanuchaddha.myenergy.resource.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PricePlanDto {
    private String id;
    private double unitRate;
    private String companyName;

}
