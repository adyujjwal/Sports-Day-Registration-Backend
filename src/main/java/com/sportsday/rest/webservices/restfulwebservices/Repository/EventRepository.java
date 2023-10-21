package com.sportsday.rest.webservices.restfulwebservices.Repository;

import com.sportsday.rest.webservices.restfulwebservices.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
