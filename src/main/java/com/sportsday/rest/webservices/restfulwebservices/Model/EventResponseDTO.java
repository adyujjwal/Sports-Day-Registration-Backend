package com.sportsday.rest.webservices.restfulwebservices.Model;

import java.time.LocalDateTime;

public class EventResponseDTO {
    private Long id;
    private String eventName;
    private String eventCategory;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EventResponseDTO(Long id, String eventName, String eventCategory, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.eventName = eventName;
        this.eventCategory = eventCategory;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
