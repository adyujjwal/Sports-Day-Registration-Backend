package com.sportsday.rest.webservices.restfulwebservices.ServiceTest;

import com.sportsday.rest.webservices.restfulwebservices.Model.Event;
import com.sportsday.rest.webservices.restfulwebservices.Model.EventRegistration;
import com.sportsday.rest.webservices.restfulwebservices.Model.Users;
import com.sportsday.rest.webservices.restfulwebservices.Repository.EventRegistrationRepository;
import com.sportsday.rest.webservices.restfulwebservices.Service.EventRegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EventRegistrationServiceTest {

    @InjectMocks
    private EventRegistrationService eventRegistrationService;

    @Mock
    private EventRegistrationRepository eventRegistrationRepository;

    @BeforeEach
    public void setUp() {
        // Set up any necessary mock behavior here
    }

    @Test
    public void testGetEventRegistrationsByEvent() {
        // Mock the eventRegistrationRepository's behavior to return a list of registrations
        Event event = new Event(1L, "Event 1", "Category 1", LocalDateTime.now(), LocalDateTime.now());
        List<EventRegistration> registrations = new ArrayList<>();
        registrations.add(new EventRegistration(new Users(1L, "John", "doe", "jdoe", "jdoe@hotmail.com"),event , LocalDateTime.now()));

        Mockito.when(eventRegistrationRepository.findByEvent(event)).thenReturn(registrations);

        // Call the method you want to test
        List<EventRegistration> result = eventRegistrationService.getEventRegistrationsByEvent(event);

        // Assert the result based on the expected outcome
        assert(result.size() == 1);
        assert(result.get(0).getUser().getUsername().equals("jdoe"));
        // Add more specific assertions as needed
    }

    @Test
    public void testDeleteEventRegistration() {
        // Mock the eventRegistrationRepository's behavior
        EventRegistration registration = new EventRegistration(new Users(1L, "John", "doe", "jdoe", "jdoe@hotmail.com"),new Event(1L, "Event 1", "Category 1", LocalDateTime.now(), LocalDateTime.now()),LocalDateTime.now());

        // Call the method you want to test
        eventRegistrationService.deleteEventRegistration(registration);

        // Verify that the delete method of eventRegistrationRepository is called with the registration
        Mockito.verify(eventRegistrationRepository, Mockito.times(1)).delete(registration);
    }

    @Test
    public void testIsRegistered() {
        // Mock the eventRegistrationRepository's behavior to return true (user is registered)
        Event event = new Event(1L, "Event 1", "Category 1", LocalDateTime.now(), LocalDateTime.now());
        Users user = new Users(1L, "John", "doe", "jdoe", "jdoe@hotmail.com");

        Mockito.when(eventRegistrationRepository.existsByEventAndUser(event, user)).thenReturn(true);

        // Call the method you want to test
        boolean result = eventRegistrationService.isRegistered(event, user);

        // Assert the result based on the expected outcome
        assert(result); // User should be registered for the event
    }

    @Test
    public void testFindByEventAndUser() {
        // Mock the eventRegistrationRepository's behavior to return a registration
        Event event = new Event(1L, "Event 1", "Category 1", LocalDateTime.now(), LocalDateTime.now());
        Users user = new Users(1L, "John", "doe", "jdoe", "jdoe@hotmail.com");
        EventRegistration registration = new EventRegistration(user, event, LocalDateTime.now());

        Mockito.when(eventRegistrationRepository.findByEventAndUser(event, user)).thenReturn(registration);

        // Call the method you want to test
        EventRegistration result = eventRegistrationService.findByEventAndUser(event, user);

        // Assert the result based on the expected outcome
        assert(result != null);
        // Add more specific assertions as needed
    }
}

