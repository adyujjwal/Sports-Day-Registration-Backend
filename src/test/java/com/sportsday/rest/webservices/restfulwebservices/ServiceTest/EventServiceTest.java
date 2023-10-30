package com.sportsday.rest.webservices.restfulwebservices.ServiceTest;

import com.sportsday.rest.webservices.restfulwebservices.Model.Event;
import com.sportsday.rest.webservices.restfulwebservices.Model.EventResponseDTO;
import com.sportsday.rest.webservices.restfulwebservices.Model.Users;
import com.sportsday.rest.webservices.restfulwebservices.Repository.EventRepository;
import com.sportsday.rest.webservices.restfulwebservices.Repository.UserRepository;
import com.sportsday.rest.webservices.restfulwebservices.Service.EventRegistrationService;
import com.sportsday.rest.webservices.restfulwebservices.Service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventRegistrationService eventRegistrationService;

    @BeforeEach
    public void setUp() {
        // Set up any necessary mock behavior here
    }

    @Test
    public void testGetAllEvents() {
        // Create a list of Event objects for mocking
        List<Event> events = new ArrayList<>();
        events.add(new Event(1L, "Event 1", "Category 1", LocalDateTime.now(), LocalDateTime.now()));
        events.add(new Event(2L, "Event 2", "Category 2", LocalDateTime.now(), LocalDateTime.now()));

        // Mock the eventRepository's behavior
        Mockito.when(eventRepository.findAll()).thenReturn(events);

        // Call the method you want to test
        List<EventResponseDTO> result = eventService.getAllEvents();

        // Assert the result based on the expected outcome
        // You can use assertions to compare the expected and actual results
        assert(result.size() == 2);
        assert(result.get(0).getEventName().equals("Event 1"));
        assert(result.get(1).getEventCategory().equals("Category 2"));
        // Add more specific assertions as needed
    }

    @Test
    public void testGetRegisteredEvents() {
        // Mock the userRepository's behavior to return a user
        Users user = new Users(1L, "John", "doe", "jdoe", "jdoe@hotmail.com");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Create a list of Event objects for mocking
        List<Event> registeredEvents = new ArrayList<>();
        registeredEvents.add(new Event(1L, "Event 1", "Category 1", LocalDateTime.now(), LocalDateTime.now()));

        // Mock the eventRegistrationService's behavior
        Mockito.when(eventRegistrationService.getEventsRegisteredByUser(user)).thenReturn(registeredEvents);

        // Call the method you want to test
        List<EventResponseDTO> result = eventService.getRegisteredEvents(1L);

        // Assert the result based on the expected outcome
        assert(result.size() == 1);
        assert(result.get(0).getEventName().equals("Event 1"));
        // Add more specific assertions as needed
    }

    @Test
    public void testRegisterUserForEvent() {
        // Mock the eventRepository's behavior to return an event
        Event event = new Event(1L, "Event 1", "Category 1", LocalDateTime.now(), LocalDateTime.now());
        Mockito.when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        // Mock the userRepository's behavior to return a user
        Users user = new Users(1L, "John", "doe", "jdoe", "jdoe@hotmail.com");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Mock the eventRegistrationService's behavior to return false (user is not registered)
        Mockito.when(eventRegistrationService.isRegistered(event, user)).thenReturn(false);

        // Call the method you want to test
        boolean result = eventService.registerUserForEvent(1L, 1L);

        // Assert the result based on the expected outcome
        assert(result); // Registration should be successful
    }

    @Test
    public void testUnregisterUserFromEvent() {
        // Mock the eventRepository's behavior to return an event
        Event event = new Event(1L, "Event 1", "Category 1", LocalDateTime.now(), LocalDateTime.now());
        Mockito.when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        // Mock the userRepository's behavior to return a user
        Users user = new Users(1L, "John", "doe", "jdoe", "jdoe@hotmail.com");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Mock the eventRegistrationService's behavior to return true (user is registered)
        Mockito.when(eventRegistrationService.isRegistered(event, user)).thenReturn(true);

        // Call the method you want to test
        boolean result = eventService.unregisterUserFromEvent(1L, 1L);

        // Assert the result based on the expected outcome
        assert(result); // Unregistration should be successful
    }
}

