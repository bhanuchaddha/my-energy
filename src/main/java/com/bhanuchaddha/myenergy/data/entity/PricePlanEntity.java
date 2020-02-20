package com.bhanuchaddha.myenergy.data.entity;

import com.bhanuchaddha.myenergy.resource.dto.PricePlanDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table( name = "PRICE_PLAN")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PricePlanEntity {

    // Not auto generated to keep it simple. Else custom sequence generator could be used to achieve price-plan-{} like ids.
    @Id
    private String id;
    private double unitRate;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable=false)
    private CompanyEntity company;

    public PricePlanDto pricePlanDto(){
        return PricePlanDto.builder()
                .id(id)
                .unitRate(unitRate)
                .companyName(company.getCompanyName())
                .build();
    }
}
