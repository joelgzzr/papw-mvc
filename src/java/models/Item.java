/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.InputStream;

public class Item {
    private int id;
    private int user;
    private String name;
    private String description;
    private int category;
    private String price;
    private String discountPrice;
    private String stock;
    private InputStream video;
    private int views;
    private int sold;
    private String date;
    private int draft;
    private int discount;
    private InputStream img1;
    private InputStream img2;
    private InputStream img3;
    private String videoString;
    private String imgString1;
    private String imgString2;
    private String imgString3;

    public Item(int user, String name, String description, int category, String price, String stock, int draft) {
        this.id = 0;
        this.user = user;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.discountPrice = "";
        this.stock = stock;
        this.video = null;
        this.views = 0;
        this.sold = 0;
        this.date = "";
        this.draft = draft;
        this.discount = 0;
        this.img1 = null;
        this.img2 = null;
        this.img3 = null;
        this.imgString1 = "";
        this.imgString2 = "";
        this.imgString3 = "";
    }

    public int getId() {
        return id;
    }

    public int getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public String getStock() {
        return stock;
    }

    public InputStream getVideo() {
        return video;
    }

    public int getViews() {
        return views;
    }

    public int getSold() {
        return sold;
    }

    public String getDate() {
        return date;
    }

    public int getDraft() {
        return draft;
    }

    public int getDiscount() {
        return discount;
    }

    public InputStream getImg1() {
        return img1;
    }

    public InputStream getImg2() {
        return img2;
    }

    public InputStream getImg3() {
        return img3;
    }

    public String getVideoString() {
        return videoString;
    }

    public String getImgString1() {
        return imgString1;
    }

    public String getImgString2() {
        return imgString2;
    }

    public String getImgString3() {
        return imgString3;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public void setVideo(InputStream video) {
        this.video = video;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDraft(int draft) {
        this.draft = draft;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setImg1(InputStream img1) {
        this.img1 = img1;
    }

    public void setImg2(InputStream img2) {
        this.img2 = img2;
    }

    public void setImg3(InputStream img3) {
        this.img3 = img3;
    }

    public void setVideoString(String videoString) {
        this.videoString = videoString;
    }

    public void setImgString1(String imgString1) {
        this.imgString1 = imgString1;
    }

    public void setImgString2(String imgString2) {
        this.imgString2 = imgString2;
    }

    public void setImgString3(String imgString3) {
        this.imgString3 = imgString3;
    }
    
    
    
    
          
}
