package com.example.controller.web;

import com.example.constant.SystemConstant;
import com.example.dto.RoleDTO;
import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import com.example.service.IRoleService;
import com.example.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    private final Logger logger = LoggerFactory.getLogger(WebController.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping(value="/homepage", method = RequestMethod.GET)
    public ModelAndView homePage(){
        ModelAndView mav = new ModelAndView("web/home");

        mav.addObject(SystemConstant.MODEL, userService.getAllUsers());
        return mav;
    }

    @RequestMapping(value="/adduserpage", method = RequestMethod.GET)
    public ModelAndView addUserPage(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("admin/user/user_add");
        mav.addObject("roles", roleService.getAllRoles());
        if(id<0) return mav;
        UserDTO userDTO = userService.getUserById(id);
        mav.addObject(SystemConstant.MODEL , userDTO);
        return mav;
    }

    @RequestMapping(value="/role/add/page", method = RequestMethod.GET)
    public ModelAndView addRolePage(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("admin/role/add");
        if(id<0) return mav;
        RoleDTO roleDTO = roleService.getOneRoleById(id);
        mav.addObject(SystemConstant.ROLE , roleDTO);
        return mav;
    }
}
