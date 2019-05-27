/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

public class Database {
    private String url;
    private String user;
    private String password;
    private String driver;

    public Database() {
        this.url = "jdbc:mysql://localhost:3306/papw_shop";
        this.user = "root";
        this.password = "";
        this.driver = "com.mysql.jdbc.Driver";
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }
    
}
