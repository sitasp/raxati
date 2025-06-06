package org.sage.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.sage.entity.EventEntity;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EventRepository implements PanacheRepository<EventEntity> {
    public Optional<EventEntity> findBySlug(String slug) {
        return find("slug = ?1 and (isDeleted is null or isDeleted = false)", slug)
                .firstResultOptional();
    }

    public Optional<EventEntity> findById(String id){
        if (id == null || id.isEmpty()) {
            return Optional.empty();
        }
        return find("id = ?1 and (isDeleted is null or isDeleted = false)", id)
                .firstResultOptional();
    }

    public List<EventEntity> findByTopicId(String topicId){
        return find("topic.id = ?1 and (isDeleted is null or isDeleted = false)", topicId)
                .list();
    }
}
