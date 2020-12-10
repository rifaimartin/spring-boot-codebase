package com.cashlez.demo.controller;

import com.cashlez.demo.dto.SearchByUserId;
import com.cashlez.demo.dto.general.GeneralResponse;
import com.cashlez.demo.model.User;
import com.cashlez.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<GeneralResponse> getAllUserController(@RequestParam Integer pageNo,Integer pageSize,String keyword) {
        return new ResponseEntity<>(userService.getAllUser(pageNo, pageSize, keyword), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> loginUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.loginUser(user), HttpStatus.OK);
    }

    @PostMapping("/get_one_user")
    public ResponseEntity<GeneralResponse> getOneUser (@RequestBody SearchByUserId searchByUserId){
        return new ResponseEntity<>(userService.getOneUser(searchByUserId.getUserId()), HttpStatus.OK);
    }

    @PostMapping("/add_user")
    public ResponseEntity<GeneralResponse> addUserController(@RequestBody User user){
        return new ResponseEntity<>(userService.addNewUser(user), HttpStatus.OK);
    }

    @PostMapping("/update_user")
    public ResponseEntity<GeneralResponse> updateUserController(@RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @PostMapping("/activation")
    public ResponseEntity<GeneralResponse> activationUserController(@RequestBody User user){
        return new ResponseEntity<>(userService.activationUser(user), HttpStatus.OK);
    }

    @PostMapping("/delete_user")
    public ResponseEntity<GeneralResponse> deleteUserController (@RequestBody SearchByUserId searchByUserId){
        return new ResponseEntity<>(userService.deleteUser(searchByUserId.getUserId()), HttpStatus.OK);
    }
}
