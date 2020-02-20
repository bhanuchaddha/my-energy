package com.bhanuchaddha.myenergy.data.repository;

import com.bhanuchaddha.myenergy.data.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository< CompanyEntity, Long> {
}
