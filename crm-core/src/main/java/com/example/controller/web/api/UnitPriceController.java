package com.example.controller.web.api;

import com.example.constant.SystemConstant;
import com.example.dto.BuildingDTO;
import com.example.dto.UnitPriceDTO;
import com.example.entity.other.ListDTO;
import com.example.paging.PageRequest;
import com.example.paging.Pageable;
import com.example.service.IUnitPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class UnitPriceController {
    @Autowired
    private IUnitPriceService unitPriceService;

//    @RequestMapping(value = "/unitPrice/{id}", method = RequestMethod.GET)
    @GetMapping(value = "/unitPrice/{id}")
    public UnitPriceDTO getUnitPrice(@PathVariable Long id){
        UnitPriceDTO unitPriceDTO = unitPriceService.getOneUnitPriceById(id);
        return unitPriceDTO;
    }

//    @RequestMapping(value="/addUnitPrice", method= RequestMethod.POST)
//    @ResponseBody
    @PostMapping(value = "/addUnitPrice")
    public String submit(@RequestBody UnitPriceDTO unitPriceDTO) {
        unitPriceService.saveUnitPrice(unitPriceDTO);
        return "success";
    }

//    @RequestMapping(value="/unitPrice/list", method = RequestMethod.GET)
    @GetMapping(value = "/unitPrice/list")
    public ModelAndView getUnitPrices(@ModelAttribute(SystemConstant.UNITPRICE) UnitPriceDTO model,
                                     HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/unit_price/list");
        Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItems());

        List<UnitPriceDTO> unitPrices = unitPriceService.searchUnitPrices(model, pageable);
        int totalUnitPrices = unitPriceService.getTotalItems(model);
        model.setListResult(unitPrices);
        model.setTotalItems(totalUnitPrices);
        mav.addObject(SystemConstant.UNITPRICE, model);
        return mav;
    }

//    @RequestMapping(value="/deleteUnitPrices", method=RequestMethod.DELETE)
    @DeleteMapping(value = "/deleteUnitPrices")
    public ResponseEntity<?> deleteUnitPrices(@RequestBody ListDTO checkedUnitPrices){
        if(checkedUnitPrices.getItems().isEmpty())
            return new ResponseEntity("not found", HttpStatus.BAD_REQUEST);

        unitPriceService.deleteUnitPrices(checkedUnitPrices.getItems());
        return new ResponseEntity("success", HttpStatus.OK);
    }

//    @RequestMapping(value="/editUnitPrice/{id}", method = RequestMethod.POST)
//    @ResponseBody
    @PostMapping(value = "/editUnitPrice/{id}")
    public String updateUnitPrice(UnitPriceDTO unitPriceDTO,@PathVariable Long id) {
        unitPriceDTO.setId(id);
        unitPriceService.saveUnitPrice(unitPriceDTO);
        return "success";
    }

//    @RequestMapping(value = "/unitPrice/export", method = RequestMethod.GET)
    @GetMapping(value = "/unitPrice/export")
    public void exportUnitPriceToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String fileName = "UnitPrices.csv";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;

        response.setHeader(headerKey, headerValue);

        List<UnitPriceDTO> unitPrices = unitPriceService.getAllUnitPrices();

        //Support utf-8
        Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
        writer.write('\uFEFF'); // BOM for UTF-*

        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"Electricity Price", "Water Price", "Room Price", "Date"};

        String[] nameMapping = {"electricityPrice", "waterPrice", "roomPrice", "date"};

        csvWriter.writeHeader(csvHeader);
        for(UnitPriceDTO unitPriceDTO : unitPrices){
            csvWriter.write(unitPriceDTO, nameMapping);
        }
        csvWriter.close();
    }
}
