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
        InputStream avatar = null;
        InputStream cover = null;
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
            
            avatar = new FileInputStream(registerUser.getAvatar());
            cover = new FileInputStream(registerUser.getCover());
            cst.setBinaryStream(7, avatar);
            cst.setBinaryStream(8, cover);
            
            cst.execute();
            
            cst.close();
            con.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
    
    
}
