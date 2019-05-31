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
}
