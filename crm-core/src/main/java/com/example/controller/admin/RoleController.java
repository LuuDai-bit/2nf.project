package com.example.controller.admin;

import com.example.constant.SystemConstant;
import com.example.dto.RoleDTO;
import com.example.dto.UnitPriceDTO;
import com.example.entity.other.ListDTO;
import com.example.paging.PageRequest;
import com.example.paging.Pageable;
import com.example.service.IRoleService;
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
import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public RoleDTO getRole(@PathVariable Long id){
        RoleDTO roleDTO = roleService.getOneRoleById(id);
        return roleDTO;
    }

    @RequestMapping(value="/addRole", method= RequestMethod.POST)
    @ResponseBody
    public String submit(@RequestBody RoleDTO roleDTO) {
        roleService.saveRole(roleDTO);
        return "success";
    }

    @RequestMapping(value="/role/list", method = RequestMethod.GET)
    public ModelAndView getRoles(@ModelAttribute(SystemConstant.MODEL) RoleDTO model,
                                 HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/role/list");
        Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItems());

        List<RoleDTO> roles = roleService.searchRoles(model, pageable);
        int totalRoles = roleService.getTotalItems(model);
        model.setListResult(roles);
        model.setTotalItems(totalRoles);
        mav.addObject(SystemConstant.ROLE, model);
        return mav;
    }

    @RequestMapping(value="/deleteRoles", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteRoles(@RequestBody ListDTO checkedRoles){
        if(checkedRoles.getItems().isEmpty())
            return new ResponseEntity("not found", HttpStatus.BAD_REQUEST);

        roleService.deleteRoles(checkedRoles.getItems());
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @RequestMapping(value="/editRole/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String updateRole(RoleDTO RoleDTO,@PathVariable Long id) {
        RoleDTO.setId(id);
        roleService.saveRole(RoleDTO);
        return "success";
    }

    @RequestMapping(value = "/role/export", method = RequestMethod.GET)
    public void exportRoleToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String fileName = "roles.csv";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;

        response.setHeader(headerKey, headerValue);

        List<RoleDTO> roles = roleService.getAllRoles();

        //Support utf-8

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"Name", "Code"};

        String[] nameMapping = {"name", "code"};

        csvWriter.writeHeader(csvHeader);
        for(RoleDTO roleDTO : roles){
            csvWriter.write(roleDTO, nameMapping);
        }
        csvWriter.close();
    }
}
