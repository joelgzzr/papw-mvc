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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class Auth {
    Database db = new Database();
    String sql = "";
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public Auth() {
        
    }
    
    public boolean validate(String email, String password){
        String dbPassword = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL selectAllFromUser(?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            
            while(rs.next()){
                dbPassword = rs.getString("password");
            }
            
            con.close();
            rs.close();
            
            return password.equals(dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    
    public boolean register(User registerUser){
        FileInputStream avatar = null;
        FileInputStream cover = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL insertIntoUser(?, ?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, registerUser.getUsername());
            pst.setString(2, registerUser.getFullname());
            pst.setString(3, registerUser.getEmail());
            pst.setString(4, registerUser.getPassword());
            pst.setString(5, registerUser.getPhone());
            pst.setString(6, registerUser.getAddress());
            
            avatar = new FileInputStream(registerUser.getAvatar());
            cover = new FileInputStream(registerUser.getCover());
            pst.setBinaryStream(7, avatar);
            pst.setBinaryStream(8, cover);
            
            rs = pst.executeQuery();
            
            con.close();
            rs.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
    
    
}
