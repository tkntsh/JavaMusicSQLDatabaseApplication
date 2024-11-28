/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Bapi
 */
public class Users 
{
    //creating values to so user is able to login
    private String username = "";
    private String password = "";
    
    public Users(String uN, String pW)
    {
        username = uN;
        password = pW;
    }

    public Users() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //creating constructors so we are able to get the username and the password
    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }
    
    //creating setter methods so that we are able to change the username and password
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }
    
    //creating a method that allows us to display the user logged in the system
    @Override
    public String toString() 
    {
        return "Users{" + "username=" + username + ", password=" + password + '}';
    }
}
