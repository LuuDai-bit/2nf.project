package com.example.controller.web.api;

import com.example.constant.SystemConstant;
import com.example.dto.UserDTO;
import com.example.entity.other.ListDTO;
import com.example.service.IRoleService;
import com.example.service.IUserService;
import com.example.utils.MessageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value="/addUser", method= RequestMethod.POST)
    @ResponseBody
//    @PostMapping(value = "/addUser")
    public String submit(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "success";
    }

    @RequestMapping(value="/user/list", method = RequestMethod.GET)
    public ModelAndView getUsers(@ModelAttribute(SystemConstant.MODEL) UserDTO model,
                                 HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/user/list");

        List<UserDTO> users = userService.searchUsers(model);
        int totalUsers = userService.getTotalItems(model);
        model.setListResult(users);
        model.setTotalItems(totalUsers);
        mav.addObject(SystemConstant.MODEL, model);
        mav.addObject(SystemConstant.MODEL2, roleService.getAllRoles());
        return mav;
    }

    @RequestMapping(value="/deleteUsers", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUsers(@RequestBody ListDTO checkedUsers){
        if(checkedUsers.getItems().isEmpty())
            return new ResponseEntity("not found", HttpStatus.BAD_REQUEST);

        userService.deleteUsers(checkedUsers.getItems());
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
