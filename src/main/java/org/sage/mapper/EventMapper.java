package org.sage.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.mapstruct.*;
import org.sage.entity.EventEntity;
import org.sage.entity.TopicEntity;
import org.sage.object.domain.Event;
import org.sage.repository.TopicRepository;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class EventMapper {

    @Inject
    TopicRepository topicRepository;

    public abstract List<Event> toDomainList(List<EventEntity> entities);

    public abstract Event toDomain(EventEntity eventEntity);

    @InheritInverseConfiguration(name = "toDomain")
    public abstract EventEntity toEntity(Event event);

    public abstract void updateEntityFromDomain(Event event, @MappingTarget EventEntity eventEntity);

    public abstract void updateDomainFromEntity(EventEntity eventEntity, @MappingTarget Event event);

    @AfterMapping
    protected void extractTopicId(EventEntity entity, @MappingTarget Event domain) {
        TopicEntity topic = entity.getTopic();
        if (Objects.nonNull(topic)) {
            domain.setTopicId(topic.getId());
        }
    }

    @AfterMapping
    @Transactional
    protected void setTopicEntity(Event domain, @MappingTarget EventEntity entity) {
        String topicId = domain.getTopicId();
        if (Objects.nonNull(topicId)) {
            TopicEntity topic = topicRepository.findById(topicId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid topicId: " + topicId));
            entity.setTopic(topic);
        }
    }
}
