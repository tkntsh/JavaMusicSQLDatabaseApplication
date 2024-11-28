/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entities.DB;
import Entities.Song;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author cha09nts001.1
 */
public class SongManager 
{
    //creating song array to store different songs
    private Song[] songArr = new Song[100];
    //size variable to calculate how many song objects we have
    int sizeSong = 0;
    //creatig dbManager to be used as transporter to retrieve data from access
    private DB dbMan = new DB();
    
    public SongManager()
    {
        try
        {
            //sql query to select all the data in the songs table 
            String sql = "SELECT* FROM tblSongs";
            //connection between access and java
            ResultSet rs = dbMan.queryDatabase(sql);
            
            //while statement checking if there's anymore objects that need to be collected
            while(rs.next())
            {
                //coping the table names in access to extract the data and store it in java
                Song song = new Song(rs.getInt("artistID"), 
                        rs.getString("songName"), 
                        rs.getDate("releaseDate"), 
                        rs.getString("genre"), 
                        rs.getString("duration"), 
                        rs.getString("album"));
                //using created song array to store current extracted song in record from access
                songArr[sizeSong] = song;
                //keeping track of how many songs are in the object
                sizeSong++;
            }
        }
        //sql catch statement to display error message if we have a problem populating
        catch(SQLException s)
        {
            System.out.println("Error: SongManager populating array error");
        }
    }
    
    //method to arrange/sort the songs in the database
    public void sort()
    {
        for(int i=0; i<sizeSong-1; i++)
        {
            for(int x=i+1; x<sizeSong; x++)
            {
                //if statement comparing the song names to see which one comes first
                if(songArr[i].getSongName().compareToIgnoreCase(songArr[x].getSongName())>0)
                {
                    //rearranging the songs
                    Song temp = songArr[x];
                    songArr[x] = songArr[i];
                    songArr[i] = temp;
                }
            }
        }
    }
    
    //method used to add songs for the user
    public String addSongs(Song s)
    {
        //using sql code INSERT INTO to give the user power to enter a song they would like to enter
        String sql = "INSERT INTO Song (songName, releaseDate, genre, duration, album "
                + " VALUES('" + s.getSongName() + "', '" + s.getReleaseDate() + "', '" + s.getGenre() + "', '" + s.getDuration() + "', '" + s.getAlbum() + "');";
        
        //try statement for dangerous code
        try
        {
            //using DB to update the rows in table so the user can insert whatever data they want to insert
            dbMan.updateQuery(sql);
            songArr[sizeSong] = s;
            //adding a new song to array
            sql = "SELECT artistID FROM Song WHERE songName = '" + s.getSongName() + "';";
            //used to ask for the information to be brought over into java
            ResultSet rs = dbMan.queryDatabase(sql);
            rs.next();
            sizeSong++;
            return "Song successfully added";
        }
        //catch statement if adding a variable was unsuccessful
        catch(SQLException f)
        {
            System.out.println("Error: Could not add song to array");
        }
        return "Song couldn't successfully be added";
    }
    
    public String deleteSong(int aId)
    {
        //hard coding delete query 
        String sql = "DELETE* FROM Song WHERE artistID = '" + aId + "';";
        
        try
        {
            //updating the database again
            dbMan.updateQuery(sql);
            
            //loop made to delete the record
            for(int i=0; i<sizeSong; i++)
            {
                if(songArr[i].getArtistID()==aId)
                {
                    //this for loop using minus will delete the record
                    for(int f=0; f<sizeSong-1; f++)
                    {
                        songArr[f] = songArr[f+1];
                    }
                    System.out.println("Deletion successful");
                }
                //making array size smaller because one song has been deleted
                sizeSong--;
            }
        }
        catch(SQLException q)
        {
            System.out.println("Error: couldn't delete row selected");
        }
        return "deletion unsuccessful";
    }
    
    //method to search for a song
    public int searchSong(int artistID)
    {
        int i = 0;
        int pos = -1;
        boolean found = false;
        
        while(found == false && i<sizeSong)
        {
            //checking if artisID the user entered is equal to the artistID it's supposed to be equal to
            if(artistID==songArr[i].getArtistID())
            {
                pos = i;
            }
            //keeping track of this position
            i++;
        }
        //returning the position so that we know where to find the name
        return pos;
    }
    
    public void updateSong(int aI, String sN, Date rD, String g, String d, String a, int aID)
    {
        //running sql code to update the song table when needed
        String sql = "UPDATE tblSongs SET artistID = '" + aI + "', songName = '" + sN + "', releaseDate = '" + rD +
                "', genre = '" + g + "', duration = '" + d + "', album = '" + a +
                "' WHERE artistID = '" + aID + "';";
        
        //try and catch to catch dangerous code running from java to sql updating table
        try
        {
            //running sql to update the table in database
            dbMan.updateQuery(sql);
            int position = searchSong(aID);
            songArr[position] = new Song(aI, sN, rD, g, d, a);
        }
        catch(SQLException e)
        {
            //displaying error message to tell user table update couldn't be executed
            System.out.println("Error: couldn't update song table");
        }
    }
    
    public Song[] getSongArr()
    {
        return songArr;
    }
    
    public int getSizeS()
    {
        return sizeSong;
    }
    
    //toString method used to display all the updated values
    public String toString()
    {
        String temp = "";
        //for loop runs everytime there is a new song object that needs to be added into the array
        for(int i=0; i<sizeSong; i++)
        {
            temp = temp + songArr[i] + "\n";
        }
        //returning to temp with all the updated values
        return temp;
    }
}
