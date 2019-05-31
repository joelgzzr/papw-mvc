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
import models.Category;
import models.Database;

public class CategoryController {
    Database db = new Database();
    String sql = "";
    Connection con;
    CallableStatement cst;
    ResultSet rs;

    public CategoryController() {
    }
    
    public List<Category> getAllCategories(){
        List<Category> allCategories = new ArrayList<>();
        Category currentCategory = null;
        try {
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            sql = "CALL getAllCategories()";
            cst = con.prepareCall(sql);
            rs = cst.executeQuery();
            
            while(rs.next()){
                currentCategory = new Category(rs.getInt("id"), rs.getString("name"));
                allCategories.add(currentCategory);
            }
            
            cst.close();
            con.close();
            rs.close();
            return allCategories;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}
