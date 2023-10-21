package com.sportsday.rest.webservices.restfulwebservices.Service;

import com.sportsday.rest.webservices.restfulwebservices.Model.Event;
import com.sportsday.rest.webservices.restfulwebservices.Model.EventRegistration;
import com.sportsday.rest.webservices.restfulwebservices.Model.EventResponseDTO;
import com.sportsday.rest.webservices.restfulwebservices.Model.Users;
import com.sportsday.rest.webservices.restfulwebservices.Repository.EventRepository;
import com.sportsday.rest.webservices.restfulwebservices.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventRegistrationService eventRegistrationService;

    public List<EventResponseDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventResponseDTO> eventResponseList = new ArrayList<>();

        for (Event event : events) {
            EventResponseDTO eventResponse = new EventResponseDTO(
                    event.getId(),
                    event.getName(),
                    event.getCategory(),
                    event.getStartTime(),
                    event.getEndTime()
            );
            eventResponseList.add(eventResponse);
        }

        return eventResponseList;
    }

    public List<EventResponseDTO> getRegisteredEvents(Long userId) {
        Users user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<Event> registeredEvents = eventRegistrationService.getEventsRegisteredByUser(user);
            List<EventResponseDTO> eventResponseList = new ArrayList<>();
            for (Event event : registeredEvents) {
                EventResponseDTO eventResponse = new EventResponseDTO(
                        event.getId(),
                        event.getName(),
                        event.getCategory(),
                        event.getStartTime(),
                        event.getEndTime()
                );
                eventResponseList.add(eventResponse);
            }
            return eventResponseList;
        }
        return null; // Handle user not found
    }

    public boolean registerUserForEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        Users user = userRepository.findById(userId).orElse(null);

        if (event != null && user != null) {
            if (!eventRegistrationService.isRegistered(event, user)) {
                EventRegistration registration = new EventRegistration();
                registration.setEvent(event);
                registration.setUser(user);
                registration.setRegistrationDate(LocalDateTime.now());
                eventRegistrationService.createEventRegistration(registration);
                return true;
            }
        }
        return false; // Handle event not found or user already registered
    }

    public boolean unregisterUserFromEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        Users user = userRepository.findById(userId).orElse(null);

        if (event != null && user != null) {
            if (eventRegistrationService.isRegistered(event, user)) {
                EventRegistration registration = eventRegistrationService.findByEventAndUser(event, user);
                eventRegistrationService.deleteEventRegistration(registration);
                return true;
            }
        }
        return false; // Handle event not found or user was not registered
    }
}