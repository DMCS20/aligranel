package com.group2.aligranel.user.controller;

import com.group2.aligranel.user.dto.request.UserLoginRequestDTO;
import com.group2.aligranel.user.dto.request.UserRequestDTO;
import com.group2.aligranel.user.dto.response.UserResponseDTO;
import com.group2.aligranel.user.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable ObjectId id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/users/register")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequest){
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@RequestBody UserRequestDTO userRequest, @PathVariable ObjectId id){
        userService.updateUser(userRequest, id);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable ObjectId id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User with ID" + id + "deleted successfully", HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/users/login")
    public ResponseEntity loginUser(@RequestBody UserLoginRequestDTO userLogin){
        userService.loginUser(userLogin);
        return new ResponseEntity<>("Login successfull" ,HttpStatus.OK);
    }
}
