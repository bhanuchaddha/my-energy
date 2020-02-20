package com.bhanuchaddha.myenergy.service.meterreading;

import com.bhanuchaddha.myenergy.data.entity.MeterReadingEntity;
import com.bhanuchaddha.myenergy.data.entity.SmartMeterEntity;
import com.bhanuchaddha.myenergy.data.repository.SmartMeterRepository;
import com.bhanuchaddha.myenergy.error.ErrorCode;
import com.bhanuchaddha.myenergy.error.exception.NotFoundException;
import com.bhanuchaddha.myenergy.service.meterreading.model.GetMeterReadingRequest;
import com.bhanuchaddha.myenergy.service.meterreading.model.MeterReading;
import com.bhanuchaddha.myenergy.service.meterreading.model.RegisterMeterReadingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeterReadingService {
    private final SmartMeterRepository meterRepository;

    public void registerMeterReading(RegisterMeterReadingRequest request){
            SmartMeterEntity meter = meterRepository.findById(request.getSmartMeterId())
                    .orElseThrow(()-> new NotFoundException(ErrorCode.METER_NOT_FOUND));

        meter.getMeterReadings().addAll(new HashSet<>(request.getElectricityReadings().stream()
                        .map(MeterReadingEntity::from)
                        .collect(Collectors.toSet())));

        meterRepository.save(meter);
    }

    public List<MeterReading> getMeterReadings(GetMeterReadingRequest request){
        return meterRepository.findById(request.getSmartMeterId())
                .orElseThrow(()-> new NotFoundException(ErrorCode.METER_NOT_FOUND))
        .getMeterReadings().stream()
                .map(MeterReadingEntity::toMeterReading)
                .collect(Collectors.toList());
    }
}
