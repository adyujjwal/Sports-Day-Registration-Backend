package com.sportsday.rest.webservices.restfulwebservices.Repository;

import com.sportsday.rest.webservices.restfulwebservices.Model.Event;
import com.sportsday.rest.webservices.restfulwebservices.Model.EventRegistration;
import com.sportsday.rest.webservices.restfulwebservices.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    List<EventRegistration> findByUser(Users user);
    List<EventRegistration> findByEvent(Event event);
    boolean existsByEventAndUser(Event event, Users user);
    EventRegistration findByEventAndUser(Event event, Users user);
}
