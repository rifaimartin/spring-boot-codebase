package com.cashlez.demo.dto;

public enum PermissionType {
    ADD("add"),
    EDIT("edit"),
    DELETE("delete"),
    READ("read");

    private String type;

    PermissionType(String typeValue) {
        this.type = typeValue;
    }

    public String getUrl() {
        return type;
    }
}
