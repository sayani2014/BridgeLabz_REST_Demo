package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

import java.util.List;

public interface IUserService {
    public User addUser(UserDTO userDTO);

    List<User> getAllUser();

    User updateUser(UserDTO userDTO, int id);
}
