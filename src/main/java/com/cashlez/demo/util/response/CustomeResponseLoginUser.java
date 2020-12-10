package com.cashlez.demo.util.response;

import com.cashlez.demo.model.Merchant;
import com.cashlez.demo.model.User;

public class CustomeResponseLoginUser {
    String token;
    User user;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
