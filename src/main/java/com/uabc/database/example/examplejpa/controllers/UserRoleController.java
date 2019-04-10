package com.uabc.database.example.examplejpa.controllers;


import com.uabc.database.example.examplejpa.constant.ViewConstant;
import com.uabc.database.example.examplejpa.model.UserModel;
import com.uabc.database.example.examplejpa.model.UserRoleModel;
import com.uabc.database.example.examplejpa.services.UserRoleService;
import com.uabc.database.example.examplejpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/userRoles")
public class UserRoleController {
    @Autowired
    @Qualifier("userRoleServiceImpl")
    private UserRoleService userRoleServices;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/userRoles/showUserRoles";
    }

    @GetMapping("/userRoleForm")
    public String redirectUserRoleForm(Model model, @RequestParam(name = "userRoleId", required = false)Integer userRoleId){
        UserRoleModel userRoleModel = new UserRoleModel();

        //STACK OVERFLOW
        List<UserModel> users = userService.listAllUsers();

        if(userRoleId != 0)
            userRoleModel = userRoleServices.findUserRoleModelById(userRoleId);

        model.addAttribute("userRoleModel",userRoleModel);
        model.addAttribute("users",users);

        return ViewConstant.USER_ROLE_FORM;
    }

    @PostMapping("/addUserRole")
    public String addUserRole(Model model, @ModelAttribute(name = "userRoleModel")UserRoleModel userRoleModel){
        if(userRoleServices.addUserRole(userRoleModel) != null)
            model.addAttribute("result",0);
        else
            model.addAttribute("result",1);

        return "redirect:/userRoles/showUserRoles";
    }

    @GetMapping("/showUserRoles")
    public ModelAndView showUserRoles(){
        ModelAndView mav = new ModelAndView(ViewConstant.USER_ROLES);

        mav.addObject("userRoles",userRoleServices.listAllUserRoleModels());

        return mav;
    }

    @GetMapping("/removeUserRole")
    public ModelAndView removeUserRole(@RequestParam(name = "userRoleId", required = true)Integer userRoleId){
        userRoleServices.removeUserRole(userRoleId);

        return showUserRoles();
    }
}
