package com.cashlez.demo.service;

import com.cashlez.demo.dto.MerchantStatus;
import com.cashlez.demo.dto.UserDTO;
import com.cashlez.demo.dto.UserStatus;
import com.cashlez.demo.dto.general.GeneralResponse;
import com.cashlez.demo.model.Merchant;
import com.cashlez.demo.model.Role;
import com.cashlez.demo.model.User;
import com.cashlez.demo.repo.UserRepository;
import com.cashlez.demo.util.ObjectMapperUtils;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository ;
    }

    public GeneralResponse getAllUser(Integer pageNo , Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> pageMerchant = userRepository.findAll(pageable);

        List<UserDTO> listOfUserDTO = ObjectMapperUtils.mapAll(pageMerchant.getContent(), UserDTO.class);

        Map<String, Object> meta = new HashMap<>();
        meta.put("page", pageMerchant.getNumber());
        meta.put("size", pageMerchant.getSize());
        meta.put("totalData", pageMerchant.getTotalElements());
        meta.put("totalDataOnPage", pageMerchant.getNumberOfElements());

        Map<String, Object> newData = new HashMap<>();
        newData.put("data", listOfUserDTO);
        newData.put("meta", meta);

        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.success("200", "List Of User Account", newData );
        return generalResponse;
    }


    public GeneralResponse addNewUser (User user){
        GeneralResponse generalResponse = new GeneralResponse();

//        String hashedPassword = Hashing.sha256()
//                .hashString(user.getPassHash(), StandardCharsets.UTF_8)
//                .toString();
//        user.setPassHash(hashedPassword); ;
        userRepository.save(user);
        generalResponse.success("201", "Success created Role !", user);

        return generalResponse;
    }

    public GeneralResponse getOneUser (long userId){
        GeneralResponse generalResponse = new GeneralResponse();

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            generalResponse.success("200", "Detail of user !", userOptional);
        }else  {
            generalResponse.fail("404", "User not found !");
        }
        return generalResponse;
    }

    public GeneralResponse deleteUser (long userId){

        Date date = new Date();

        User deletedUser;

        GeneralResponse generalResponse = new GeneralResponse();

        Optional<User> userOptional = userRepository.findByIdAndStatus(userId, UserStatus.ACTIVE);

        if (userOptional.isPresent()){
            deletedUser = userOptional.get();
            deletedUser.setModifiedDate(date);
            deletedUser.setStatus(UserStatus.DELETED);
            userRepository.save(deletedUser);
            generalResponse.success("200", "Success delete user !", deletedUser);
        }else {
            generalResponse.fail("404", "User not found !");
        }
        return generalResponse;
    }

    public GeneralResponse updateUser (User user){
        Date date = new Date();
        Optional<User> roleOptional = userRepository.findById(user.getId());

        GeneralResponse generalResponse = new GeneralResponse();

        User newUser = new User();
        if (roleOptional.isPresent()){
            newUser = roleOptional.get();
            newUser.setModifiedDate(date);
            if (newUser.getUserName() != null){
                newUser.setUserName(user.getUserName());
            }
            if (newUser.getPhoneNumber() != null){
                newUser.setPhoneNumber(user.getPhoneNumber());
            }
            if (newUser.getRole() != null){
                newUser.setRole(user.getRole());
            }
            userRepository.save(newUser);
            generalResponse.success("200","Success update user !", newUser);
        }else {
            generalResponse.fail("404", "User not found !");
        }
        return generalResponse;
    }
}
