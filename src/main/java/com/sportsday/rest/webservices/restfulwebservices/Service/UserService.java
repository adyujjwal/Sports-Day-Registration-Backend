package com.sportsday.rest.webservices.restfulwebservices.Service;

import com.sportsday.rest.webservices.restfulwebservices.Exception.SportsdayException;
import com.sportsday.rest.webservices.restfulwebservices.Model.Users;
import com.sportsday.rest.webservices.restfulwebservices.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users createUser(Users users) {
        Users existingUser = userRepository.findByUsername(users.getUsername());
        if (existingUser != null) {
            throw new SportsdayException("Username already exists");
        }
        return userRepository.save(users);
    }

    public Users loginUser(String username) {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new SportsdayException("Username not found");
        }
        return user;
    }
}
