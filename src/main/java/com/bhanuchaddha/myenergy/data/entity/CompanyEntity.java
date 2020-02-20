package com.bhanuchaddha.myenergy.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "COMPANY")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private Long companyId;
    private String companyName;

    @OneToMany(mappedBy="company")
    private Set<PricePlanEntity> pricePlanEntities;
}
