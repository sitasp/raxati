package org.sage.mapper;

import com.github.f4b6a3.ulid.Ulid;
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
public abstract class EventMapper implements BaseMapper<Event, EventEntity> {

    @Inject
    TopicRepository topicRepository;

    @AfterMapping
    protected void e2d(EventEntity entity, @MappingTarget Event domain) {
        TopicEntity topic = entity.getTopic();
        if (Objects.nonNull(topic)) {
            domain.setTopicId(topic.getId());
        }
    }

    @AfterMapping
    @Transactional
    protected void d2e(Event domain, @MappingTarget EventEntity entity) {
        String topicId = domain.getTopicId();
        if (Objects.nonNull(topicId)) {
            TopicEntity topic = topicRepository.findById(topicId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid topicId: " + topicId));
            entity.setTopic(topic);
        }
    }
}
