package com.example.service.impl;
import com.example.converter.BuildingConverter;
import com.example.dto.BuildingDTO;
import com.example.entity.BuildingEntity;
import com.example.paging.Pageable;
import com.example.repository.IBuildingRepository;
import com.example.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService implements IBuildingService {
    @Autowired
    private IBuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Override
    public BuildingDTO getOneBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findOne(id);
        BuildingDTO buildingDTO = buildingConverter.convertToDto(buildingEntity);
        return buildingDTO;
    }

    @Override
    public List<BuildingDTO> searchBuildings(BuildingDTO modelSearch, Pageable pageable) {

        List<?> buildingEntities = new ArrayList<>();
        List<BuildingDTO> result = new ArrayList<>();

        buildingEntities = buildingRepository.findAll(modelSearch, pageable);
        for (Object item : buildingEntities) {
            BuildingEntity buildingEntity = new BuildingEntity();
            try {
                buildingEntity = (BuildingEntity) item;
            } catch (Exception e) {
                buildingEntity = (BuildingEntity) ((Object[]) item)[0];
            }
            BuildingDTO buildingDTO = buildingConverter.convertToDto(buildingEntity);

            result.add(buildingDTO);
        }

        return result;
    }

    @Override
    public int getTotalItems(BuildingDTO modelSearch) {
        int totalItem = 0;
        totalItem = buildingRepository.getTotalItems(modelSearch).intValue();
        return totalItem;
    }

    @Override
    public void saveBuilding(BuildingDTO buildingDto) {
        if(buildingDto.getId() !=null && buildingRepository.findOne(buildingDto.getId()) != null){
            BuildingEntity buildingEntity = buildingRepository.findOne(buildingDto.getId());
            buildingEntity.setAvatar(buildingDto.getAvatar());
            buildingEntity.setCode(buildingDto.getCode());
            buildingEntity.setDistrict(buildingDto.getCode());
            buildingEntity.setLeasedArea(buildingDto.getLeasedArea());
            buildingEntity.setNote(buildingDto.getNote());
            buildingEntity.setRoomNumber(buildingDto.getRoomNumber());
            buildingEntity.setStreet(buildingDto.getCode());
            buildingEntity.setWard(buildingDto.getWard());
            buildingRepository.save(buildingEntity);
        }
        else {
            BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDto);
            buildingRepository.save(buildingEntity);
        }
    }

    @Override
    public void deleteBuildings(List<Long> buildings) {
        for(Long buildingId : buildings){
            buildingRepository.delete(buildingId);
        }
    }

    @Override
    public List<BuildingDTO> getAllBuildings() {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll();
        List<BuildingDTO> buildingDTOS = new ArrayList<>();
        for(BuildingEntity elem: buildingEntities){
            BuildingDTO buildingDTO = buildingConverter.convertToDto(elem);
            buildingDTOS.add(buildingDTO);
        }
        return buildingDTOS;
    }

    @Override
    public List<BuildingDTO> getAllBuildings(BuildingDTO buildingDTO) {
        List<BuildingEntity> buildingEntities = (List<BuildingEntity>) buildingRepository.findAllWithoutPageable(buildingDTO);
        List<BuildingDTO> buildingDTOS = new ArrayList<>();
        for(BuildingEntity elem: buildingEntities){
            BuildingDTO building = buildingConverter.convertToDto(elem);
            buildingDTOS.add(building);
        }
        return buildingDTOS;
    }
}
