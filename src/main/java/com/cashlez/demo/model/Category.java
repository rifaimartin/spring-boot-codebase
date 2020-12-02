package com.cashlez.demo.model;

import com.cashlez.demo.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Category extends BaseEntity {
    private String name;

    @Column(length = 510)
    private String description;

    private String status = "ACTIVE";

    private String categoryImage;

    @OneToOne
    private Merchant Merchant;

    private Long vapnCategoryId;

    private String createdBy;

    private String modifiedBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public com.cashlez.demo.model.Merchant getMerchant() {
        return Merchant;
    }

    public void setMerchant(com.cashlez.demo.model.Merchant merchant) {
        Merchant = merchant;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public Long getVapnCategoryId() {
        return vapnCategoryId;
    }

    public void setVapnCategoryId(Long vapnCategoryId) {
        this.vapnCategoryId = vapnCategoryId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", categoryImage='" + categoryImage + '\'' +
                ", Merchant=" + Merchant +
                ", vapnCategoryId=" + vapnCategoryId +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }
}