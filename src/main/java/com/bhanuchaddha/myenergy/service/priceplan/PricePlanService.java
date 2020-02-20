package com.bhanuchaddha.myenergy.service.priceplan;

import com.bhanuchaddha.myenergy.data.entity.CompanyEntity;
import com.bhanuchaddha.myenergy.data.entity.PricePlanEntity;
import com.bhanuchaddha.myenergy.data.repository.CompanyRepository;
import com.bhanuchaddha.myenergy.data.repository.PricePlanRepository;
import com.bhanuchaddha.myenergy.error.ErrorCode;
import com.bhanuchaddha.myenergy.error.exception.ServiceException;
import com.bhanuchaddha.myenergy.error.exception.ValidationException;
import com.bhanuchaddha.myenergy.resource.dto.PricePlanDto;
import com.bhanuchaddha.myenergy.service.priceplan.model.CreatePricePlanRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PricePlanService {
    private final CompanyRepository companyRepository;
    private final PricePlanRepository pricePlanRepository;

    public PricePlanDto createPricePlan(CreatePricePlanRequest request){

        pricePlanRepository.findById(request.getPlanName())
                .ifPresent( __ -> { throw new ValidationException(ErrorCode.PRICE_PLAN_ALREADY_EXIST);});

        CompanyEntity companyEntity = companyRepository.save(CompanyEntity.builder()
                .companyName(request.getCompanyName())
                .build());
        return pricePlanRepository.save(PricePlanEntity.builder()
                .id(request.getPlanName())
                .unitRate(request.getUnitRate())
                .company(companyEntity)
                .build()
        ).pricePlanDto();
    }
}
