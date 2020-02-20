package com.bhanuchaddha.myenergy;

import com.bhanuchaddha.myenergy.resource.dto.request.CreatePricePlanRequestDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.Matchers.equalTo;

public class PricePlansTest {

    // Bad request if price plan already exist
    @Test
    public void badRequestExceptionIfPricePlanAlreadyExist(){
        int random = new Random().nextInt(1000)+10;
        String meterId = "smart-meter-"+random;
        String planName = "price-plan-"+random;
        HelperMethods.generateSingleTestData(planName, meterId);
        HelperMethods.createPricePlan(CreatePricePlanRequestDto.builder()
                .planName(planName)
                .build())
                .then()
                .statusCode(400)
                .assertThat()
                .body("errorCode", equalTo("PRICE_PLAN_ALREADY_EXIST"));
    }

    // exception if not enough reading to compare
    @Test
    public void notFoundExceptionIsThrownIfThereAreNotEnoughReadingToCalculateComparison(){
        int random = new Random().nextInt(1000)+10;
        String meterId = "smart-meter-"+random;
        String planName = "price-plan-"+random;
        HelperMethods.generateSingleTestData(planName, meterId);
        HelperMethods.getComparisons(meterId)
                .then()
                .statusCode(404)
                .assertThat()
                .body("errorCode", equalTo("METER_READING_NOT_AVAILABLE"));
    }

    //comparison- exception if no such meter
    @Test
    public void notFoundExceptionIsThrownIfMeterNotFoudForCalculation(){
        HelperMethods.getComparisons("wrongMeterId")
                .then()
                .statusCode(404)
                .assertThat()
                .body("errorCode", equalTo("METER_NOT_FOUND"));
    }

    // recommendation - fail if no enough readings
    @Test
    public void notFoundExceptionIsThrownIfThereAreNotEnoughReadingToCalculateRecommendation(){
        int random = new Random().nextInt(1000)+10;
        String meterId = "smart-meter-"+random;
        String planName = "price-plan-"+random;
        HelperMethods.generateSingleTestData(planName, meterId);
        HelperMethods.getRecommendation(meterId)
                .then()
                .statusCode(404)
                .assertThat()
                .body("errorCode", equalTo("METER_READING_NOT_AVAILABLE"));
    }

    //recommendation- exception if no such meter
    @Test
    public void notFoundExceptionIsThrownIfMeterNotFoudForRecommendation(){
        HelperMethods.getRecommendation("wrongMeterId")
                .then()
                .statusCode(404)
                .assertThat()
                .body("errorCode", equalTo("METER_NOT_FOUND"));
    }

}
