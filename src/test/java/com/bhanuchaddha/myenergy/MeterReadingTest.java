package com.bhanuchaddha.myenergy;

import com.bhanuchaddha.myenergy.resource.dto.MeterReadingDto;
import com.bhanuchaddha.myenergy.resource.dto.request.RegisterMeterReadingRequestDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

import static org.hamcrest.Matchers.equalTo;

public class MeterReadingTest {
    //Duplicate reading is ignored
    @Test
    public void duplicateMeterReadingIsIgnored(){
        int random = new Random().nextInt(1000)+10;
        String meterId = "smart-meter-"+random;
        String planName = "price-plan-"+random;
        HelperMethods.generateSingleTestData(planName, meterId);
        HelperMethods.recordMeterReading(meterReadingWithDuplicateData(meterId));
        Response response = HelperMethods.getMeterReading(meterId);
        response.body().jsonPath().getList("time");
        Assert.isTrue(response.body().jsonPath().getList("time").size() == 4, "Only four record are saved");
    }

    // new meter readings are merged
    @Test
    public void newMeterReadingAreMergedWithExistingReadings(){
        int random = new Random().nextInt(1000)+10;
        String meterId = "smart-meter-"+random;
        String planName = "price-plan-"+random;
        HelperMethods.generateSingleTestData(planName, meterId);
        HelperMethods.recordMeterReading(meterReadingPart1(meterId));
        HelperMethods.recordMeterReading(meterReadingPart1Plus1Extra(meterId));
        Response response = HelperMethods.getMeterReading(meterId);
        response.body().jsonPath().getList("time");
        Assert.isTrue(response.body().jsonPath().getList("time").size() == 5, "There should be total of 5 records now");
    }

    // exception if no such meter
    @Test
    public void errorMessageIsReceivedIfMeterIsNotFound(){
        Response response = HelperMethods.getMeterReading("wrongId");
        response.then()
                .statusCode(404)
                .assertThat()
                .body("errorCode",equalTo("METER_NOT_FOUND"));
    }


    private RegisterMeterReadingRequestDto meterReadingPart1(String meterId){
        return RegisterMeterReadingRequestDto.builder()
                .smartMeterId(meterId)
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

    private RegisterMeterReadingRequestDto meterReadingPart1Plus1Extra(String meterId){
        return RegisterMeterReadingRequestDto.builder()
                .smartMeterId(meterId)
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
                                .build(),
                        MeterReadingDto.builder()
                                .time(date("2020-05-01T10:15:30"))
                                .reading(23.30)
                                .build()
                )))
                .build();
    }

    private RegisterMeterReadingRequestDto meterReadingWithDuplicateData(String meterId){
        return RegisterMeterReadingRequestDto.builder()
                .smartMeterId(meterId)
                .electricityReadings(new HashSet<>(Arrays.asList(
                        MeterReadingDto.builder()
                                .time(date("2020-01-01T10:15:30"))
                                .reading(1.32)
                                .build(),
                        MeterReadingDto.builder()
                                .time(date("2020-01-01T10:15:30"))
                                .reading(1.33)
                                .build(),
                        MeterReadingDto.builder()
                                .time(date("2020-01-01T10:15:30"))
                                .reading(1.34)
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
