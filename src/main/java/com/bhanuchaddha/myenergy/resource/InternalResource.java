package com.bhanuchaddha.myenergy.resource;

import com.bhanuchaddha.myenergy.resource.dto.request.CreatePricePlanRequestDto;

import com.bhanuchaddha.myenergy.resource.dto.request.EnrollSmartMeterRequestDto;
import com.bhanuchaddha.myenergy.resource.dto.PricePlanDto;
import com.bhanuchaddha.myenergy.resource.dto.SmartMeterDto;
import com.bhanuchaddha.myenergy.service.priceplan.PricePlanService;
import com.bhanuchaddha.myenergy.service.priceplan.model.CreatePricePlanRequest;
import com.bhanuchaddha.myenergy.service.smartmeter.SmartMeterService;
import com.bhanuchaddha.myenergy.service.smartmeter.model.EnrollSmartMeterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class InternalResource {

    private final PricePlanService pricePlanService;
    private final SmartMeterService smartMeterService;

    @PostMapping("/price-plan")
    public PricePlanDto createPricePlan(@RequestBody CreatePricePlanRequestDto request){
        return pricePlanService.createPricePlan(CreatePricePlanRequest.from(request));
    }

    @PostMapping("/smart-meter")
    public SmartMeterDto enrollSmartMeter(@RequestBody EnrollSmartMeterRequestDto request){
        return smartMeterService.enrollSmartMeter(EnrollSmartMeterRequest.from(request));
    }
}
