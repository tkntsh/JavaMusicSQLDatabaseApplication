/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entities.Artist;
import Entities.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author cha09nts001.1
 */
public class ArtistManager 
{
    //creating artist array to store different listeners
    private Artist[] artistArr = new Artist[100];
    //size variable to calculate how many artist objects we have
    private int sizeArt = 0;
    //creatig dbManager to be used as transporter to retrieve data from access
    private DB dbMan = new DB();
    
    public ArtistManager()
    {
        try
        {
            //sql query to select all the data in the artist table 
            String sql = "SELECT* FROM tblArtists";
            //connection between access and java
            ResultSet rs = dbMan.queryDatabase(sql);
            
            //while statement checking if there's anymore objects that need to be collected
            while(rs.next())
            {
                //coping the table names in access to extract the data and store it in java
                Artist art = new Artist(rs.getInt("artistID"), 
                        rs.getString("artist"), 
                        rs.getBoolean("grammyNominated"), 
                        rs.getString("latestAlbum"), 
                        rs.getString("genre"), 
                        rs.getInt("albumSales"), 
                        rs.getString("upcomingTours"), 
                        rs.getInt("phoneNum"), 
                        rs.getString("website"), 
                        rs.getString("streamingPlatforms"), 
                        rs.getString("mostStreamedSong"), 
                        rs.getString("otherOccupation"));
                
                //using created artist array to store current extracted artist in record from access
                artistArr[sizeArt] = art;
                //keeping track of how many artists are in the object
                sizeArt++;
            }
        }
        //sql catch statement to display error message if we have a problem populating
        catch(SQLException a)
        {
            //telling user there was an error populating the artistmanager
            System.out.println("Error: ArtistManager populating array error");
        }
    }
    
