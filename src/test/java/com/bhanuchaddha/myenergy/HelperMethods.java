package com.bhanuchaddha.myenergy;

import com.bhanuchaddha.myenergy.resource.dto.request.CreatePricePlanRequestDto;
import com.bhanuchaddha.myenergy.resource.dto.request.EnrollSmartMeterRequestDto;
import com.bhanuchaddha.myenergy.resource.dto.request.RegisterMeterReadingRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.with;

@UtilityClass
@Slf4j
public class HelperMethods {
    private static ObjectMapper objectMapper= new ObjectMapper();

    @SneakyThrows
    Response createPricePlan(CreatePricePlanRequestDto request){
        RestAssured.reset();
        RestAssured.baseURI="http://127.0.0.1:8080/myenergy";

        Response response = with()
                .body(objectMapper.writeValueAsString(request))
                .contentType(ContentType.JSON)
                .when()
                .request(Method.POST,"/internal/price-plan")
                .then()
                .extract()
                .response();

        Thread.sleep(100);
        return response;
    }

    @SneakyThrows
    Response enrollSmartMeterToPricePlan(EnrollSmartMeterRequestDto request){
        RestAssured.reset();
        RestAssured.baseURI="http://127.0.0.1:8080/myenergy";

        return with()
                .body(objectMapper.writeValueAsString(request))
                .contentType(ContentType.JSON)
                .when()
                .request(Method.POST,"/internal/smart-meter")
                .then()
                .extract()
                .response();
    }

    @SneakyThrows
    void recordMeterReading(RegisterMeterReadingRequestDto request){
        RestAssured.reset();
        RestAssured.baseURI="http://127.0.0.1:8080/myenergy";

       with()
                .body(objectMapper.writeValueAsString(request))
                .contentType(ContentType.JSON)
                .when()
                .request(Method.POST,"/readings/store")
                .then()
                .statusCode(200);

        log.info("Reading {} had been recorded for Smart Meter {} ",request.getElectricityReadings(), request.getSmartMeterId());
        Thread.sleep(100);
    }

    @SneakyThrows
    Response getMeterReading(String smartMeterId){
        RestAssured.reset();
        RestAssured.baseURI="http://127.0.0.1:8080/myenergy";

        Response response = get("/readings/read/{smartMeterId}", smartMeterId)
                .then()
                .extract()
                .response()
                ;

        log.info("Got meterReading {} for meterId {} ", response.body().prettyPrint() , smartMeterId);
        return  response;
    }

    @SneakyThrows
    Response getComparisons(String smartMeterId){
        RestAssured.reset();
        RestAssured.baseURI="http://127.0.0.1:8080/myenergy";

        Response response = get("/price-plans/compare-all/{smartMeterId}", smartMeterId)
                .then()
                .extract()
                .response()
                ;

        log.info("Got Comparison {} for meterId {} ", response.body().prettyPrint() , smartMeterId);
        return  response;
    }

    @SneakyThrows
    Response getRecommendation(String smartMeterId){
        RestAssured.reset();
        RestAssured.baseURI="http://127.0.0.1:8080/myenergy";

        Response response = get("/price-plans/recommend/{smartMeterId}", smartMeterId)
                .then()
                .extract()
                .response()
                ;

        log.info("Got Recommendation {} for meterId {} ", response.body().prettyPrint() , smartMeterId);
        return  response;
    }

    void generateSingleTestData(String planName, String meterId){
        createPricePlan(CreatePricePlanRequestDto.builder()
                .planName(planName)
                .unitRate(100)
                .companyName(planName+"-XYX")
                .build());
        enrollSmartMeterToPricePlan(EnrollSmartMeterRequestDto.builder()
                .smartMeterId(meterId)
                .pricePlanId(planName)
                .customerName(planName+"-"+meterId+"-customer")
                .build());
    }
}
