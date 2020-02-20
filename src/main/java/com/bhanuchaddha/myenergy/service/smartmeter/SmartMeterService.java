package com.bhanuchaddha.myenergy.service.smartmeter;

import com.bhanuchaddha.myenergy.data.entity.MeterReadingEntity;
import com.bhanuchaddha.myenergy.data.entity.PricePlanEntity;
import com.bhanuchaddha.myenergy.data.entity.SmartMeterEntity;
import com.bhanuchaddha.myenergy.data.repository.PricePlanRepository;
import com.bhanuchaddha.myenergy.data.repository.SmartMeterRepository;
import com.bhanuchaddha.myenergy.error.exception.ValidationException;
import com.bhanuchaddha.myenergy.resource.dto.PlanComparisonDto;
import com.bhanuchaddha.myenergy.error.ErrorCode;
import com.bhanuchaddha.myenergy.error.exception.NotFoundException;
import com.bhanuchaddha.myenergy.resource.dto.SmartMeterDto;
import com.bhanuchaddha.myenergy.service.smartmeter.model.EnrollSmartMeterRequest;
import com.bhanuchaddha.myenergy.service.smartmeter.model.PlanComparisonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SmartMeterService {
    private final SmartMeterRepository meterRepository;
    private final PricePlanRepository pricePlanRepository;

    private Comparator<MeterReadingEntity> meterReadingComparator = Comparator.comparing(MeterReadingEntity::getTimestamp);


    public SmartMeterDto enrollSmartMeter(EnrollSmartMeterRequest request){
        meterRepository.findById(request.getSmartMeterId())
                .ifPresent( __ -> { throw new ValidationException(ErrorCode.METER_ALREADY_ENROLLED);});

        PricePlanEntity pricePlanEntity = pricePlanRepository.findById(request.getPricePlanId())
                .orElseThrow(()->  new NotFoundException(ErrorCode.PRICE_PLAN_NOT_FOUND));

        SmartMeterEntity smartMeter = meterRepository.save(SmartMeterEntity.builder()
                .id(request.getSmartMeterId())
                .pricePlan(pricePlanEntity)
                .customer(new SmartMeterEntity.CustomerEntity(request.getCustomerName()))
                .build()
        );

        return SmartMeterDto.builder()
                .customerName(smartMeter.getCustomer().getName())
                .pricePlanId(smartMeter.getPricePlan().getId())
                .unitRate(smartMeter.getPricePlan().getUnitRate())
                .smartMeterId(smartMeter.getId())
                .build();
    }

    public PlanComparisonDto getPlanComparison(PlanComparisonRequest request) {
        SmartMeterEntity meter = meterRepository.findById(request.getSmartMeterId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.METER_NOT_FOUND));
        MeterReadingEntity latestReading= getLatestReading(meter);
        Map<String, Double> pricePlanComparisons = pricePlanRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(PricePlanEntity::getUnitRate))
                .collect(Collectors.toMap(PricePlanEntity::getId, pp -> pp.getUnitRate() * latestReading.getReading()));
        return PlanComparisonDto.builder()
                .pricePlanId(request.getSmartMeterId())
                .pricePlanComparisons(pricePlanComparisons)
                .build();
    }

    private MeterReadingEntity getLatestReading(SmartMeterEntity meter) {
        List<MeterReadingEntity> readingEntityList = new ArrayList<>(meter.getMeterReadings());
        readingEntityList.sort(meterReadingComparator.reversed());
        try {
            return readingEntityList.get(0);
        } catch (IndexOutOfBoundsException ex) {
            throw new NotFoundException(ErrorCode.METER_READING_NOT_AVAILABLE);
        }
    }
}