    public String searchArtistGUI()
    {
        //button pressed by user will let them search for what they're looking for by the txf value they entered
        String temp = "";
        try
        {
            //creating connecting between java and sql to get data
            Connection con = DriverManager.getConnection("jdbc: ucanaccess");
            Statement stmt = con.createStatement();
            //code to run in sql to search for variable entered by user
            String sql = "SELECT* FROM tblArtists WHERE artist = '";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()==true)
            {
                //rs variable checks if anything corressponds with what the user has entered
                String artist = rs.getString("artist");
                boolean grammyNominated = rs.getBoolean("grammyNominated");
                String latestAlbum = rs.getString("latestAlbum");
                String genre = rs.getString("genre");
                int albumSales = rs.getInt("albumSales");
                String upcomingTours = rs.getString("upcomingTours");
                int phoneNum = rs.getInt("phoneNum");
                String website = rs.getString("website");
                String streamingPlatforms = rs.getString("streamingPlatforms");
                String mostStreamedSong = rs.getString("mostStreamedSong");
                String otherOccupation = rs.getString("otherOccupation");
                
                //displaying the results found on the table
                System.out.format("found artists: " + artist, grammyNominated, latestAlbum, genre, albumSales, upcomingTours, phoneNum, website, streamingPlatforms, mostStreamedSong, otherOccupation);
            }
        }
        catch(Exception e)
        {
            //telling user that the artist they searched for wasn't found
            System.out.println("Error: couldn't find artist - check spelling");
        }
        return temp;
    }
    
    //method to arrange/sort the artist in the database
    public void sort()
    {
        for(int i=0; i<sizeArt-1; i++)
        {
            for(int x=i+1; x<sizeArt; x++)
            {
                //if statement comparing the artist names to see which one comes first
                if(artistArr[i].getArtist().compareToIgnoreCase(artistArr[x].getArtist())>0)
                {
                    //rearranging the artists
                    Artist temp = artistArr[x];
                    artistArr[x] = artistArr[i];
                    artistArr[i] = temp;
                }
            }
        }
    }
    
    //method used to add artists for the user
    public String addArtist(Artist a)
    {
        //using sql code INSERT INTO to give the user power to enter a artist they would like to enter
        String sql = "INSERT INTO Artists (artists, grammyNominated, latestALbum, genre, albumSales, upcomingTours, phoneNum, website, streamingPlatform, mostStreamedSong, otherOccupation "
                + " VALUES('" + a.getArtist() + "', '" + a.isGrammyNominated() + "', '" + a.getLatestAlbum() + "', '" + a.getGenre() + "', '" + a.getAlbumSales() + "', '" 
                + a.getUpcomingTours() + "', '" + a.getPhoneNum() + "', '" + a.getWebsite() + "', '" + a.getMostStreamedSong() + "', '" + a.getOtherOccupation() + "');";
        
        //try statement for dangerous code
        try
        {
            //using DB to update the rows in table so the user can insert whatever data they want to insert
            dbMan.updateQuery(sql);
            artistArr[sizeArt] = a;
            //adding a new artist to array
            sql = "SELECT artist FROM tblArtists WHERE artist = '" + a.getArtist() + "';";
            //used to ask for the information to be brought over into java
            ResultSet rs = dbMan.queryDatabase(sql);
            rs.next();
            sizeArt++;
            return "Artist successfully added";
        }
        //catch statement if adding a variable was unsuccessful
        catch(SQLException d)
        {
            System.out.println("Error: Could not add artist to array");
        }
        //returning message to user
        return "Artist couldn't successfully be added";
    }
    
    public String deleteArtist(String art)
    {
        //hard coding delete query 
        String sql = "DELETE* FROM tblArtists WHERE artist = '" + art + "';";
        
        try
        {
            //updating the database again
            dbMan.updateQuery(sql);
            
            //loop made to delete the record
            for(int i=0; i<sizeArt; i++)
            {
                if(artistArr[i].getArtist()==art)
                {
                    //this for loop using minus will delete the record
                    for(int f=0; f<sizeArt-1; f++)
                    {
                        artistArr[f] = artistArr[f+1];
                    }
                    System.out.println("Deletion successful");
                }
                //making array size smaller because one Artist has been deleted
                sizeArt--;
            }
        }
        catch(SQLException q)
        {
            System.out.println("Error: couldn't delete row selected");
        }
        return "deletion unsuccessful";
    }
    //method to search for an artist in table in GUI
    public int searchArtist(String artist)
    {
        int i = 0;
        int pos = -1;
        boolean found = false;
        
        while(found == false && i<sizeArt)
        {
            //checking if artist user entered is equal to the artist it's supposed to be equal to
            if(artist.equalsIgnoreCase(artistArr[i].getArtist()))
            {
                pos = i;
            }
            //keeping track of this position
            i++;
        }
        //returning the position so that we know where to find the artist
        return pos;
    }
    
    public void updateArtists(int aI, String a, boolean gN, String lA, String g, int aS, String uT, int pN, String w, String sP, String mS, String oP)
    {
        //running sql code to update the artist table when needed
        String sql = "UPDATE tblArtists SET artist = '" + a +  "', grammyNominated = '" + gN + "', latestAlbum = '" + lA + 
                "', genre = '" + g + "', albumSales = '" + aS + "', upcomingTours = '" + uT + "', phoneNum = '" + pN +
                "', webiste = '" + w + "', streamingPlatform = '" + sP + "', mostStreamedSong = '" + mS + "', otherOccuaption = '" + oP +
                "' WHERE artistID = '" + aI + "';";
        
        //try and catch to catch dangerous code running from java to sql updating table
        try
        {
            //running sql to update the table in database
            dbMan.updateQuery(sql);
            int position = searchArtist(a);
            artistArr[position] = new Artist(aI, a, gN, lA, g, aS, uT, pN, w, sP, mS, oP);
        }
        catch(SQLException e)
        {
            //displaying error message to tell user table update couldn't be executed
            System.out.println("Error: couldn't update artist table");
        }
    }
    
    public String getArtist(String artist)
    {
        try
        {
            //searching from artist from database to check if it's the same with details 
            String sql = "SELECT artist FROM tblArtists WHERE artist = '" + artist + "';";
            ResultSet rs = dbMan.queryDatabase(sql);
            rs.next();
            return rs.getString("artist");
        }
        catch(SQLException e)
        {
            System.out.println("Error: Artist not found - check spelling");
        }
        //returning value to show user artist wasn't found
        return "Artist found";
    }
    
    //throwing back the artist array to be used
    public Artist[] getArtistArr()
    {
        return artistArr;
    }
    
    public int getSizeL()
    {
        return sizeArt;
    }
    
    //toString method used to display all the updated values
    public String toString()
    {
        String temp = "";
        //for loop runs everytime there is a new artist object that needs to be added into the array
        for(int i=0; i<sizeArt; i++)
        {
            temp = temp + artistArr[i] + "\n";
        }
        //returning to temp with all the updated values
        return temp;
    }
}
