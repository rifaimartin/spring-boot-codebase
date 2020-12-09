package com.cashlez.demo.model;

import com.cashlez.demo.base.BaseEntity;
import com.cashlez.demo.dto.PermissionType;

import javax.persistence.Entity;

@Entity
public class Permissions extends BaseEntity {
    private PermissionType name;


    public PermissionType getName() {
        return name;
    }

    public void setName(PermissionType name) {
        this.name = name;
    }
}
