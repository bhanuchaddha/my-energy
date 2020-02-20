package com.bhanuchaddha.myenergy;

import com.bhanuchaddha.myenergy.resource.dto.MeterReadingDto;
import com.bhanuchaddha.myenergy.resource.dto.request.CreatePricePlanRequestDto;
import com.bhanuchaddha.myenergy.resource.dto.request.EnrollSmartMeterRequestDto;
import com.bhanuchaddha.myenergy.resource.dto.request.RegisterMeterReadingRequestDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class GenerateTestData {

    @Test
    public void generate(){
        pricePlans().forEach(HelperMethods::createPricePlan);
        meterEnrollmentData().forEach(HelperMethods::enrollSmartMeterToPricePlan);
        HelperMethods.recordMeterReading(meterReadingData());
    }


    private List<CreatePricePlanRequestDto> pricePlans(){
        return Arrays.asList(
                CreatePricePlanRequestDto.builder()
                        .planName("price-plan-0")
                        .unitRate(10)
                        .companyName("Day Night Energy")
                        .build(),
                CreatePricePlanRequestDto.builder()
                        .planName("price-plan-1")
                        .unitRate(2)
                        .companyName("Green Energy")
                        .build(),
                CreatePricePlanRequestDto.builder()
                        .planName("price-plan-2")
                        .unitRate(1)
                        .companyName("Power Energy")
                        .build()
        );
    }

    private List<EnrollSmartMeterRequestDto> meterEnrollmentData(){
        return Arrays.asList(
                EnrollSmartMeterRequestDto.builder()
                        .smartMeterId("smart-meter-0")
                        .pricePlanId("price-plan-0")
                        .customerName("John")
                        .build(),
                EnrollSmartMeterRequestDto.builder()
                        .smartMeterId("smart-meter-1")
                        .pricePlanId("price-plan-1")
                        .customerName("Dave")
                        .build(),
                EnrollSmartMeterRequestDto.builder()
                        .smartMeterId("smart-meter-2")
                        .pricePlanId("price-plan-0")
                        .customerName("Sam")
                        .build(),
                EnrollSmartMeterRequestDto.builder()
                        .smartMeterId("smart-meter-3")
                        .pricePlanId("price-plan-2")
                        .customerName("Anders")
                        .build(),
                EnrollSmartMeterRequestDto.builder()
                        .smartMeterId("smart-meter-4")
                        .pricePlanId("price-plan-1")
                        .customerName("Alex")
                        .build()
                );
    }

    private RegisterMeterReadingRequestDto meterReadingData(){
        return RegisterMeterReadingRequestDto.builder()
                .smartMeterId("smart-meter-0")
                .electricityReadings(new HashSet<>(Arrays.asList(
                        MeterReadingDto.builder()
                                .time(date("2020-01-01T10:15:30"))
                                .reading(1.32)
                                .build(),
                        MeterReadingDto.builder()
                                .time(date("2020-02-01T10:15:30"))
                                .reading(2.32)
                                .build(),
                        MeterReadingDto.builder()
                                .time(date("2020-03-01T10:15:30"))
                                .reading(4.27)
                                .build(),
                        MeterReadingDto.builder()
                                .time(date("2020-04-01T10:15:30"))
                                .reading(10.30)
                                .build()

                )))
                .build();
    }

    private Date date(String dateString){
        return Date.from(LocalDateTime.parse(dateString).atZone(ZoneId.systemDefault()).toInstant());
    }
}
