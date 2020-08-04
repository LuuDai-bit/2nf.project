package com.example.controller.web;

import com.example.constant.SystemConstant;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UnitPriceController {
    @Autowired
    private IUnitPriceService unitPriceService;

    @RequestMapping(value = "/unitPrice/{id}", method = RequestMethod.GET)
    public UnitPriceDTO getUnitPrice(@PathVariable Long id){
        UnitPriceDTO unitPriceDTO = unitPriceService.getOneUnitPriceById(id);
        return unitPriceDTO;
    }

    @RequestMapping(value="/addUnitPrice", method= RequestMethod.POST)
    @ResponseBody
//    @PostMapping(value = "/addUnitPrice")
    public String submit(@RequestBody UnitPriceDTO unitPriceDTO) {
        unitPriceService.saveUnitPrice(unitPriceDTO);
        return "success";
    }

    @RequestMapping(value="/unitPrice/list", method = RequestMethod.GET)
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

    @RequestMapping(value="/deleteUnitPrices", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUnitPrices(@RequestBody ListDTO checkedUnitPrices){
        if(checkedUnitPrices.getItems().isEmpty())
            return new ResponseEntity("not found", HttpStatus.BAD_REQUEST);

        unitPriceService.deleteUnitPrices(checkedUnitPrices.getItems());
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @RequestMapping(value="/editUnitPrice/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String updateUnitPrice(UnitPriceDTO unitPriceDTO,@PathVariable Long id) {
        unitPriceDTO.setId(id);
        unitPriceService.saveUnitPrice(unitPriceDTO);
        return "success";
    }
}
