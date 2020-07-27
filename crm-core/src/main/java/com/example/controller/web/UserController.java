package com.example.controller.web;

import com.example.constant.SystemConstant;
import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import com.example.entity.other.ListDTO;
import com.example.service.IUserService;
import com.example.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value="/user/list", method = RequestMethod.GET)
    public ModelAndView getUsers(@ModelAttribute(SystemConstant.MODEL) UserDTO model, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/home");
        DisplayTagUtils.initSearchBean(request, model);
        Pageable pageable = new PageRequest(model.getPage()-1, model.getMaxPageItems());
        List<UserDTO> users = userService.getUsers(pageable);
        model.setListResult(users);
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value="/deleteUsers", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUsers(@RequestBody ListDTO checkedUsers){
        if(checkedUsers.getItems().isEmpty())
            return new ResponseEntity("not found", HttpStatus.BAD_REQUEST);

        userService.deleteUsers(checkedUsers.getItems());
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @RequestMapping(value="/deleteUser/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Long> deleteUser(@PathVariable Long id){
        boolean isDelete = userService.deleteUser(id);
        if(!isDelete) return new ResponseEntity("not found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @RequestMapping(value="/editUser/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(UserDTO userDTO,@PathVariable Long id) {
        userDTO.setId(id);
        userService.saveUser(userDTO);
        return "success";
    }
}
