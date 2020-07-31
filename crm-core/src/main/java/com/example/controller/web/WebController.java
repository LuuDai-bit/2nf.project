package com.example.controller.web;

import com.example.constant.SystemConstant;
import com.example.dto.UserDTO;
import com.example.service.IRoleService;
import com.example.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    private final Logger logger = LoggerFactory.getLogger(WebController.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

//    @RequestMapping(value="/homepage", method = RequestMethod.GET)
//    public ModelAndView homePage(){
//        ModelAndView mav = new ModelAndView("web/home");
//
//        mav.addObject(SystemConstant.MODEL, userService.getAllUsers());
//        return mav;
//    }

    @RequestMapping(value="/adduserpage", method = RequestMethod.GET)
    public ModelAndView addUserPage(){
        ModelAndView mav = new ModelAndView("web/user_add");
        mav.addObject(SystemConstant.MODEL , new UserDTO());
        mav.addObject("roles", roleService.getAllRoles());
        return mav;
    }
}
