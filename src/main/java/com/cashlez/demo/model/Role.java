package com.cashlez.demo.model;

import com.cashlez.demo.base.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Role extends BaseEntity {
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Permissions> permissions;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }
}
