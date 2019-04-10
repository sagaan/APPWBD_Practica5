package com.uabc.database.example.examplejpa.services.impl;

import com.uabc.database.example.examplejpa.components.UserConverter;
import com.uabc.database.example.examplejpa.entity.User;
import com.uabc.database.example.examplejpa.model.UserModel;
import com.uabc.database.example.examplejpa.respository.UserRepository;
import com.uabc.database.example.examplejpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("userConverter")
    private UserConverter userConverter;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserModel addUser(UserModel userModel) {
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        User temp = userConverter.convertUserModelToUser(userModel);
        User usuario = userRepository.save(temp);
        return userConverter.convertUserToUserModel(usuario);
    }

    @Override
    public List<UserModel> listAllUsers() {
        List<User> usuarios = userRepository.findAll();
        List<UserModel> userModels = new ArrayList<>();
        for (User usuario: usuarios) {
            userModels.add(userConverter.convertUserToUserModel(usuario));
        }
        return userModels;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void removeUser(String username) {
        User user = findUserByUsername(username);
        if(user != null)
            userRepository.delete(user);
    }

    @Override
    public UserModel findUserModelByUsername(String username) {
        return userConverter.convertUserToUserModel(findUserByUsername(username));
    }
}
