package com.uabc.database.example.examplejpa.services.impl;

import com.uabc.database.example.examplejpa.components.UserRoleConverter;
import com.uabc.database.example.examplejpa.entity.UserRole;
import com.uabc.database.example.examplejpa.model.UserRoleModel;
import com.uabc.database.example.examplejpa.respository.UserRoleRepository;
import com.uabc.database.example.examplejpa.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userRoleServiceImpl")
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    @Qualifier("userRoleRepository")
    private UserRoleRepository userRoleRepository;

    @Autowired
    @Qualifier("userRoleConverter")
    private UserRoleConverter userRoleConverter;

    @Override
    public UserRoleModel addUserRole(UserRoleModel userRoleModel) {
        UserRole temp = userRoleConverter.convertUserRoleModelToUserRole(userRoleModel);
        UserRole userRole = userRoleRepository.save(temp);

        return userRoleConverter.convertUserRoleToUserRoleModel(userRole);
    }

    @Override
    public List<UserRoleModel> listAllUserRoleModels() {
        List<UserRole> userRoles = userRoleRepository.findAll();
        List<UserRoleModel> userRoleModels = new ArrayList<>();

        for (UserRole userRole: userRoles) {
            userRoleModels.add(userRoleConverter.convertUserRoleToUserRoleModel(userRole));
        }

        return userRoleModels;
    }

    @Override
    public UserRole findUserRoleById(int id) {
        return userRoleRepository.findByUserRoleId(id);
    }

    @Override
    public void removeUserRole(int id) {
        UserRole userRole = userRoleRepository.findByUserRoleId(id);

        if(userRole != null)
            userRoleRepository.delete(userRole);
    }

    @Override
    public UserRoleModel findUserRoleModelById(int id) {
        return userRoleConverter.convertUserRoleToUserRoleModel(findUserRoleById(id));
    }
}
