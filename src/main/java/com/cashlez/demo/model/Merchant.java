package com.cashlez.demo.model;

import com.cashlez.demo.base.BaseEntity;
import com.cashlez.demo.dto.MerchantStatus;

import javax.persistence.Entity;

@Entity
public class Merchant extends BaseEntity {

    private String merchantName;

    private String address;

    private MerchantStatus status = MerchantStatus.ACTIVE;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MerchantStatus getStatus() {
        return status;
    }

    public void setStatus(MerchantStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantName='" + merchantName + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}
