package com.example.controller.web;

import com.example.constant.SystemConstant;
import com.example.dto.BuildingDTO;
import com.example.dto.UserDTO;
import com.example.entity.BuildingEntity;
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
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
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

    @RequestMapping(value = "/building/export", method = RequestMethod.GET)
    public void exportBuildingToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String fileName = "buildings.csv";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;

        response.setHeader(headerKey, headerValue);

        List<BuildingDTO> buildings = buildingService.getAllBuildings();

        //Support utf-8

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"Code", "District", "Street", "Ward", "Leased Area", "Room Number", "Note"};

        String[] nameMapping = {"code", "district", "street", "ward", "leasedArea", "roomNumber", "note"};

        csvWriter.writeHeader(csvHeader);
        for(BuildingDTO buildingDTO : buildings){
            csvWriter.write(buildingDTO, nameMapping);
        }
        csvWriter.close();
    }
}
