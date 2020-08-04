package com.example.controller.web;

import com.example.constant.SystemConstant;
import com.example.dto.BuildingDTO;
import com.example.entity.other.ListDTO;
import com.example.paging.PageRequest;
import com.example.paging.Pageable;
import com.example.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BuildingController {
    @Autowired
    private IBuildingService buildingService;

    @RequestMapping(value = "/building/{id}", method = RequestMethod.GET)
    public BuildingDTO getBuilding(@PathVariable Long id){
        BuildingDTO buildingDTO = buildingService.getOneBuildingById(id);
        return buildingDTO;
    }

    @RequestMapping(value="/addBuilding", method= RequestMethod.POST)
    @ResponseBody
//    @PostMapping(value = "/addBuilding")
    public String submit(@RequestBody BuildingDTO buildingDTO) {
        buildingService.saveBuilding(buildingDTO);
        return "success";
    }

    @RequestMapping(value="/building/list", method = RequestMethod.GET)
    public ModelAndView getBuildings(@ModelAttribute(SystemConstant.BUILDING) BuildingDTO model,
                                 HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/building/list");
        Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItems());

        List<BuildingDTO> buildings = buildingService.searchBuildings(model, pageable);
        int totalBuildings = buildingService.getTotalItems(model);
        model.setListResult(buildings);
        model.setTotalItems(totalBuildings);
        mav.addObject(SystemConstant.BUILDING, model);
        return mav;
    }

    @RequestMapping(value="/deleteBuildings", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteBuildings(@RequestBody ListDTO checkedBuildings){
        if(checkedBuildings.getItems().isEmpty())
            return new ResponseEntity("not found", HttpStatus.BAD_REQUEST);

        buildingService.deleteBuildings(checkedBuildings.getItems());
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @RequestMapping(value="/editBuilding/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String updateBuilding(BuildingDTO buildingDTO,@PathVariable Long id) {
        buildingDTO.setId(id);
        buildingService.saveBuilding(buildingDTO);
        return "success";
    }
}
