package com.sportsday.rest.webservices.restfulwebservices.Service;

import com.sportsday.rest.webservices.restfulwebservices.Model.Event;
import com.sportsday.rest.webservices.restfulwebservices.Model.EventRegistration;
import com.sportsday.rest.webservices.restfulwebservices.Model.Users;
import com.sportsday.rest.webservices.restfulwebservices.Repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventRegistrationService {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    public EventRegistration createEventRegistration(EventRegistration registration) {
        registration.setRegistrationDate(LocalDateTime.now());
        return eventRegistrationRepository.save(registration);
    }

    public List<Event> getEventsRegisteredByUser(Users user) {
        List<EventRegistration> registrations = eventRegistrationRepository.findByUser(user);
        List<Event> events = new ArrayList<>();
        for (EventRegistration registration : registrations) {
            events.add(registration.getEvent());
        }
        return events;
    }

    public List<EventRegistration> getEventRegistrationsByEvent(Event event) {
        return eventRegistrationRepository.findByEvent(event);
    }

    public void deleteEventRegistration(EventRegistration registration) {
        eventRegistrationRepository.delete(registration);
    }

    public boolean isRegistered(Event event, Users user) {
        return eventRegistrationRepository.existsByEventAndUser(event, user);
    }

    public EventRegistration findByEventAndUser(Event event, Users user) {
        return eventRegistrationRepository.findByEventAndUser(event, user);
    }
}
