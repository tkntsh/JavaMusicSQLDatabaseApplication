/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entities.DB;
import Entities.Playlist;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cha09nts001.1
 */
public class PlaylistManager 
{
    //creating playlist array to store different songs
    private Playlist[] playLArr = new Playlist[100];
    //size variable to calculate how many playlist objects we have
    int sizePl = 0;
    //creatig dbManager to be used as transporter to retrieve data from access
    private DB dbMan = new DB();
    
    public PlaylistManager()
    {
        try
        {
            //sql query to select all the data in the listerners table 
            String sql = "SELECT* FROM tblPlaylists";
            //connection between access and java
            ResultSet rs = dbMan.queryDatabase(sql);
            
            //while statement checking if there's anymore objects that need to be collected
            while(rs.next())
            {
                //coping the table names in access to extract the data and store it in java
                Playlist playL = new Playlist(rs.getString("listernerID"), rs.getInt("songID"));
                
                //using created playlist array to store current extracted listerner in record from access
                playLArr[sizePl] = playL;
                //keeping track of how many playlists are in the object
                sizePl++;
            }
        }
        //sql catch statement to display error message if we have a problem populating
        catch(SQLException s)
        {
            System.out.println("Error: ListenerManager populating array error");
        }
    }
    
    //method to arrange/sort the playlists in the database
    public void sort()
    {
        for(int i=0; i<sizePl-1; i++)
        {
            for(int x=i+1; x<sizePl; x++)
            {
                //if statement comparing the listerner ID's to see which one comes first
                if(playLArr[i].getListenerID().compareToIgnoreCase(playLArr[x].getListenerID())>0)
                {
                    //rearranging the playlists
                    Playlist temp = playLArr[x];
                    playLArr[x] = playLArr[i];
                    playLArr[i] = temp;
                }
            }
        }
    }
    
    //method used to add playlists for the user
    public String addPlaylist(Playlist p)
    {
        //using sql code INSERT INTO to give the user power to enter a playlist they would like to enter
        String sql = "INSERT INTO Song (listernerID, songID "
                + " VALUES('" + p.getListenerID() + ", " + p.getSongID() + "');";
        
        //try statement for dangerous code
        try
        {
            //using DB to update the rows in table so the user can insert whatever data they want to insert
            dbMan.updateQuery(sql);
            playLArr[sizePl] = p;
            //adding a new listernID to array
            sql = "SELECT listernerID FROM Listerners WHERE songID = '" + p.getSongID() + "';";
            //used to ask for the information to be brought over into java
            ResultSet rs = dbMan.queryDatabase(sql);
            rs.next();
            sizePl++;
            return "ListernID successfully added";
        }
        //catch statement if adding a variable was unsuccessful
        catch(SQLException f)
        {
            System.out.println("Error: Could not add playlist to array");
        }
        return "Playlist couldn't successfully be added";
    }
    
    public String deletePlaylist(String lId)
    {
        //hard coding delete query 
        String sql = "DELETE* FROM Listerners WHERE listernID = '" + lId + "';";
        
        try
        {
            //updating the database again
            dbMan.updateQuery(sql);
            
            //loop made to delete the record
            for(int i=0; i<sizePl; i++)
            {
                if(playLArr[i].getListenerID().equalsIgnoreCase(lId))
                {
                    //this for loop using minus will delete the record
                    for(int f=0; f<sizePl-1; f++)
                    {
                        playLArr[f] = playLArr[f+1];
                    }
                    System.out.println("Deletion successful");
                }
                //making array size smaller because one song has been deleted
                sizePl--;
            }
        }
        catch(SQLException q)
        {
            System.out.println("Error: couldn't delete row selected");
        }
        //returning message that the deletion wasn't successful
        return "deletion unsuccessful";
    }
    
    //method to search for a playlist
    public int searchPlaylist(String listenerID)
    {
        int i = 0;
        int pos = -1;
        boolean found = false;
        
        while(found == false && i<sizePl)
        {
            //checking if listenerID user entered is equal to the listenerID it's supposed to be equal to
            if(listenerID.equalsIgnoreCase(playLArr[i].getListenerID()))
            {
                pos = i;
            }
            //keeping track of this position
            i++;
        }
        //returning the position so that we know where to find the name
        return pos;
    }
    
    public void updatePlaylist(String a, int sI, String aI)
    {
        //running sql code to update the playlist table when needed
        String sql = "UPDATE tblPlaylists SET songID = '" + a + "', aongID = '" + sI + 
                "' WHERE listenerID = '" + aI + "';";
        
        //try and catch to catch dangerous code running from java to sql updating table
        try
        {
            //running sql to update the table in database
            dbMan.updateQuery(sql);
            int position = searchPlaylist(a);
            playLArr[position] = new Playlist(a, sI);
        }
        catch(SQLException e)
        {
            //displaying error message to tell user table update couldn't be executed
            System.out.println("Error: couldn't update playlist table");
        }
    }
    
    //accessor methods so user can call this method to view playlist and size
    public Playlist[] getPlaylistArr()
    {
        return playLArr;
    }
    
    public int getSizePl()
    {
        return sizePl;
    }
    
    //toString method used to display all the updated values
    public String toString()
    {
        String temp = "";
        //for loop runs everytime there is a new playlist object that needs to be added into the array
        for(int i=0; i<sizePl; i++)
        {
            temp = temp + playLArr[i] + "\n";
        }
        //returning to temp with all the updated values
        return temp;
    }
}
