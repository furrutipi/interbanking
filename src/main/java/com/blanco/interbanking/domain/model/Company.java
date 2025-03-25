package com.blanco.interbanking.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Company {
    private String id;
    private String cuit;
    private String companyName;
    private LocalDateTime adhesionDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isDeleted;
    private LocalDateTime deletedAt;

    public Company() {
    }

    public Company(String id, String cuit, String companyName, LocalDateTime adhesionTime, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean isDeleted, LocalDateTime deletedAt) {

        this.id = id;
        this.cuit = cuit;
        this.companyName = companyName;
        this.adhesionDate = adhesionTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }

    public Company(String cuit, String companyName, LocalDateTime adhesionTime) {
        this.id = UUID.randomUUID().toString();;
        this.cuit = cuit;
        this.companyName = companyName;
        this.adhesionDate = adhesionTime;
        this.isDeleted = false;
    }
    public Company(String cuit, String companyName, LocalDateTime adhesionTime,String id) {
        this.id = id;
        this.cuit = cuit;
        this.companyName = companyName;
        this.adhesionDate = adhesionTime;
        this.isDeleted = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String name) {
        this.companyName = name;
    }

    public LocalDateTime getAdhesionDate() {
        return adhesionDate;
    }

    public void setAdhesionDate(LocalDateTime adhesionTime) {
        this.adhesionDate = adhesionTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
