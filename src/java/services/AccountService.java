package services;

import dataaccess.UserDB;
import models.User;

public class AccountService {
    
    public User login(String email, String password) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    // Find user based off of their email, returning a User object
    public User FindUser( String email )
    {
        UserDB userDB = new UserDB();
        
        try
        {
            User user = userDB.get( email );
            return user;
        }
        catch( Exception ex ) {}
        
        return null;
    }
}
