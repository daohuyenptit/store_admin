package com.daohuyen.dell.store_admin.model;

import java.io.Serializable;


public class User implements Serializable {
    private String username;
    private String password;
    private int post;




    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, int post) {
        this.username = username;
        this.password = password;
        this.post = post;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
