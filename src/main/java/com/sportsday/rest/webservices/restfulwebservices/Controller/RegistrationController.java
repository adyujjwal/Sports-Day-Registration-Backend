package com.sportsday.rest.webservices.restfulwebservices.Controller;

import com.sportsday.rest.webservices.restfulwebservices.Model.EventResponseDTO;
import com.sportsday.rest.webservices.restfulwebservices.Model.Users;
import com.sportsday.rest.webservices.restfulwebservices.Service.EventRegistrationService;
import com.sportsday.rest.webservices.restfulwebservices.Service.EventService;
import com.sportsday.rest.webservices.restfulwebservices.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRegistrationService eventRegistrationService;

    // Create User API
    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody Users user) {
        Users createdUser = userService.createUser(user);
        if (createdUser != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().body("Username already present, Kindly choose a different username.");
        }
    }

    // Login User API
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestParam String username) {
        Users user = userService.loginUser(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    // Get All Events API
    @GetMapping("/events")
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // Get All Registered Events List API
    @GetMapping("/events/registered/{userId}")
    public ResponseEntity<List<EventResponseDTO>> getRegisteredEvents(@PathVariable Long userId) {
        List<EventResponseDTO> registeredEvents = eventService.getRegisteredEvents(userId);
        if (registeredEvents != null) {
            return ResponseEntity.ok(registeredEvents);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    // Register Event API
    @PostMapping("/events/register")
    public ResponseEntity<String> registerForEvent(
            @RequestParam Long eventId,
            @RequestParam Long userId
    ) {
        boolean registrationSuccess = eventService.registerUserForEvent(eventId, userId);
        if (registrationSuccess) {
            return ResponseEntity.ok("Registered for the event");
        } else {
            return ResponseEntity.badRequest().body("Event not found or user is already registered");
        }
    }

    // Unregister Event API
    @PostMapping("/events/unregister")
    public ResponseEntity<String> unregisterFromEvent(
            @RequestParam Long eventId,
            @RequestParam Long userId
    ) {
        boolean unregistrationSuccess = eventService.unregisterUserFromEvent(eventId, userId);
        if (unregistrationSuccess) {
            return ResponseEntity.ok("Unregistered from the event");
        } else {
            return ResponseEntity.badRequest().body("Event not found or user was not registered");
        }
    }
}
