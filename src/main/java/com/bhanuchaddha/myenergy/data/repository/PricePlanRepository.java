package com.bhanuchaddha.myenergy.data.repository;

import com.bhanuchaddha.myenergy.data.entity.PricePlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricePlanRepository extends JpaRepository<PricePlanEntity, String> {
}
