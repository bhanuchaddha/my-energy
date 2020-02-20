package com.bhanuchaddha.myenergy.resource.dto.request;

import com.bhanuchaddha.myenergy.resource.dto.MeterReadingDto;
import com.bhanuchaddha.myenergy.service.meterreading.model.MeterReading;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMeterReadingRequestDto {
    private String smartMeterId;
    private Set<MeterReadingDto> electricityReadings;
}
