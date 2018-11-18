package com.daohuyen.dell.storeadmin.model;

import java.util.Date;

public class Customer {

    private String id;
    private String fullName;
    private String phone;
    private Gender genderID;
    private String address;
    private String identityCard;
    private String description;
    private String avatarUrl;
    private Date birthday;
    private String email;
    private User user;

    public String getEmail() {
        return email;
    }

//    public void update(ProfileBody body) {
//        this.fullName = body.getFullName();
//        this.phone = body.getPhone();
//        this.address = body.getAddress();
//        this.identityCard = body.getIdentityCard();
//        this.avatarUrl = body.getAvatarUrl();
//        this.gender = body.getGender();
//        if (body.getBirthday() == -1) {
//            birthday = new Date();
//        } else {
//            birthday = new Date(body.getBirthday());
//        }
//        this.email = body.getEmail();
//    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGenderID() {
        return genderID;
    }

    public void setGenderID(Gender genderID) {
        this.genderID = genderID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
