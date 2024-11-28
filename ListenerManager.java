/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entities.DB;
import Entities.Listener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cha09nts001.1
 */
public class ListenerManager 
{
    //creating listener array to store different listeners
    private Listener[] listArr = new Listener[100];
    //size variable to calculate how many listener objects we have
    int sizeList = 0;
    //creatig dbManager to be used as transporter to retrieve data from access
    private DB dbMan = new DB();
    
    public ListenerManager()
    {
        try
        {
            //sql query to select all the data in the listener table 
            String sql = "SELECT* FROM tblListeners";
            //connection between access and java
            ResultSet rs = dbMan.queryDatabase(sql);
            
            //while statement checking if there's anymore objects that need to be collected
            while(rs.next())
            {
                //coping the table names in access to extract the data and store it in java
                Listener list = new Listener(rs.getInt("listenerID"), 
                        rs.getString("listener"), 
                        rs.getString("name"), 
                        rs.getString("surname"), 
                        rs.getString("preferredGenre"), 
                        rs.getString("username"));
                
                //using created lsitener array to store current extracted listener in record from access
                listArr[sizeList] = list;
                //keeping track of how many listener are in the object
                sizeList++;
            }
        }
        //sql catch statement to display error message if we have a problem populating
        catch(SQLException s)
        {
            System.out.println("Error: ListenerManager populating array error");
        }
    }
    
    //method to arrange/sort the listeners in the database
    public void sort()
    {
        for(int i=0; i<sizeList-1; i++)
        {
            for(int x=i+1; x<sizeList; x++)
            {
                //if statement comparing the listener names to see which one comes first
                if(listArr[i].getName().compareToIgnoreCase(listArr[x].getName())>0)
                {
                    //rearranging the songs
                    Listener temp = listArr[x];
                    listArr[x] = listArr[i];
                    listArr[i] = temp;
                }
            }
        }
    }
    
    //method used to add songs for the user
    public String addListeners(Listener l)
    {
        //using sql code INSERT INTO to give the user power to enter a listener they would like to enter
        String sql = "INSERT INTO Listeners (listener, name, surname, surname, preferredGenre, username "
                + " VALUES('" + l.getListener() + "', '" + l.getName() + "', '" + l.getSurname() + "', '" + l.getPrefferedGenre() + "', '" + l.getUsername() + "');";
        
        //try statement for dangerous code
        try
        {
            //using DB to update the rows in table so the user can insert whatever data they want to insert
            dbMan.updateQuery(sql);
            listArr[sizeList] = l;
            //adding a new listener to array
            sql = "SELECT listener FROM Song WHERE name = '" + l.getName() + "';";
            //used to ask for the information to be brought over into java
            ResultSet rs = dbMan.queryDatabase(sql);
            rs.next();
            sizeList++;
            return "Listener successfully added";
        }
        //catch statement if adding a variable was unsuccessful
        catch(SQLException f)
        {
            System.out.println("Error: Could not add listener to array");
        }
        return "Listener couldn't successfully be added";
    }
    
    public String deleteListener(String list)
    {
        //hard coding delete query 
        String sql = "DELETE* FROM listeners WHERE name = '" + list + "';";
        
        try
        {
            //updating the database again
            dbMan.updateQuery(sql);
            
            //loop made to delete the record
            for(int i=0; i<sizeList; i++)
            {
                if(listArr[i].getListener()==list)
                {
                    //this for loop using minus will delete the record
                    for(int f=0; f<sizeList-1; f++)
                    {
                        listArr[f] = listArr[f+1];
                    }
                    System.out.println("Deletion successful");
                }
                //making array size smaller because one Listener has been deleted
                sizeList--;
            }
        }
        catch(SQLException q)
        {
            System.out.println("Error: couldn't delete row selected");
        }
        return "deletion unsuccessful";
    }
    
    //method to search for the listener
    public int searchListener(String listener)
    {
        int i = 0;
        int pos = -1;
        boolean found = false;
        
        while(found == false && i<sizeList)
        {
            //checking if listener user entered is equal to the listener it's supposed to be equal to
            if(listener.equalsIgnoreCase(listArr[i].getListener()))
            {
                pos = i;
            }
            //keeping track of this position
            i++;
        }
        //returning the position so that we know where to find the name
        return pos;
    }
    
    public void updateListener(int lI, String l, String n, String s, String pG, String u)
    {
        //running sql code to update the listener table when needed
        String sql = "UPDATE tblListeners SET listener = '" + l + "', name = '" + n + "', surname = '" + s +
                "', preferredGenre = '" + pG + "', username = '" + u +
                "' WHERE listenerID = '" + lI + "';";
        
        //try and catch to catch dangerous code running from java to sql updating table
        try
        {
            //running sql to update the table in database
            dbMan.updateQuery(sql);
            int position = searchListener(l);
            listArr[position] = new Listener(lI, l, n, s, pG, u);
        }
        catch(SQLException e)
        {
            //displaying error message to tell user table update couldn't be executed
            System.out.println("Error: couldn't update listener table");
        }
    }
    
    //get array of listerners to be thrown back
    public Listener[] getListenerArr()
    {
        return listArr;
    }
    
    public int getSizeL()
    {
        return sizeList;
    }
    
    //toString method used to display all the updated values
    public String toString()
    {
        String temp = "";
        //for loop runs everytime there is a new listener object that needs to be added into the array
        for(int i=0; i<sizeList; i++)
        {
            temp = temp + listArr[i] + "\n";
        }
        //returning to temp with all the updated values
        return temp;
    }
}
