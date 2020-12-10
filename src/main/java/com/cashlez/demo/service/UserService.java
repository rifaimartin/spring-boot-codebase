package com.cashlez.demo.service;

import com.cashlez.demo.dto.UserDTO;
import com.cashlez.demo.dto.UserStatus;
import com.cashlez.demo.dto.general.GeneralResponse;
import com.cashlez.demo.model.User;
import com.cashlez.demo.repo.UserRepository;
import com.cashlez.demo.util.JwtUtil;
import com.cashlez.demo.util.ObjectMapperUtils;
import com.cashlez.demo.util.response.CustomeResponseLoginUser;
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
    JwtUtil jwtTokenUtil;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository ;
    }

    public GeneralResponse getAllUser(Integer pageNo , Integer pageSize, String keyword){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        GeneralResponse generalResponse = new GeneralResponse();
        Page<User> dataUser = userRepository.findAll(pageable, keyword);

        if (dataUser.getContent().size() == 0) {
            return generalResponse.fail("404", "User is empity !");
        }

        List<UserDTO> listOfUserDTO = ObjectMapperUtils.mapAll(dataUser.getContent(), UserDTO.class);

        Map<String, Object> meta = new HashMap<>();
        meta.put("page", dataUser.getNumber());
        meta.put("size", dataUser.getSize());
        meta.put("totalData", dataUser.getTotalElements());
        meta.put("totalDataOnPage", dataUser.getNumberOfElements());

        Map<String, Object> newData = new HashMap<>();
        newData.put("data", listOfUserDTO);
        newData.put("meta", meta);

        generalResponse.success("200", "List Of User", newData );
        return generalResponse;
    }


    public GeneralResponse addNewUser (User user) {
        GeneralResponse generalResponse = new GeneralResponse();
        String hashedPassword = Hashing.sha256()
                .hashString(user.getPassword(), StandardCharsets.UTF_8)
                .toString();
        user.setPassword(hashedPassword);
        userRepository.save(user);
        generalResponse.success("201", "Success created User !", user);

        return generalResponse;
    }

    public GeneralResponse getOneUser (long userId){
        GeneralResponse generalResponse = new GeneralResponse();

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            generalResponse.success("200", "Detail of user !", userOptional);
        }else  {
            generalResponse.fail("404", "user not found !");
        }
        return generalResponse;
    }

    public GeneralResponse deleteUser (long userId){
        GeneralResponse generalResponse = new GeneralResponse();
        Optional<User> userOptional = userRepository.findByIdAndStatus(userId, UserStatus.ACTIVE);
        Date date = new Date();
        User deletedUser;
        if (userOptional.isPresent()){
            deletedUser = userOptional.get();
            deletedUser.setModifiedDate(date);
            deletedUser.setStatus(UserStatus.DELETED);
            userRepository.save(deletedUser);
            generalResponse.success("200", "user status successfully deleted !", deletedUser);
        }else {
            generalResponse.fail("404", "user not found !");
        }
        return generalResponse;
    }

    public GeneralResponse updateUser (User user){
        Date date = new Date();
        Optional<User> roleOptional = userRepository.findById(user.getId());

        GeneralResponse generalResponse = new GeneralResponse();

        User newUser;
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
            generalResponse.success("200","user successfully updated!", newUser);
        }else {
            generalResponse.fail("404", "user not found !");
        }
        return generalResponse;
    }

    public GeneralResponse activationUser (User user){
        Date date = new Date();
        Optional<User> roleOptional = userRepository.findById(user.getId());

        GeneralResponse generalResponse = new GeneralResponse();

        User newUser;
        if (roleOptional.isPresent()){
            newUser = roleOptional.get();
            newUser.setModifiedDate(date);
            if (newUser.getStatus() != null){
                newUser.setStatus(user.getStatus());
            }
            userRepository.save(newUser);
            generalResponse.success("200","user status successfully updated !", newUser);
        }else {
            generalResponse.fail("404", "user not found !");
        }
        return generalResponse;
    }

    public GeneralResponse loginUser (User user) {
        GeneralResponse generalResponse = new GeneralResponse();
        Optional<User> userOptional = userRepository.findByUserName(user.getUserName());
        if(userOptional.isPresent()){
            String passwordData = userOptional.get().getPassword();
            String passwordPayload = user.getPassword();
            if (!passwordData.equals(passwordPayload)){
                return generalResponse.fail("403", "Incorrect username or password!");
            }
            String idUser = String.valueOf(userOptional.get().getId());
            String tokenMerchant = jwtTokenUtil.generateToken(userOptional.get(), idUser);

            CustomeResponseLoginUser returnLoginResponse = new CustomeResponseLoginUser();
            returnLoginResponse.setToken(tokenMerchant);
            returnLoginResponse.setUser(userOptional.get());

            generalResponse.success("200", "Success Login merchant !", returnLoginResponse);
        } else {
            generalResponse.fail("404", "Merchant not found !");
        }
        return generalResponse;
    }
}
