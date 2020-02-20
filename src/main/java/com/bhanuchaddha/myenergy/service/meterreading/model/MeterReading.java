package com.bhanuchaddha.myenergy.service.meterreading.model;

import com.bhanuchaddha.myenergy.resource.dto.MeterReadingDto;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Date;

@Builder
@Getter
public class MeterReading {
    private Date time;
    private double reading;

    public MeterReadingDto toMeterReadingDto(){
        return MeterReadingDto.builder()
                .time(time)
                .reading(reading)
                .build();
    }

    public static MeterReading from(MeterReadingDto meterReadingDto){
        return MeterReading.builder()
                .time(meterReadingDto.getTime())
                .reading(meterReadingDto.getReading())
                .build();
    }
}
