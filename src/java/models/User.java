/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class User {
    private int id;
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String phone;
    private String address;
    private InputStream avatar;
    private InputStream cover;
    private String avatarString;
    private String coverString;

    public User(String username, String fullname, String email, String password, String phone, String address) {
        this.id = 0;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.avatar = null;
        this.cover = null;
        this.avatarString = "";
        this.coverString = "";
    }

    public int getId(){
        return id;
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

    public InputStream getAvatar() {
        return avatar;
    }

    public InputStream getCover() {
        return cover;
    }
    
    public String getAvatarString() {
        return avatarString;
    }

    public String getCoverString() {
        return coverString;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvatar(InputStream avatar) {
        this.avatar = avatar;
    }

    public void setCover(InputStream cover) {
        this.cover = cover;
    }

    public void setAvatarString(String avatarString) {
        this.avatarString = avatarString;
    }

    public void setCoverString(String coverString) {
        this.coverString = coverString;
    }
}
