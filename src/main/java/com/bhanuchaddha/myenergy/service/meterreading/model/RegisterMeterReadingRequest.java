package com.bhanuchaddha.myenergy.service.meterreading.model;

import com.bhanuchaddha.myenergy.resource.dto.request.RegisterMeterReadingRequestDto;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
public class RegisterMeterReadingRequest {
    private String smartMeterId;
    private Set<MeterReading> electricityReadings;

    public static RegisterMeterReadingRequest from(RegisterMeterReadingRequestDto meterReading){
        return RegisterMeterReadingRequest.builder()
                .smartMeterId(meterReading.getSmartMeterId())
                .electricityReadings(meterReading.getElectricityReadings().stream()
                        .map(MeterReading::from)
                        .collect(Collectors.toSet()))
                .build();
    }
}
