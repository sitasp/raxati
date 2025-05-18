package org.sage.service;

import com.github.slugify.Slugify;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sage.constant.RaxatiConstants;
import org.sage.entity.EventEntity;
import org.sage.entity.TopicEntity;
import org.sage.mapper.EventMapper;
import org.sage.mapper.TopicMapper;
import org.sage.object.domain.Event;
import org.sage.object.domain.Topic;
import org.sage.repository.EventRepository;
import org.sage.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class EventService {

    private final EventRepository eventRepository;

    private final EventMapper   eventMapper;

    private final Slugify slugify;

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

    @Transactional
    public Optional<Event> saveEvent(Event event){
        try {
            preprocess(event);
            EventEntity eventEntity = eventMapper.toEntity(event);
            eventRepository.persist(eventEntity);
            Event saved = eventMapper.toDomain(eventEntity);

            return Optional.of(saved);
        } catch (IllegalArgumentException e) {
            log.error("Error while saving event: {}", e.getMessage());
            return Optional.empty();
        }
    }

    private void preprocess(Event event) {
        String generatedSlug = slugify.slugify(event.getName());
        event.setSlug(generatedSlug);
        event.setId(IdGenerator.newUuid());
        if(Objects.isNull(event.getCreatedAt())){
            event.setCreatedAt(LocalDateTime.now());
        }
        if(Objects.isNull(event.getUpdatedAt())){
            event.setUpdatedAt(LocalDateTime.now());
        }
        event.setIsDeleted(false);
        if(Objects.isNull(event.getCreatedBy())) {
            event.setCreatedBy(RaxatiConstants.RAXATI_BOT);
        }
        if(Objects.isNull(event.getUpdatedBy())) {
            event.setUpdatedBy(RaxatiConstants.RAXATI_BOT);
        }
        event.setNumberOfLikes(0L);
        event.setNumberOfViews(0L);
        event.setStatus("active");
    }

    public List<Event> fetchEventsByTopicId(String topicId){
        List<EventEntity> eventEntities = eventRepository.findByTopicId(topicId);
        return eventMapper.toDomainList(eventEntities);
    }
}
