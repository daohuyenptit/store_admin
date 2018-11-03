package com.daohuyen.dell.store_admin.model.body;

import java.io.Serializable;

public class CustomerBody1 implements Serializable {
    private String fullName;
    private String phone;
    private int gender;
    private String address;
    private String identityCard;
    private String description;
    private String avatarUrl;
    private long birthday;
    private String email;

    public CustomerBody1() {
    }

    public CustomerBody1(String fullName, String phone, int gender, String address, String identityCard, String description, String avatarUrl, long birthday, String email) {
        this.fullName = fullName;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.identityCard = identityCard;
        this.description = description;
        this.avatarUrl = avatarUrl;
        this.birthday = birthday;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
