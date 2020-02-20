package com.bhanuchaddha.myenergy.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "SMART_METER")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SmartMeterEntity {


    // Not auto generated to keep it simple. Else custom sequence generator could be used to achieve smart-meter-{} like ids.
    @Id
    @Column(name = "METER_ID")
    private String id;

    @OneToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "id")
    private PricePlanEntity pricePlan;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "METER_ID")
    private Set<MeterReadingEntity> meterReadings;

    @Embedded
    private CustomerEntity customer;

    // There could be one to many relation between Customer and meter but for simplicity assuming one to one relation.
    @Embeddable
    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class CustomerEntity {
        private String name;
    }


}
