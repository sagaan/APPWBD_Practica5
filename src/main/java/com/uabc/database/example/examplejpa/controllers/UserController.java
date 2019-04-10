package com.uabc.database.example.examplejpa.controllers;

import com.uabc.database.example.examplejpa.constant.ViewConstant;
import com.uabc.database.example.examplejpa.model.UserModel;
import com.uabc.database.example.examplejpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @GetMapping("/cancel")
    public String cancel(){return "redirect:/users/showUsers";}

    @GetMapping("/userForm")
    public String redirectUserForm(Model model, @RequestParam(value = "username", required = false) String username){
        UserModel userModel = new UserModel();
        //Booleano para ver si se quiere editar o no.
        //Si se va a editar, se tiene que bloquear el username, ya que al ser el ID no se puede editar
        boolean edit = false;

        //Si no esta vacio, se traera los datos correspondientes al username y lo mostrara.
        //Basicamente es para distinguir si se modificara o se registrara un nuevo usuario, sin tener
        //que hacer una vista diferente para cada funcion.
        if(username != null) {
            userModel = userService.findUserModelByUsername(username);
            edit = true;
        }
        model.addAttribute("userModel",userModel);
        model.addAttribute("edit", edit);

        return ViewConstant.USER_FORM;
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute(name = "userModel")UserModel userModel, Model model){

        if(userService.addUser(userModel) != null){
            //Registro exitoso
            model.addAttribute("result", 0);
        }else{
            //Falla en el registro
            model.addAttribute("result", 1);
        }

        return "redirect:/users/showUsers";
    }

    @GetMapping("/showUsers")
    public ModelAndView showUsers(){
        ModelAndView mav = new ModelAndView(ViewConstant.USERS);
        mav.addObject("users", userService.listAllUsers());
        return mav;
    }

    @GetMapping("/removeUser")
    public ModelAndView removeUser(String username){
        userService.removeUser(username);
        return showUsers();
    }
}
