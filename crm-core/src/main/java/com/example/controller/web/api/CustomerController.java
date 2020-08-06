package com.example.controller.web.api;

import com.example.constant.SystemConstant;
import com.example.dto.CustomerDTO;
import com.example.dto.CustomerDTO;
import com.example.entity.other.ListDTO;
import com.example.paging.PageRequest;
import com.example.paging.Pageable;
import com.example.service.ICustomerService;
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
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

//    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @GetMapping(value = "/customer/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id){
        CustomerDTO customerDTO = customerService.getOneCustomerById(id);
        return customerDTO;
    }

//    @RequestMapping(value="/addCustomer", method= RequestMethod.POST)
//    @ResponseBody

    @PostMapping(value = "/addCustomer")
    public String submit(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
        return "success";
    }

//    @RequestMapping(value="/customer/list", method = RequestMethod.GET)
    @GetMapping(value = "/customer/list")
    public ModelAndView getCustomers(@ModelAttribute(SystemConstant.CUSTOMER) CustomerDTO model,
                                     HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/customer/list");
        Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItems());

        List<CustomerDTO> customers = customerService.searchCustomers(model, pageable);
        int totalCustomers = customerService.getTotalItems(model);
        model.setListResult(customers);
        model.setTotalItems(totalCustomers);
        mav.addObject(SystemConstant.CUSTOMER, model);
        return mav;
    }

//    @RequestMapping(value="/deleteCustomers", method=RequestMethod.DELETE)
    @DeleteMapping(value = "/deleteCustomers")
    public ResponseEntity<?> deleteCustomers(@RequestBody ListDTO checkedCustomers){
        if(checkedCustomers.getItems().isEmpty())
            return new ResponseEntity("not found", HttpStatus.BAD_REQUEST);

        customerService.deleteCustomers(checkedCustomers.getItems());
        return new ResponseEntity("success", HttpStatus.OK);
    }

//    @RequestMapping(value="/editCustomer/{id}", method = RequestMethod.POST)
//    @ResponseBody
    @PostMapping(value = "/editCustomer/{id}")
    public String updateCustomer(CustomerDTO customerDTO,@PathVariable Long id) {
        customerDTO.setId(id);
        customerService.saveCustomer(customerDTO);
        return "success";
    }

//    @RequestMapping(value = "/customer/export", method = RequestMethod.GET)
    @GetMapping(value = "/customer/export")
    public void exportCustomerToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String fileName = "customers.csv";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;

        response.setHeader(headerKey, headerValue);

        List<CustomerDTO> customers = customerService.getAllCustomers();

        //Support utf-8
        Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
        writer.write('\uFEFF'); // BOM for UTF-*

        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"Name", "Email", "Phone"};

        String[] nameMapping = {"name", "email", "phone"};

        csvWriter.writeHeader(csvHeader);
        for(CustomerDTO customerDTO : customers){
            csvWriter.write(customerDTO, nameMapping);
        }
        csvWriter.close();
    }
}
