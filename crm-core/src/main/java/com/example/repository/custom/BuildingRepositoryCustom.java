package com.example.repository.custom;

import com.example.dto.BuildingDTO;
import com.example.dto.RoleDTO;
import com.example.paging.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<?> findAll(BuildingDTO buildingDTO, Pageable pageable);
    List<?> findAllWithoutPageable(BuildingDTO buildingDTO);
    Long getTotalItems(BuildingDTO buildingDTO);
}
