package com.cashlez.demo.controller;

import com.cashlez.demo.dto.SearchByRoleId;
import com.cashlez.demo.dto.general.GeneralResponse;
import com.cashlez.demo.model.Role;
import com.cashlez.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping()
    public ResponseEntity<GeneralResponse> getAllMerchant( @RequestParam(defaultValue = "0") Integer pageNo,
                                                           @RequestParam(defaultValue = "10") Integer pageSize) {
        return new ResponseEntity<>(roleService.getAllRole(pageNo, pageSize), HttpStatus.OK);
    }

    @PostMapping("/get_one_role")
    public ResponseEntity<GeneralResponse> getOneMerchant (@RequestBody SearchByRoleId searchByRoleId){
        return new ResponseEntity<>(roleService.getOneRole(searchByRoleId.getRoleId()), HttpStatus.OK);
    }

    @PostMapping("/add_role")
    public ResponseEntity<GeneralResponse> addRoleController(@RequestBody Role role){
        return new ResponseEntity<>(roleService.addNewRole(role), HttpStatus.OK);
    }

    @PostMapping("/update_role")
    public ResponseEntity<GeneralResponse> updateRoleController(@RequestBody Role role){
        return new ResponseEntity<>(roleService.updateRole(role), HttpStatus.OK);
    }

    @PostMapping("/delete_role")
    public ResponseEntity<GeneralResponse> deleteMerchantController (@RequestBody SearchByRoleId searchByRoleId){
        return new ResponseEntity<>(roleService.deleteRole(searchByRoleId.getRoleId()), HttpStatus.OK);
    }

}
