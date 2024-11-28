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
public class Artist 
{
    //mirroring my database in sql
    private int artistID;
    private String artist;
    private boolean grammyNominated;
    private String latestAlbum;
    private String genre;
    private int albumSales;
    private String upcomingTours;
    private int phoneNum;
    private String website;
    private String streamingPlatforms;
    private String mostStreamedSong;
    private String otherOccupation;
    
    //making default values the artist type
    public Artist(int artistID, String artist, boolean grammyNominated, String latestAlbum, String genre, int albumSales, String upcomingTours, int phoneNum, String website, String streamingPlatforms, String mostStreamedSong, String otherOccupation) 
    {
        this.artistID = artistID;
        this.artist = artist;
        this.grammyNominated = grammyNominated;
        this.latestAlbum = latestAlbum;
        this.genre = genre;
        this.albumSales = albumSales;
        this.upcomingTours = upcomingTours;
        this.phoneNum = phoneNum;
        this.website = website;
        this.streamingPlatforms = streamingPlatforms;
        this.mostStreamedSong = mostStreamedSong;
        this.otherOccupation = otherOccupation;
    }
    
    //creating get methods to throw the values over to where they are needed
    public int getArtistID()
    {
        return artistID;
    }
    
    public String getArtist() 
    {
        return artist;
    }

    public boolean isGrammyNominated() 
    {
        return grammyNominated;
    }

    public String getLatestAlbum() 
    {
        return latestAlbum;
    }

    public String getGenre() 
    {
        return genre;
    }

    public int getAlbumSales() 
    {
        return albumSales;
    }

    public String getUpcomingTours() 
    {
        return upcomingTours;
    }

    public int getPhoneNum() 
    {
        return phoneNum;
    }

    public String getWebsite() 
    {
        return website;
    }

    public String getStreamingPlatform() 
    {
        return streamingPlatforms;
    }

    public String getMostStreamedSong() 
    {
        return mostStreamedSong;
    }

    public String getOtherOccupation() 
    {
        return otherOccupation;
    }
    
    //creating set values to make sure they have a default value 
    public void setArtist(String artist) 
    {
        this.artist = artist;
    }

    public void setGrammyNominated(boolean grammyNominated) 
    {
        this.grammyNominated = grammyNominated;
    }

    public void setLatestAlbum(String latestAlbum) 
    {
        this.latestAlbum = latestAlbum;
    }

    public void setGenre(String genre) 
    {
        this.genre = genre;
    }

    public void setAlbumSales(int albumSales) 
    {
        this.albumSales = albumSales;
    }

    public void setUpcomingTours(String upcomingTours) 
    {
        this.upcomingTours = upcomingTours;
    }

    public void setPhoneNum(int phoneNum) 
    {
        this.phoneNum = phoneNum;
    }

    public void setWebsite(String website) 
    {
        this.website = website;
    }

    public void setStreamingPlatform(String streamingPlatforms) 
    {
        this.streamingPlatforms = streamingPlatforms;
    }

    public void setMostStreamedSong(String mostStreamedSong) 
    {
        this.mostStreamedSong = mostStreamedSong;
    }

    public void setOtherOccupation(String otherOccupation) 
    {
        this.otherOccupation = otherOccupation;
    }
    
    //creating toString method to display the contents of the current object
    @Override
    public String toString() 
    {
        return "Artist{" + "artist=" + artist + ", grammyNominated=" + grammyNominated + ", latestAlbum=" + latestAlbum + ", genre=" + genre + ", albumSales=" + albumSales + ", upcomingTours=" + upcomingTours + ", phoneNum=" + phoneNum + ", website=" + website + ", streamingPlatforms=" + streamingPlatforms + ", mostStreamedSong=" + mostStreamedSong + ", otherOccupation=" + otherOccupation + '}';
    }
    
}
