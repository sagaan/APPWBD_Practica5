package com.uabc.database.example.examplejpa.components;

import com.uabc.database.example.examplejpa.entity.UserRole;
import com.uabc.database.example.examplejpa.model.UserRoleModel;
import com.uabc.database.example.examplejpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userRoleConverter")
public class UserRoleConverter {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    public UserRole convertUserRoleModelToUserRole(UserRoleModel userRoleModel){
        UserRole userRole = new UserRole();

        userRole.setUserRoleId(userRoleModel.getUserRoleId());
        userRole.setUser(userService.findUserByUsername(userRoleModel.getUser()));
        userRole.setRole(userRoleModel.getRole());

        return userRole;
    }

    public UserRoleModel convertUserRoleToUserRoleModel(UserRole userRole){
        UserRoleModel userRoleModel = new UserRoleModel();

        userRoleModel.setUserRoleId(userRole.getUserRoleId());
        userRoleModel.setUser(userRole.getUser().getUsername());
        userRoleModel.setRole(userRole.getRole());

        return userRoleModel;
    }
}
