package com.example.controller.web;

import com.example.constant.SystemConstant;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public CustomerDTO getCustomer(@PathVariable Long id){
        CustomerDTO customerDTO = customerService.getOneCustomerById(id);
        return customerDTO;
    }

    @RequestMapping(value="/addCustomer", method= RequestMethod.POST)
    @ResponseBody
//    @PostMapping(value = "/addCustomer")
    public String submit(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
        return "success";
    }

    @RequestMapping(value="/customer/list", method = RequestMethod.GET)
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

    @RequestMapping(value="/deleteCustomers", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomers(@RequestBody ListDTO checkedCustomers){
        if(checkedCustomers.getItems().isEmpty())
            return new ResponseEntity("not found", HttpStatus.BAD_REQUEST);

        customerService.deleteCustomers(checkedCustomers.getItems());
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @RequestMapping(value="/editCustomer/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String updateCustomer(CustomerDTO customerDTO,@PathVariable Long id) {
        customerDTO.setId(id);
        customerService.saveCustomer(customerDTO);
        return "success";
    }
}
