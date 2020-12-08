package com.cashlez.demo.model;

import com.cashlez.demo.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class User extends BaseEntity {
    private String userName;

    private String phoneNumber;

    private String status = "ACTIVE";

    @JsonIgnore
    private String passHash;

    private String email;

    @OneToOne
    private Role Role;

    @OneToOne
    private Merchant Merchant;

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

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return Role;
    }

    public void setRole(Role role) {
        this.Role = role;
    }

    public com.cashlez.demo.model.Merchant getMerchant() {
        return Merchant;
    }

    public void setMerchant(com.cashlez.demo.model.Merchant merchant) {
        Merchant = merchant;
    }
}
