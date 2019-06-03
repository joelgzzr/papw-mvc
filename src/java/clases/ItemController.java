/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Database;
import models.Item;

public class ItemController {
    Database db = new Database();
    String sql = "";
    Connection con;
    PreparedStatement pst;
    CallableStatement cst;
    ResultSet rs;

    public ItemController() {
    }
    
    public boolean addItemToDatabase(Item item){
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL insertIntoItem(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, item.getUser());
            cst.setString(2, item.getName());
            cst.setString(3, item.getDescription());
            cst.setInt(4, item.getCategory());
            cst.setString(5, item.getPrice());
            cst.setString(6, item.getStock());
            cst.setBinaryStream(7, item.getVideo());
            cst.setBinaryStream(8, item.getImg1());
            cst.setBinaryStream(9, item.getImg2());
            cst.setBinaryStream(10, item.getImg3());
            cst.setInt(11, item.getDraft());
            
            cst.execute();
            
            cst.close();
            con.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    
    public List<Item> getAllItems(int user){
        List<Item> allItems = new ArrayList<>();
        Item currentItem = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getItemsFromUser(?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, user);
            rs = cst.executeQuery();
            
            while(rs.next()){
                currentItem = new Item(user, rs.getString("name"), rs.getString("description"), rs.getInt("category"), rs.getString("price"), rs.getString("stock"), rs.getInt("draft"));
                currentItem.setId(rs.getInt("id"));
                allItems.add(currentItem);
            }
            
            cst.close();
            con.close();
            rs.close();
            return allItems;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public List<Item> getAllDraftItems(int user){
        List<Item> allItems = new ArrayList<>();
        Item currentItem = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getItemsFromUser(?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, user);
            rs = cst.executeQuery();
            
            while(rs.next()){
                if(rs.getInt("draft") == 1){
                    currentItem = new Item(user, rs.getString("name"), rs.getString("description"), rs.getInt("category"), rs.getString("price"), rs.getString("stock"), rs.getInt("draft"));
                    currentItem.setId(rs.getInt("id"));
                    allItems.add(currentItem);
                }
            }
            
            cst.close();
            con.close();
            rs.close();
            return allItems;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public boolean deleteItem(int item){
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL deleteItem(?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, item);
            
            cst.execute();
            
            cst.close();
            con.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    
    public boolean publishItem(int item){
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL updateDraftItem(?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, item);
            
            cst.execute();
            
            cst.close();
            con.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    
    public Item getItemData(int id){
        Item retrievedItem = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getItemDataForm(?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            rs = cst.executeQuery();
            
            while(rs.next()){
                retrievedItem = new Item(rs.getInt("user"), rs.getString("name"), rs.getString("description"), rs.getInt("category"), rs.getString("price"), rs.getString("stock"), rs.getInt("draft"));
                retrievedItem.setId(rs.getInt("id"));
            }
            
            cst.close();
            con.close();
            rs.close();
            return retrievedItem;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public Item getItemDataFull(int id){
        Item retrievedItem = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getItemData(?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            rs = cst.executeQuery();
            
            while(rs.next()){
                retrievedItem = new Item(rs.getInt("user"), rs.getString("name"), rs.getString("description"), rs.getInt("category"), rs.getString("price"), rs.getString("stock"), rs.getInt("draft"));
                retrievedItem.setId(rs.getInt("id"));
                retrievedItem.setVideoString(rs.getString("video"));
            }
            
            cst.close();
            con.close();
            rs.close();
            return retrievedItem;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public boolean modifyItem(Item item){
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL updateItem(?, ?, ?, ?, ?, ?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, item.getId());
            cst.setString(2, item.getName());
            cst.setString(3, item.getDescription());
            cst.setInt(4, item.getCategory());
            cst.setString(5, item.getPrice());
            cst.setString(6, item.getStock());
            
            cst.execute();
            
            cst.close();
            con.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    
    public List<Item> getMostSoldItems(){
        List<Item> allItems = new ArrayList<>();
        Item currentItem = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getItemDataBySold()";
            cst = con.prepareCall(sql);
            rs = cst.executeQuery();
            
            while(rs.next()){
                currentItem = new Item(rs.getInt("user"), rs.getString("name"), rs.getString("description"), rs.getInt("category"), rs.getString("price"), rs.getString("stock"), rs.getInt("draft"));
                currentItem.setId(rs.getInt("id"));
                currentItem.setImgString1(rs.getString("image"));
                allItems.add(currentItem);
            }
            
            cst.close();
            con.close();
            rs.close();
            return allItems;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public List<Item> getMostViewedItems(){
        List<Item> allItems = new ArrayList<>();
        Item currentItem = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getItemsByViews()";
            cst = con.prepareCall(sql);
            rs = cst.executeQuery();
            
            while(rs.next()){
                currentItem = new Item(rs.getInt("user"), rs.getString("name"), rs.getString("description"), rs.getInt("category"), rs.getString("price"), rs.getString("stock"), rs.getInt("draft"));
                currentItem.setId(rs.getInt("id"));
                currentItem.setImgString1(rs.getString("image"));
                allItems.add(currentItem);
            }
            
            cst.close();
            con.close();
            rs.close();
            return allItems;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public List<Item> getBestRatedItems(){
        List<Item> allItems = new ArrayList<>();
        Item currentItem = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getItemsByRating()";
            cst = con.prepareCall(sql);
            rs = cst.executeQuery();
            
            while(rs.next()){
                currentItem = new Item(rs.getInt("user"), rs.getString("name"), rs.getString("description"), rs.getInt("category"), rs.getString("price"), rs.getString("stock"), rs.getInt("draft"));
                currentItem.setId(rs.getInt("id"));
                currentItem.setImgString1(rs.getString("image"));
                allItems.add(currentItem);
            }
            
            cst.close();
            con.close();
            rs.close();
            return allItems;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public List<String> getItemImages(int id){
        List<String> allImages = new ArrayList<>();
        String currentImage = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getItemImages(?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            rs = cst.executeQuery();
            
            while(rs.next()){
                currentImage = rs.getString("image");
                allImages.add(currentImage);
            }
            
            cst.close();
            con.close();
            rs.close();
            return allImages;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public List<String[]> getItemComments(int id){
        List<String[]> allComments = new ArrayList<>();
        String[] currentComment;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL selectItemComment(?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            rs = cst.executeQuery();
            
            while(rs.next()){
                currentComment = new String[3];
                currentComment[0] = rs.getString("name");
                currentComment[1] = rs.getString("comment");
                currentComment[2] = rs.getString("profilepic");
                allComments.add(currentComment);
            }
            
            cst.close();
            con.close();
            rs.close();
            return allComments;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public String getItemRating(int id){
        String rating = "Aun sin";
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getItemRating(?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            rs = cst.executeQuery();
            
            while(rs.next()){
                rating = rs.getString("rating");
            }
            
            cst.close();
            con.close();
            rs.close();
            return rating;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public boolean getBoughtItems(int user, int id){
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getBoughtItems(?,?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, user);
            cst.setInt(2, id);
            rs = cst.executeQuery();
            
            while(rs.next()){
                return true;
            }
            
            cst.close();
            con.close();
            rs.close();
            return false;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    
    public boolean insertItemComment(int user, int id, String comment){
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL insertItemComment(?,?,?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, user);
            cst.setInt(2, id);
            cst.setString(3, comment);
            cst.execute();
            
            cst.close();
            con.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    
    public boolean addCart(int user, int id){
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL insertCart(?,?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, user);
            cst.setInt(2, id);
            cst.execute();
            
            cst.close();
            con.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    
    public boolean rateItem(int user, int id, String rating){
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL insertItemRating(?,?,?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            cst.setInt(2, user);
            cst.setString(3, rating);
            cst.execute();
            
            cst.close();
            con.close();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    
    public void addItemView(int id){
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL addItemView(?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            cst.execute();
            
            cst.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            
        }
    }
    
    public List<String[]> getUserCart(int user){
        List<String[]> allCart = new ArrayList<>();
        String[] currentCart;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getUserCart(?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, user);
            rs = cst.executeQuery();
            
            while(rs.next()){
                currentCart = new String[5];
                currentCart[0] = rs.getString("id");
                currentCart[1] = rs.getString("user");
                currentCart[2] = rs.getString("itemname");
                currentCart[3] = rs.getString("price");
                currentCart[4] = rs.getString("item");
                allCart.add(currentCart);
            }
            
            cst.close();
            con.close();
            rs.close();
            return allCart;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public List<String[]> getUserCheckouts(int user){
        List<String[]> allCart = new ArrayList<>();
        String[] currentCart;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getUserCheckouts(?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, user);
            rs = cst.executeQuery();
            
            while(rs.next()){
                currentCart = new String[3];
                currentCart[0] = rs.getString("name");
                currentCart[1] = rs.getString("price");
                currentCart[2] = rs.getString("date");
                allCart.add(currentCart);
            }
            
            cst.close();
            con.close();
            rs.close();
            return allCart;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public void insertCheckout(int user, int item, int cartId){
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL insertCheckout(?,?,?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, user);
            cst.setInt(2, item);
            cst.setInt(3, cartId);
            cst.execute();
            
            cst.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            
        }
    }
    
    public void deleteCart(int cartId){
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL deleteCart(?)";
            cst = con.prepareCall(sql);
            cst.setInt(1, cartId);
            cst.execute();
            
            cst.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            
        }
    }
    
    public List<Item> getItemsBySearch(String search){
        List<Item> allItems = new ArrayList<>();
        Item currentItem = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getItemsBySearch(?)";
            cst = con.prepareCall(sql);
            cst.setString(1, search);
            rs = cst.executeQuery();
            
            while(rs.next()){
                currentItem = new Item(rs.getInt("user"), rs.getString("name"), rs.getString("description"), rs.getInt("category"), rs.getString("price"), rs.getString("stock"), rs.getInt("draft"));
                currentItem.setId(rs.getInt("id"));
                currentItem.setImgString1(rs.getString("image"));
                allItems.add(currentItem);
            }
            
            cst.close();
            con.close();
            rs.close();
            return allItems;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}
