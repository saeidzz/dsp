/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dsproject;


/**
 *
 * @author saeid zangeneh
 */
public class User {
    private String userName;
    private int userId;

    public User() {
    }
    
    public User(String userName) {
        this.userName = userName;
    }
    public User(String userName,int userId) {
        this.userName = userName;
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    

    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }


 
    
    
 
    
    
    
}

