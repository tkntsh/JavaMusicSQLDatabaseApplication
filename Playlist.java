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
public class Playlist 
{
    //creating private vaules to mirror data in sql database
    private String listernerID;
    private int songID;
    
    //creating default listener
    public Playlist(String listernerID, int songID) 
    {
        this.listernerID = listernerID;
        this.songID = songID;
    }
    
    //creating get methods to throw the values needed
    public String getListenerID() 
    {
        return listernerID;
    }

    public int getSongID() 
    {
        return songID;
    }
    
    //creating set methods to give them a default values
    public void setListernerID(String listernerID) 
    {
        this.listernerID = listernerID;
    }

    public void setSongID(int songID) 
    {
        this.songID = songID;
    }
    
    //creating toString method to display what's currently in the object
    @Override
    public String toString() 
    {
        return "Playlist{" + "listernerID=" + listernerID + ", songID=" + songID + '}';
    }
}
