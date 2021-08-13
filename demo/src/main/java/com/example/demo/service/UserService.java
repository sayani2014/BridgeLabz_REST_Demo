package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.UserException;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User addUser ( UserDTO userDTO ) {
        User user = new User(userDTO);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User updateUser(UserDTO userDTO, int id) {
        return this.userRepository.findById(id)
                .map(user -> {
                    user.setAge(userDTO.getAge());
                    user.setName(userDTO.getName());
                    user.setEmail(userDTO.getEmail());
                    user.setPhone(userDTO.getPhone());
                    user.setUpdatedDate(LocalDate.now());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserException("User Not Found",
                       UserException.ExceptionType.USER_NOT_FOUND));
    }

}
