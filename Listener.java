/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author cha09nts001.1
 */
public class Listener 
{
    //mirror sql database on the listener object
    private int listenerID;
    private String listener; 
    private String name;
    private String surname;
    private String preferredGenre;
    private String username;
    
    //creating default listener value
    public Listener(int listenerID, String listener, String name, String surname, String preferredGenre, String username) 
    {
        this.listenerID = listenerID;
        this.listener = listener;
        this.name = name;
        this.surname = surname;
        this.preferredGenre = preferredGenre;
        this.username = username;
    }
    
    //creating get methods to get the values needed wherever they are needed
    public int getListenerID()
    {
        return listenerID;
    }
    
    public String getListener() 
    {
        return listener;
    }

    public String getName() 
    {
        return name;
    }

    public String getSurname() 
    {
        return surname;
    }

    public String getPrefferedGenre() 
    {
        return preferredGenre;
    }

    public String getUsername() 
    {
        return username;
    }
    
    //creating set values to give the entities of the objects default values
    public void setListener(String listener) 
    {
        this.listener = listener;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setSurname(String surname) 
    {
        this.surname = surname;
    }

    public void setPrefferedGenre(String prefferedGenre) 
    {
        this.preferredGenre = preferredGenre;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }
    
    //creating toString method to display what's currently in the object
    @Override
    public String toString() 
    {
        return "Listener{" + "listener=" + listener + ", name=" + name + ", surname=" + surname + ", preferredGenre=" + preferredGenre + ", username=" + username + '}';
    }
}
