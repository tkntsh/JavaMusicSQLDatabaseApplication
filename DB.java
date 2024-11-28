/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cha09nts001.1
 */
public class DB 
{
    //creating a connection from sql to java netbeans
    private Connection conn = null;
    private String conString = "jdbc:ucanaccess://ArtistDB1.accdb";
    
    public DB()
    {
        //try and catch for dangerous code (connection between sql and java)
        try
        {
            conn = DriverManager.getConnection(conString);
            //message to display that connection was successful
            System.out.println("Connection successful");
        }
        catch(SQLException e)
        {
            //error message to tell the user the connection was unsuccessful
            System.out.println("Connection was unsuccessful " + e.getStackTrace());
        }
    }
    
    //vehicle that brings data from sql into java
    public ResultSet queryDatabase(String sql) throws SQLException
    {
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet set = stat.executeQuery();
        return set;
    }
    
    //this method runs everytime we need to update the database
    public void updateQuery(String sql) throws SQLException
    {
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.execute();
    }
}
