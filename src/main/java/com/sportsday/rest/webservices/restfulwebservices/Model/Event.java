package com.sportsday.rest.webservices.restfulwebservices.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private LocalDateTime start_Time;
    private LocalDateTime end_Time;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<EventRegistration> registrations = new HashSet<>();

    public Event() {
    }

    public Event(Long id, String name, String category, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.start_Time = startTime;
        this.end_Time = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getStartTime() {
        return start_Time;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.start_Time = startTime;
    }

    public LocalDateTime getEndTime() {
        return end_Time;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.end_Time = endTime;
    }

    public Set<EventRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<EventRegistration> registrations) {
        this.registrations = registrations;
    }

}
