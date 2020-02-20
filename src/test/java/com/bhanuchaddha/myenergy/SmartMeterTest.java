package com.bhanuchaddha.myenergy;

import com.bhanuchaddha.myenergy.resource.dto.request.EnrollSmartMeterRequestDto;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.Matchers.equalTo;

public class SmartMeterTest {

    // Bad Request if meter is already enrolled
    @Test
    public void badRequestExceptionIsThrownIfMeterAlreadyEnrolled(){
        int random = new Random().nextInt(1000)+10;
        String meterId = "smart-meter-"+random;
        String planName = "price-plan-"+random;
        HelperMethods.generateSingleTestData(planName, meterId);
        HelperMethods.enrollSmartMeterToPricePlan(EnrollSmartMeterRequestDto.builder()
                .smartMeterId(meterId)
                .pricePlanId(planName)
                .build())
                .then()
                .statusCode(400)
                .assertThat()
                .body("errorCode", equalTo("METER_ALREADY_ENROLLED"));
    }
}
