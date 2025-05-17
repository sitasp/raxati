package org.sage.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sage.entity.EventEntity;
import org.sage.entity.TopicEntity;
import org.sage.mapper.EventMapper;
import org.sage.mapper.TopicMapper;
import org.sage.object.domain.Event;
import org.sage.object.domain.Topic;
import org.sage.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class EventService {

    private final EventRepository eventRepository;

    private final EventMapper   eventMapper;

    public Optional<Event> fetchEventBySlug(String slug){
        Optional<EventEntity> eventEntityOptional = eventRepository.findBySlug(slug);
        if(eventEntityOptional.isPresent()){
            EventEntity eventEntity = eventEntityOptional.get();
            return Optional.ofNullable(eventMapper.toDomain(eventEntity));
        }
        return Optional.empty();
    }

    public Optional<Event> fetchEventById(String id){
        Optional<EventEntity> eventEntityOptional = eventRepository.findById(id);
        if(eventEntityOptional.isPresent()){
            EventEntity eventEntity = eventEntityOptional.get();
            return Optional.ofNullable(eventMapper.toDomain(eventEntity));
        }
        return Optional.empty();
    }

    public Optional<Event> saveEvent(Event event){
        try {
            EventEntity eventEntity = eventMapper.toEntity(event);
            eventRepository.persist(eventEntity);
            Event saved = eventMapper.toDomain(eventEntity);

            return Optional.of(saved);
        } catch (IllegalArgumentException e) {
            log.error("Error while saving event: {}", e.getMessage());
            return Optional.empty();
        }
    }

    public List<Event> fetchEventsByTopicId(String topicId){
        List<EventEntity> eventEntities = eventRepository.findByTopicId(topicId);
        return eventMapper.toDomainList(eventEntities);
    }
}
