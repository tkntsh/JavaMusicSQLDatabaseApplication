/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entities.Users;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Bapi
 */
public class UserManager 
{
    //varaibles made for user population and keep track of the amount of users
    private Users userArr[] = new Users[200];
    private int size = 0;
    
    //default construtor
    public UserManager()
    {
        try
        {
            //getting users from Login textfile 
            Scanner scFile = new Scanner(new File("Login.txt"));
            //creating varibles to store data from textfile
            String line = "";
            String username = "";
            String password = "";
            
            //while scFile still has data in the file this loop will run
            while(scFile.hasNext())
            {
                //storing data in created variables
                line = scFile.nextLine();
                //using delimiter to split username and password
                Scanner scLine = new Scanner(line).useDelimiter("%");
                username = scFile.next();
                password = scFile.next();
                
                //adding the users in textfile to the user array created in this class
                userArr[size] = new Users(username, password);
                //keeping track of how many users are in the array
                size++;
                //closing scLine
                scLine.close();
            }
            //closing the whole file because we are done using/scanning through it
            scFile.close();
        }
        //catching exception
        catch(FileNotFoundException fe)
        {
            //telling user that the file wasn't found
            System.out.println("Error: File not found - check path");
        }
    }
    
    //creating method to check if username is correct
    public boolean loginDets(String uN)
    {
        int i = 0;
        int pos = -1;
        boolean checkPN = false;
        
        //using while loop because there is an unknown number of usernames to be entered 
        while(!checkPN && i<size)
        {
            if(uN.equals(userArr[i].getUsername()))
            {
                checkPN = true;
            }
            i++;
        }
        //returning the checkPN so it can used to check if the username is correct
        return checkPN;
    }
    
    //method to check the password is correct or not
    public boolean loginDetsPass(String pW)
    {
        int i = 0;
        int pos = -1;
        boolean checkN = false;
        
        //using while loop because there is an unknown number of passwords to be entered 
        while(!checkN && i<size)
        {
            if(pW.equals(userArr[i].getPassword()))
            {
                checkN = true;
            }
            i++;
        }
        //returning the checkN so it can used to check if the passwords is correct
        return checkN;
    }
    //creating a search method so the user can search for a user
    public int searchUser(String name)
    {
        int i = 0;
        int pos = -1;
        boolean found = false;
        
        while(found == false && i<size)
        {
            //checking if name is equal to the username it's supposed to be equal to
            if(name.equalsIgnoreCase(userArr[i].getUsername()))
            {
                pos = i;
            }
            //keeping track of this position
            i++;
        }
        //returning the position so that we know where to find the name
        return pos;
    }
    
    //creating a toString method to display the users
    public String toString()
    {
        String temp = "";
        //using a for loop to display each user one by one on their whole
        for(int i = 0; i<size; i++)
        {
            temp = temp + userArr[i] + "\n";
        }
        //returning temp with all updated users
        return temp;
    }
    
    
}
