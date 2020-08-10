package com.example.controller.web;

import com.example.constant.SystemConstant;
import com.example.dto.*;
import com.example.paging.PageRequest;
import com.example.paging.Pageable;
import com.example.service.*;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class WebController {

    private final Logger logger = LoggerFactory.getLogger(WebController.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IUnitPriceService unitPriceService;
    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value="/homepage", method = RequestMethod.GET)
    public ModelAndView homePage(){
        ModelAndView mav = new ModelAndView("web/home");
        UserDTO userDTO = new UserDTO();
        Pageable pageable = new PageRequest(userDTO.getPage(), userDTO.getMaxPageItems());
        mav.addObject(SystemConstant.MODEL, userService.searchUsers(userDTO, pageable));
        return mav;
    }

    @RequestMapping(value="/add/user/page", method = RequestMethod.GET)
    public ModelAndView addUserPage(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("admin/user/user_add");
        mav.addObject("roles", roleService.getAllRoles());
        if(id<0) return mav;
        UserDTO userDTO = userService.getUserById(id);
        mav.addObject(SystemConstant.MODEL , userDTO);
        return mav;
    }

    @RequestMapping(value="/add/role/page", method = RequestMethod.GET)
    public ModelAndView addRolePage(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("admin/role/add");
        if(id<0) return mav;
        RoleDTO roleDTO = roleService.getOneRoleById(id);
        mav.addObject(SystemConstant.ROLE , roleDTO);
        return mav;
    }

    @RequestMapping(value="/add/building/page", method = RequestMethod.GET)
    public ModelAndView addBuildingPage(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("web/building/add");
        if(id<0) return mav;
        BuildingDTO buildingDTO = buildingService.getOneBuildingById(id);
        mav.addObject(SystemConstant.BUILDING , buildingDTO);
        return mav;
    }

    @RequestMapping(value="/add/customer/page", method = RequestMethod.GET)
    public ModelAndView addCustomerPage(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("web/customer/add");

        mav.addObject(SystemConstant.MODEL, userService.getAllUsers());
        CustomerDTO customerDTO = new CustomerDTO();

        if(id<0) return mav;
        customerDTO = customerService.getOneCustomerById(id);
        mav.addObject(SystemConstant.CUSTOMER , customerDTO);
        return mav;
    }

    @RequestMapping(value="/add/unitPrice/page", method = RequestMethod.GET)
    public ModelAndView addUnitPricePage(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("web/unit_price/add");
        UnitPriceDTO unitPriceDTO = new UnitPriceDTO();
        mav.addObject("buildings", buildingService.getAllBuildings());
        if(id<0) return mav;
        unitPriceDTO = unitPriceService.getOneUnitPriceById(id);
        mav.addObject(SystemConstant.UNITPRICE , unitPriceDTO);
        return mav;
    }

    @RequestMapping(value="/add/payment/page", method = RequestMethod.GET)
    public ModelAndView addPaymentPage(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("web/payment/add");

        //consume getAll()
        PaymentDTO paymentDTO = new PaymentDTO();

        if(id<0) return mav;
        paymentDTO = paymentService.getOnePaymentById(id);
        mav.addObject(SystemConstant.PAYMENT , paymentDTO);
        return mav;
    }

    @RequestMapping(value = "/image-response-entity", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity(@RequestParam("ava") String pictureName) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        String dir = "E:\\pictures\\";
        FileInputStream fin = new FileInputStream(dir+pictureName);
        byte[] media = IOUtils.toByteArray(fin);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }
}
