package com.example.repository;

import com.example.entity.UnitPriceEntity;
import com.example.repository.custom.UnitPriceRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUnitPriceRepository extends JpaRepository<UnitPriceEntity, Long>, UnitPriceRepositoryCustom {
}
