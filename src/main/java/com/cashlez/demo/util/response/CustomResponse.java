package com.cashlez.demo.util.response;

import com.cashlez.demo.model.Merchant;

public class CustomResponse {
    String token;
    Merchant merchant;

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
