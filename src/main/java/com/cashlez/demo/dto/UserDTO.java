package com.cashlez.demo.dto;

import com.cashlez.demo.model.Role;

public class UserDTO {
    private long id;
    private String userName;

    private String phoneNumber;

    private String status;

    private String email;

    public com.cashlez.demo.model.Role getRole() {
        return Role;
    }

    public void setRole(com.cashlez.demo.model.Role role) {
        Role = role;
    }

    private Role Role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
