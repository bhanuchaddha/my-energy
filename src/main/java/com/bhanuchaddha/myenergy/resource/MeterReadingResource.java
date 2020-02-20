package com.bhanuchaddha.myenergy.resource;

import com.bhanuchaddha.myenergy.resource.dto.MeterReadingDto;
import com.bhanuchaddha.myenergy.resource.dto.request.RegisterMeterReadingRequestDto;
import com.bhanuchaddha.myenergy.service.meterreading.MeterReadingService;
import com.bhanuchaddha.myenergy.service.meterreading.model.GetMeterReadingRequest;
import com.bhanuchaddha.myenergy.service.meterreading.model.MeterReading;
import com.bhanuchaddha.myenergy.service.meterreading.model.RegisterMeterReadingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/readings")
@RequiredArgsConstructor
public class MeterReadingResource {
    private final MeterReadingService meterReadingService;

    @PostMapping("/store")
    public void storeReading(@RequestBody RegisterMeterReadingRequestDto meterReading){
        meterReadingService.registerMeterReading(RegisterMeterReadingRequest.from(meterReading));
    }

    @GetMapping("/read/{smartMeterId}")
    public List<MeterReadingDto> getMeterReading(@PathVariable("smartMeterId") String smartMeterId ){
        return meterReadingService.getMeterReadings(GetMeterReadingRequest.from(smartMeterId)).stream()
                .map(MeterReading::toMeterReadingDto)
                .collect(Collectors.toList())
                ;
    }
}
