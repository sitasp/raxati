package org.sage.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.sage.entity.EventEntity;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EventRepository implements PanacheRepository<EventEntity> {
    public Optional<EventEntity> findBySlug(String slug) {
        return find("slug = ?1 and (is_deleted is null or is_deleted = false)", slug)
                .firstResultOptional();
    }

    public Optional<EventEntity> findById(String id){
        return find("id = ?1 and (is_deleted is null or is_deleted = false)", id)
                .firstResultOptional();
    }

    public List<EventEntity> findByTopicId(String topicId){
        return find("topic_id = ?1 and (is_deleted is null or is_deleted = false)", topicId)
                .list();
    }
}
