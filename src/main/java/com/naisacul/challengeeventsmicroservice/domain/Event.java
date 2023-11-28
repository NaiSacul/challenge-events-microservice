package com.naisacul.challengeeventsmicroservice.domain;

import com.naisacul.challengeeventsmicroservice.dtos.EventRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="event")
@Table(name="event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int maxParticipants;

    private int registeredParticipants;

    private String date;

    private String title;

    private String description;

    public Event(EventRequestDTO eventRequest){

        this.maxParticipants = eventRequest.maxParticipants();

        this.registeredParticipants = eventRequest.registeredParticipants();

        this.date = eventRequest.date();

        this.title = eventRequest.title();

        this.description = eventRequest.description();
    }
}
