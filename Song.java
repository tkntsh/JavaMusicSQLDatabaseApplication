/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author cha09nts001.1
 */
public class Song 
{
    //mirroring database with private entities of the song object
    private int artistID;
    private String songName;
    private Date releaseDate;
    private String genre;
    private String duration;
    private String album;
    
    //creating default values for the song object
    public Song(int artistID, String songName, Date releaseDate, String genre, String duration, String album) 
    {
        this.artistID = artistID;
        this.songName = songName;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.duration = duration;
        this.album = album;
    }
    
    //creating get methods to get the vaules of the song object
    public int getArtistID() 
    {
        return artistID;
    }

    public String getSongName() 
    {
        return songName;
    }

    public Date getReleaseDate() 
    {
        return releaseDate;
    }

    public String getGenre() 
    {
        return genre;
    }

    public String getDuration() 
    {
        return duration;
    }

    public String getAlbum() 
    {
        return album;
    }
    
    //creating set methods to give the entities of the object values
    public void setArtistID(int artistID) 
    {
        this.artistID = artistID;
    }

    public void setSongName(String songName) 
    {
        this.songName = songName;
    }

    public void setReleaseDate(Date releaseDate) 
    {
        this.releaseDate = releaseDate;
    }

    public void setGenre(String genre) 
    {
        this.genre = genre;
    }

    public void setDuration(String duration) 
    {
        this.duration = duration;
    }

    public void setAlbum(String album) 
    {
        this.album = album;
    }
    
    //creating toString method to display what's currrntly in the song object
    @Override
    public String toString() 
    {
        return "Song{" + "artistID=" + artistID + ", songName=" + songName + ", releaseDate=" + releaseDate + ", genre=" + genre + ", duration=" + duration + ", album=" + album + '}';
    }
}
