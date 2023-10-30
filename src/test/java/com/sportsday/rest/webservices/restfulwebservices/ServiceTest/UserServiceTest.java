package com.sportsday.rest.webservices.restfulwebservices.ServiceTest;

import com.sportsday.rest.webservices.restfulwebservices.Exception.SportsdayException;
import com.sportsday.rest.webservices.restfulwebservices.Model.Users;
import com.sportsday.rest.webservices.restfulwebservices.Repository.UserRepository;
import com.sportsday.rest.webservices.restfulwebservices.Service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        // Create a sample user for testing
        Users newUser = new Users();
        newUser.setUsername("testuser");

        // Mock the behavior of userRepository.findByUsername to return null (user does not exist)
        Mockito.when(userRepository.findByUsername("testuser")).thenReturn(null);

        // Mock the behavior of userRepository.save to return the user
        Mockito.when(userRepository.save(newUser)).thenReturn(newUser);

        // Call the method you want to test
        Users result = userService.createUser(newUser);

        // Assert the result based on the expected outcome
        assert(result != null);
        assert(result.getUsername().equals("testuser"));
        // Add more specific assertions as needed
    }

    @Test
    public void testCreateUser_UsernameAlreadyExists() {
        // Create a sample user for testing
        Users existingUser = new Users();
        existingUser.setUsername("testuser");

        // Mock the behavior of userRepository.findByUsername to return the existing user
        Mockito.when(userRepository.findByUsername("testuser")).thenReturn(existingUser);

        // Create a new user with the same username
        Users newUser = new Users();
        newUser.setUsername("testuser");

        // Assert that a SportsdayException is thrown when createUser is called
        Assert.assertThrows(SportsdayException.class, () -> userService.createUser(newUser));
    }

    @Test
    public void testLoginUser() {
        // Create a sample user for testing
        Users existingUser = new Users();
        existingUser.setUsername("testuser");

        // Mock the behavior of userRepository.findByUsername to return the existing user
        Mockito.when(userRepository.findByUsername("testuser")).thenReturn(existingUser);

        // Call the method you want to test
        Users result = userService.loginUser("testuser");

        // Assert the result based on the expected outcome
        Assert.assertEquals("testuser", result.getUsername());


    }

    @Test
    public void testLoginUser_UserNotFound() {
        // Mock the behavior of userRepository.findByUsername to return null (user not found)
        Mockito.when(userRepository.findByUsername("nonexistentuser")).thenReturn(null);

        // Assert that a SportsdayException is thrown when loginUser is called with a non-existent username
        Assert.assertThrows(SportsdayException.class, () -> userService.loginUser("nonexistentuser"));
    }



}
