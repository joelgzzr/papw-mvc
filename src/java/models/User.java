/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.File;

public class User {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String phone;
    private String address;
    private File avatar;
    private File cover;

    public User(String username, String fullname, String email, String password, String phone, String address, File avatar, File cover) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
        this.cover = cover;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public File getAvatar() {
        return avatar;
    }

    public File getCover() {
        return cover;
    }
    
    
}
