package com.example.controller.web;

import com.example.dto.RoleDTO;
import com.example.entity.RoleEntity;
import com.example.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public RoleDTO getRole(@PathVariable Long id){
        RoleDTO roleDTO = roleService.getOneRoleById(id);
        return roleDTO;
    }
}
