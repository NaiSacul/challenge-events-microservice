package com.naisacul.challengeeventsmicroservice.controllers;

import com.naisacul.challengeeventsmicroservice.domain.Event;
import com.naisacul.challengeeventsmicroservice.dtos.EventRequestDTO;
import com.naisacul.challengeeventsmicroservice.dtos.SubscriptionRequestDTO;
import com.naisacul.challengeeventsmicroservice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/upcoming")
    public List<Event> getUpcomingEvents() {
        return eventService.getUpcomingEvents();
    }

    @PostMapping
    public Event createEvent(@RequestBody EventRequestDTO event) {
        return eventService.createEvent(event);
    }

    @PostMapping("/{eventId}/register")
    public void registerParticipant(@PathVariable String eventId, @RequestBody SubscriptionRequestDTO
            subscriptionRequest) {
        eventService.registerParticipant(eventId, subscriptionRequest.participantEmail());
    }
}
