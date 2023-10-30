package com.sportsday.rest.webservices.restfulwebservices.ControllerTest;

import com.sportsday.rest.webservices.restfulwebservices.Controller.RegistrationController;
import com.sportsday.rest.webservices.restfulwebservices.Model.EventResponseDTO;
import com.sportsday.rest.webservices.restfulwebservices.Model.Users;
import com.sportsday.rest.webservices.restfulwebservices.Service.EventService;
import com.sportsday.rest.webservices.restfulwebservices.Service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    private UserService userService;

    @Mock
    private EventService eventService;

    @Test
    public void testCreateUser_Success() {
        Users newUser = new Users();
        newUser.setUsername("newuser");
        newUser.setFirstname("John");
        newUser.setLastname("Doe");
        newUser.setEmail("newuser@example.com");

        Mockito.when(userService.createUser(newUser)).thenReturn(newUser);

        ResponseEntity response = registrationController.createUser(newUser);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateUser_UsernameExists() {
        Users existingUser = new Users();
        existingUser.setUsername("existinguser");

        Mockito.when(userService.createUser(existingUser)).thenReturn(null);

        ResponseEntity response = registrationController.createUser(existingUser);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testLoginUser_UserExists() {
        Users user = new Users();
        user.setUsername("testuser");

        Mockito.when(userService.loginUser("testuser")).thenReturn(user);

        ResponseEntity response = registrationController.loginUser("testuser");

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLoginUser_UserDoesNotExist() {
        Mockito.when(userService.loginUser("nonexistentuser")).thenReturn(null);

        ResponseEntity response = registrationController.loginUser("nonexistentuser");

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetAllEvents() {
        List<EventResponseDTO> events = new ArrayList<>();
        // Add event objects to the list

        Mockito.when(eventService.getAllEvents()).thenReturn(events);

        ResponseEntity response = registrationController.getAllEvents();

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetRegisteredEvents_UserIdExists() {
        List<EventResponseDTO> registeredEvents = new ArrayList<>();
        // Add registered event objects to the list

        Mockito.when(eventService.getRegisteredEvents(1L)).thenReturn(registeredEvents);

        ResponseEntity response = registrationController.getRegisteredEvents(1L);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetRegisteredEvents_UserIdDoesNotExist() {
        Mockito.when(eventService.getRegisteredEvents(2L)).thenReturn(null);

        ResponseEntity response = registrationController.getRegisteredEvents(2L);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    public void testRegisterForEvent_Success() {
        Long eventId = 1L;
        Long userId = 1L;

        Mockito.when(eventService.registerUserForEvent(eventId, userId)).thenReturn(true);

        ResponseEntity response = registrationController.registerForEvent(eventId, userId);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRegisterForEvent_Failure() {
        Long eventId = 1L;
        Long userId = 1L;

        Mockito.when(eventService.registerUserForEvent(eventId, userId)).thenReturn(false);

        ResponseEntity response = registrationController.registerForEvent(eventId, userId);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testUnregisterFromEvent_Success() {
        Long eventId = 1L;
        Long userId = 1L;

        Mockito.when(eventService.unregisterUserFromEvent(eventId, userId)).thenReturn(true);

        ResponseEntity response = registrationController.unregisterFromEvent(eventId, userId);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUnregisterFromEvent_Failure() {
        Long eventId = 1L;
        Long userId = 1L;

        Mockito.when(eventService.unregisterUserFromEvent(eventId, userId)).thenReturn(false);

        ResponseEntity response = registrationController.unregisterFromEvent(eventId, userId);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}

