package com.cashlez.demo.controller;

import com.cashlez.demo.dto.general.GeneralResponse;
import com.cashlez.demo.model.Role;
import com.cashlez.demo.model.User;
import com.cashlez.demo.service.RoleService;
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
    public ResponseEntity<GeneralResponse> getAllRoleController( @RequestParam(defaultValue = "0") Integer pageNo,
                                                           @RequestParam(defaultValue = "10") Integer pageSize) {
        return new ResponseEntity<>(userService.getAllUser(pageNo, pageSize), HttpStatus.OK);
    }

    @PostMapping("/add_user")
    public ResponseEntity<GeneralResponse> addRoleController(@RequestBody User user){
        return new ResponseEntity<>(userService.addNewUser(user), HttpStatus.OK);
    }
}
