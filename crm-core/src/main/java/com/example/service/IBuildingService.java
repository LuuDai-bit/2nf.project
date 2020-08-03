package com.example.service;

import com.example.dto.BuildingDTO;
import com.example.paging.Pageable;

import java.util.List;

public interface IBuildingService {
    BuildingDTO getOneBuildingById(Long id);
    List<BuildingDTO> searchBuildings(BuildingDTO modelSearch, Pageable pageable);
    int getTotalItems(BuildingDTO modelSearch);
    void saveBuilding(BuildingDTO building);
    void deleteBuildings(List<Long> buildings);
}
