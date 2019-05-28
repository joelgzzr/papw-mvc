/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import models.Database;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class Auth {
    Database db = new Database();
    String sql = "";
    Connection con;
    PreparedStatement pst;
    CallableStatement cst;
    ResultSet rs;

    public Auth() {
        
    }
    
    public boolean validate(String email, String password){
        String dbPassword = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL selectAllFromUser(?)";
            cst = con.prepareCall(sql);
            cst.setString(1, email);
            rs = cst.executeQuery();
            
            while(rs.next()){
                dbPassword = rs.getString("password");
            }
            
            cst.close();
            con.close();
            rs.close();
            
            return password.equals(dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    
    public User getUserFromDatabase(String email){
        User retrievedUser = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL selectAllFromUser(?)";
            cst = con.prepareCall(sql);
            cst.setString(1, email);
            
            rs = cst.executeQuery();
            
            while(rs.next()){
                retrievedUser = new User(
                        rs.getString("username"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getBinaryStream("profilepic"),
                        rs.getBinaryStream("cover")
                );
            }
            
            cst.close();
            con.close();
            rs.close();
            
            return retrievedUser;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public boolean register(User registerUser){
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL insertIntoUser(?, ?, ?, ?, ?, ?, ?, ?)";
            cst = con.prepareCall(sql);
            cst.setString(1, registerUser.getUsername());
            cst.setString(2, registerUser.getFullname());
            cst.setString(3, registerUser.getEmail());
            cst.setString(4, registerUser.getPassword());
            cst.setString(5, registerUser.getPhone());
            cst.setString(6, registerUser.getAddress());
            cst.setBinaryStream(7, registerUser.getAvatar());
            cst.setBinaryStream(8, registerUser.getCover());
            
            cst.execute();
            
            cst.close();
            con.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
}
