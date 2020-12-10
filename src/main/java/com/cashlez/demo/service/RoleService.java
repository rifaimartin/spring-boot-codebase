package com.cashlez.demo.service;

import com.cashlez.demo.dto.general.GeneralResponse;
import com.cashlez.demo.model.Role;
import com.cashlez.demo.repo.RoleRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public GeneralResponse getAllRole(Integer pageNo , Integer pageSize, String keyword){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Role> dataRole = roleRepository.findAll(pageable, keyword);

        GeneralResponse generalResponse = new GeneralResponse();

        if (dataRole.getContent().size() == 0) {
            return generalResponse.fail("404", "Role is empity !");
        }

        Map<String, Object> meta = new HashMap<>();
        meta.put("page", dataRole.getNumber());
        meta.put("size", dataRole.getSize());
        meta.put("totalData", dataRole.getTotalElements());
        meta.put("totalDataOnPage", dataRole.getNumberOfElements());

        Map<String, Object> newData = new HashMap<>();
        newData.put("data", dataRole.getContent());
        newData.put("meta", meta);

        generalResponse.success("200", "List Of Role", newData);
        return generalResponse;
    }

    public GeneralResponse addNewRole (Role role){
        GeneralResponse generalResponse = new GeneralResponse();

        roleRepository.save(role);
        generalResponse.success("201", "Success created Role !", role);

        return generalResponse;
    }

    public GeneralResponse updateRole (Role role){
        Optional<Role> roleOptional = roleRepository.findById(role.getId());

        GeneralResponse generalResponse = new GeneralResponse();
        Date date = new Date();

        Role newRole;
        if (roleOptional.isPresent()){
            newRole = roleOptional.get();
            newRole.setModifiedDate(date);
            if (newRole.getName() != null){
                newRole.setName(role.getName());
            }
            if (role.getPermissions() != null){
                newRole.setPermissions(role.getPermissions());
            }
            roleRepository.save(newRole);
            generalResponse.success("200","Success update role !", newRole);
        }else {
            generalResponse.fail("404", "Role not found !");
        }
        return generalResponse;
    }

    public GeneralResponse deleteRole (long roleId){
        GeneralResponse generalResponse = new GeneralResponse();

        Optional<Role> merchantOptional = roleRepository.findById(roleId);

        if (merchantOptional.isPresent()){
            roleRepository.deleteById(merchantOptional.get().getId());
            generalResponse.success("200", "Success delete role !", null);
        }else {
            generalResponse.fail("404", "Role not found !");
        }
        return generalResponse;
    }

    public GeneralResponse getOneRole (long roleId){
        GeneralResponse generalResponse = new GeneralResponse();

        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isPresent()) {
            generalResponse.success("200", "Detail of role !", roleOptional);
        }else  {
            generalResponse.fail("404", "Role not found !");
        }
        return generalResponse;
    }
}
