package com.cs4125.bookingapp.model;

import com.cs4125.bookingapp.model.entities.User;
import org.springframework.stereotype.Service;

@Service
public class ConcreteUserFactory implements UserFactory {

    @Override
    public User getUser(String userType, String username, String password, String email) {
        if(userType == null) {
            return null;
        }
        else if(userType.equalsIgnoreCase("NORMAL_USER")) {
            return new User(username, password, email, 1);
        }
        else if(userType.equalsIgnoreCase("ADMIN")) {
            return new User(username, password, email, 2);
        }

        return null;
    }
}
