package com.example.controller.web;

import com.example.dto.UserDTO;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value="/addUser", method= RequestMethod.POST)
    @ResponseBody
    public String submit(UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "success";
    }

}
