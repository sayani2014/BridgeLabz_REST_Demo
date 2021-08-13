package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //RequestBody, RequestHeader, PathVariable, RequestParam
    @RequestMapping(value = "/create", method = RequestMethod.POST,
                                                consumes = "application/json",
                                                produces = "application/json")
    // OR @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addUser(@Valid @RequestBody UserDTO userDTO,
                                               BindingResult ex) {
        if(ex.hasErrors()) {
            List<String> error = ex.getAllErrors().stream().map(objectError -> {
                return objectError.getDefaultMessage();
            }).collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseDTO("Validation error" , error),
                                                                            HttpStatus.BAD_REQUEST);
        }
        User user = this.userService.addUser(userDTO);
        return new ResponseEntity<>(new ResponseDTO("Created User successfully" , userDTO),
                                                                            HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllUser() {
        List<User> users = this.userService.getAllUser();
        return new ResponseEntity<>(new ResponseDTO("All Users", users), HttpStatus.OK);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT,
                                            consumes = "application/json",
                                            produces = "application/json")
    public ResponseEntity<ResponseDTO> updateUser(@Valid @RequestBody UserDTO userDTO,
                                                    @PathVariable("id") int id) {
        User user = this.userService.updateUser(userDTO, id);
        return new ResponseEntity<>(new ResponseDTO("Updating Users", user), HttpStatus.OK);
    }
}
