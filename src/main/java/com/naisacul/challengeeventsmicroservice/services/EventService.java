package com.naisacul.challengeeventsmicroservice.services;

import com.naisacul.challengeeventsmicroservice.domain.Event;
import com.naisacul.challengeeventsmicroservice.domain.Subscription;
import com.naisacul.challengeeventsmicroservice.dtos.EmailRequestDTO;
import com.naisacul.challengeeventsmicroservice.dtos.EventRequestDTO;
import com.naisacul.challengeeventsmicroservice.exceptions.EventFullException;
import com.naisacul.challengeeventsmicroservice.exceptions.EventNotFoundException;
import com.naisacul.challengeeventsmicroservice.repositories.EventRepository;
import com.naisacul.challengeeventsmicroservice.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmailServiceClient emailServiceClient;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findUpcomingEvents(LocalDateTime.now());
    }

    public Event createEvent(EventRequestDTO eventRequest) {
        Event newEvent = new Event(eventRequest);
        return eventRepository.save(newEvent);
    }

    private Boolean isEventFull(Event event){
        return event.getRegisteredParticipants() >= event.getMaxParticipants();
    }

    public void registerParticipant(String eventId, String participantEmail) {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);

        if(isEventFull(event)) {
            throw new EventFullException();
        }

        Subscription subscription = new Subscription(event, participantEmail);
        subscriptionRepository.save(subscription);

        event.setRegisteredParticipants(event.getRegisteredParticipants() + 1);

        EmailRequestDTO emailRequest = new EmailRequestDTO(participantEmail, "Confirmação de Inscrição",
                "Você foi inscrito no evento com sucesso!");

        emailServiceClient.sendEmail(emailRequest);
    }
}
