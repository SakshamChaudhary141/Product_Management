/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.products_manager;

import java.sql.Connection;


/**
 *
 * @author ASUS
 */
import com.mysql.cj.jdbc.MysqlDataSource;//**what is the purpose?
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Data_Base {
    public static String server_name="Local Host";
    public static String username="Root";
    public static String dbname="products_db";
    public static Integer port_number=3306;
    public static String password="";//no password
    
    static Connection con;
    
    //here I create a function to create and get a connection
    public static Connection getConnection()
    {
        
        /*MysqlDataSource datasource= new MysqlDataSource();
        datasource.setServerName(server_name);
        datasource.setUser(username);
        datasource.setDatabaseName(dbname);
        datasource.setPortNumber(port_number);
        datasource.setPassword(password);
        
        try {
            return datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Data_Base.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }*/
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/products_db","root","");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Data_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Data_Base.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;  
        
        
        
    }
    /*public static void main(String[] args) {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/products_db");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Data_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Data_Base.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
