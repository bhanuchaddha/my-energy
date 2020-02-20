package com.bhanuchaddha.myenergy.data.repository;

import com.bhanuchaddha.myenergy.data.entity.SmartMeterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmartMeterRepository extends JpaRepository<SmartMeterEntity, String> {
}
